package cn.com.chaochuang.doc.archive.reference;

import cn.com.chaochuang.common.dictionary.IDictionary;
import cn.com.chaochuang.common.dictionary.support.DictionaryRefresher;

/**
 * @author dengl 2017.11.29
 *
 */
public enum ArchStatus implements IDictionary{
	
	在库("1", "在库"), 外借("2", "外借"), 销毁("3", "销毁");
	
	private String key;
    private String value;
    
    private ArchStatus(String key) {
        this(key, null);
        DictionaryRefresher.getInstance().refreshIDictionary(this);
    }

    private ArchStatus(String key, String value) {
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