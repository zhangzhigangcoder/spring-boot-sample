package org.spring.boot.es;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.boot.Application;
import org.spring.boot.EsApplication;
import org.spring.boot.entity.OperateTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@ActiveProfiles("test")
@Slf4j
public class EsTest {

    private static final String TEST_INDEX = "index_dimensions_search_trace";
    private static final String TEST_TYPE = "type_dimensions_search_trace";

    @Autowired
    private JestClient client;

   @Before
    public void doBefore() {
        log.info("---------before---------");
    }

    @Test
    public void testQuery() throws IOException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder userIdQuery = QueryBuilders.termQuery("id", "a5f07f42bb494a9dab6b09318d3adc62");
        boolQueryBuilder.must(userIdQuery);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);

//        if (pageNo != null && pageSize !=null) {
//            searchSourceBuilder.from(pageNo*pageSize).size(pageSize);
//        }
//        if (sort != null) {
//            searchSourceBuilder.sort(sort);
//        }
        // 如果没有分页，默认接收上限1000条
//        if (pageNo == null && pageSize == null) {
//            searchSourceBuilder.from(0).size(1000);
//        }
        log.info("查询语句：{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(TEST_INDEX)
                .build();
        SearchResult execute = client.execute(search);
        List list = convertList(execute, OperateTrace.class);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(p -> {
                log.info("结果:{}", p);
            });
        }
    }

    @Test
    public void testInsert() throws IOException {
        OperateTrace operateTrace = buildInstance();
        Index index = new Index.Builder(operateTrace)
                .index(TEST_INDEX)
                .type(TEST_TYPE)
                // Builder中会查找JestId注解的值作为id
//              .id(operateTrace.getId())
                .refresh(true)
                .build();
        log.info("插入数据参数：{}", index);
        final DocumentResult result = client.execute(index);
        log.info("数据插入成功,id = {}", result.getId());
    }

    @Test
    public void testBulkInsert() throws IOException {
        List<OperateTrace> operateTraces = this.buildBulkInstance();
        Bulk.Builder builder = new Bulk.Builder();
        operateTraces.forEach(e -> {
            Index index = new Index.Builder(e)
                    .index(TEST_INDEX)
                    .type(TEST_TYPE)
                    .build();
            builder.addAction(index);
        });
        BulkResult result = this.client.execute(builder.refresh(true).build());
        Set<String>  failedIds = result.getFailedItems().stream().map(item -> item.id).collect(Collectors.toSet());
        List<String> successfulIds = operateTraces.stream()
                                    .map(entity -> entity.getId())
                                    .filter(id -> !failedIds.contains(id))
                                    .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(successfulIds)) {
            successfulIds.forEach(id -> log.info("-----id = {}", id));
        }
    }

    @Test
    public void testDeleteById() throws IOException {
       String id = "755756c473d84cf9bbcb8103764f0f61";
        Delete build = new Delete.Builder(id)
                        .index(TEST_INDEX)
                        .type(TEST_TYPE)
                        .refresh(true)
                        .build();
        DocumentResult result = this.client.execute(build);
        log.info("result = {}", result.getId());
    }

    @Test
    public void testBulkDeleteById() throws IOException {
        List<String> ids = Arrays.asList("755756c473d84cf9bbcb8103764f0f61","d1390380235a44bc9e99ce8cba0f6d6d");
        Bulk.Builder builder = new Bulk.Builder();
        ids.forEach(id -> {
            builder.addAction(new Delete.Builder(id).index(TEST_INDEX).type(TEST_TYPE).build());
        });
        Bulk build = builder.refresh(true).build();
        BulkResult result = this.client.execute(build);
        Set<String> failedIds = result.getFailedItems().stream().map(item -> item.id).collect(Collectors.toSet());
        List<String> successfulIds = ids.stream()
                .filter(id -> !failedIds.contains(id))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(successfulIds)) {
            successfulIds.forEach(id -> log.info("-----id = {}", id));
        }
    }

    /**
     * 统计
     */
    @Test
    public void testCount() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder userIdQuery = QueryBuilders.termQuery("id", "a5f07f42bb494a9dab6b09318d3adc62");
        boolQueryBuilder.must(userIdQuery);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        Count count = new Count.Builder().query(searchSourceBuilder.toString()).addIndex(TEST_INDEX).build();
        try {
            CountResult result = this.client.execute(count);
            log.info("查询结果: {}", result.getCount());
        } catch (IOException e) {
            log.error("查询失败: {}", e);
        }
    }

    private List convertList(SearchResult result, Class clazz) {
        List<Object> datas = new ArrayList<>();
        try {
            // 过滤打印异常
            JsonArray asJsonArray = result.getJsonObject().get("_shards").getAsJsonObject().getAsJsonArray("failures");
            if (asJsonArray != null) {
                asJsonArray.forEach(a -> {
                    JSONObject jsonObject = JSONObject.parseObject(a.getAsJsonObject().toString());
                    log.error("一键搜查询异常索引:{}", jsonObject.getString("index"));
                    log.error("一键搜查询异常索引详情:{}", jsonObject.getString("reason"));
                });
            }

            List<SearchResult.Hit<JSONObject, Void>> hits = result.getHits(JSONObject.class);
            //遍历取出数据
            for (SearchResult.Hit s : hits) {
                Map<String, Object> source = (Map<String, Object>) s.source;
                JSONObject jsonObject = new JSONObject(source);
                jsonObject.put("id", s.id);
                Object o1 = jsonObject.toJavaObject(clazz);
                datas.add(o1);
            }
        } catch (Exception e) {
            log.error("转换es数据异常: {}", e);
        }
        return datas;
    }

    /**
     * 构建实体
     */
    private OperateTrace buildInstance() {
        OperateTrace operateTrace = new OperateTrace();
        operateTrace.setId(this.uuid());
        operateTrace.setIdentifyCode("111111");
        return operateTrace;
    }
    
    private List<OperateTrace> buildBulkInstance() {
        ArrayList<OperateTrace> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            OperateTrace operateTrace = new OperateTrace();
            operateTrace.setId(this.uuid());
            operateTrace.setIdentifyCode("111111" + i);
            list.add(operateTrace);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * 生成id
     */
    private String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
