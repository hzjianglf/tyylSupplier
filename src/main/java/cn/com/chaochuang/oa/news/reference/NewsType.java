/*
 * FileName:    NewsType.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月25日 (HM) 1.0 Create
 */

package cn.com.chaochuang.oa.news.reference;

import cn.com.chaochuang.common.dictionary.IDictionary;
import cn.com.chaochuang.common.dictionary.support.DictionaryRefresher;

/** 
 * @ClassName: NewsType 
 * @Description: 新闻分类
 * @author: chunshu
 * @date: 2017年8月11日 上午11:50:45  
 */
public enum NewsType implements IDictionary {

    时政("0", "时政"), 社会("1", "社会"), 法制("2", "法制");

    private String key;
    private String value;

    private NewsType(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    private NewsType(String key, String value) {
        this.key = key;
        this.value = value;
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return (null == value) ? name() : value;
    }

    @Override
    public Object getObject() {
        return this;
    }

    @Override
    public String toString() {
        return this.key;
    }

}