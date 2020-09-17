package org.spring.boot.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.searchbox.annotations.JestId;
import lombok.Data;
import lombok.EqualsAndHashCode;


///**
// * @ClassName OperateTrace
// * @Description 搜索日志
// * @Author zhangzhigang
// * @Date 2020/8/27 10:19
// * @Version 1.0
// **/



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false, of = {"id"})
public class OperateTrace {
	private static final long serialVersionUID = 1237880684013923215L;
	
	@JestId
    private String id;

	// 登录用户id
    private String userId;
    
    // 父节点id
    private String parentId;
    
    // 对象id(personId | vehicleId | caseId)
    private String targetId;
    
    // 关系
    private String relation;
    
    // 人员姓名 | 案件名称
    private String name;
    
    // 证件号 | 车牌号 | 案件号
    private String identifyCode;
    
    // 人员头像 | 车辆图片 | 案件图片
    private String image;
    
    // 根节点
    private String rootId;
    
    // 操作时间
    private String operateTime;
    
    // 最后更新时间
    private String lastUpdateTime;
    
    private Integer objectType;

}
