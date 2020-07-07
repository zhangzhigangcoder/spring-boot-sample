package com.core.algorithm.common.kmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 关键词过滤
 *
 */
public class KeywordFilterUtil {

	/** 直接禁止的 */
	private static int matchType = 2; // 1:最小长度匹配 2：最大长度匹配
	
	public static void main(String[] args) {
		List<String> keywords = new ArrayList<String>() {
			private static final long serialVersionUID = 5885979316092126277L;
			{
				add("北京");
				add("上海");
				add("广州");
				add("南京");
				add("新疆乌鲁木齐");
			}
		};
		HashMap keysMap = addKeywords(keywords);
		String str = "北京是中国首都";
		System.out.println(isContentKeyWords(str, keysMap));
	}


    
    /**
     * 添加关键词
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static HashMap addKeywords(List<String> keywords) {
		HashMap keysMap = new HashMap();
        //循环关键词列表
        for (int i = 0; i < keywords.size(); i++) {
            //获取关键词
            String key = keywords.get(i).trim();
            //定义临时的变量
            HashMap nowhash = null;
            nowhash = keysMap;
            //循环关键词的个数
            for (int j = 0; j < key.length(); j++) {
                String word = String.valueOf(key.charAt(j));
                Object wordMap = nowhash.get(word);
                //关键字去重 wordMap != null 则说明关键字已存在
                if (wordMap != null) {
                    nowhash = (HashMap) wordMap;
                } else {
                    HashMap<String, String> newWordHash = new HashMap<String, String>();
                    newWordHash.put("isEnd", "0");
                    nowhash.put(word, newWordHash);
                    nowhash = newWordHash;
                    
                }
                //最后的关键词设置为结束
                if (j == key.length() - 1) {
                    nowhash.put("isEnd", "1");
                }
            }
        }
        
        return keysMap;
    }
    
    /**
     * 校验关键词
     */
    @SuppressWarnings("rawtypes")
	public static boolean isContentKeyWords(String txt,HashMap keysMap) {
        for (int i = 0; i < txt.length(); i++) {
            int len = checkKeyWords(txt, i, 1, keysMap);
            if (len > 0) { 
            	return true; 
            }
        }
        txt = null;
        return false;
    }
    
    /**
     * 替换
     */
    @SuppressWarnings("rawtypes")
	public static String getFilerWords(String txt,HashMap keysMap,Map<String,String> map) {
        int l = txt.length();
        String filterWords = txt;
        for (int i = 0; i < l;) {
            int len = checkKeyWords(txt, i, matchType,keysMap);
            if (len > 0) {
                String replaceStr = txt.substring(i, i + len);
                String keywordReplaced = map.get(replaceStr);
                filterWords = filterWords.replaceAll(txt.substring(i, i + len), keywordReplaced);
                i += len;
            } else {
                i++;
            }
        }
        return filterWords;
    }
    
    /**
     * 返回txt中关键字的列表
     */
    @SuppressWarnings("rawtypes")
	public static Set<String> getTxtKeyWords(String txt,HashMap keysMap) {
        Set<String> set = new HashSet<String>();
        int l = txt.length();
        for (int i = 0; i < l;) {
            int len = checkKeyWords(txt, i, matchType,keysMap);
            if (len > 0) {
                set.add(txt.substring(i, i + len));
                i += len;
            } else {
                i++;
            }
        }
        txt = null;
        return set;
    }

    /**
     * 检查一个字符串从begin位置起开始是否有keyword符合， 如果有符合的keyword值，返回值为匹配keyword的长度，否则返回零
     *  flag 1:最小长度匹配 2：最大长度匹配
     */
    @SuppressWarnings("rawtypes") 
    private static int checkKeyWords(String txt, int begin, int flag,HashMap keysMap) {
        Map nowhash = null;
        nowhash = keysMap;
        int maxMatchRes = 0;
        int res = 0;
        int l = txt.length();
        String word = "";
        for (int i = begin; i < l; i++) {
            word = String.valueOf(txt.charAt(i));
            Object wordMap = nowhash.get(word);
            if (wordMap != null) {
                res++;
                nowhash = (Map) wordMap;
                if (((String) nowhash.get("isEnd")).equals("1")) {
                    //最小长度匹配的比如 中国 和中国人为关键词，最小长度是匹配到中国就返回，最大长度则是匹配到最后
                    if (flag == 1) {
                        wordMap = null;
                        nowhash = null;
                        txt = null;
                        return res;
                    } else {
                        maxMatchRes = res;
                    }
                }
            } else {
                txt = null;
                nowhash = null;
                return maxMatchRes;
            }
        }
        txt = null;
        nowhash = null;
        return maxMatchRes;
    }
    
}