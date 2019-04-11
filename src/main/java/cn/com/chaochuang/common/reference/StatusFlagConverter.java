/*
 * FileName:    AttachFlagConverter.java
 * Description:
 * Company:     
 * Copyright:    (c) 2016
 * History:     2016年1月25日 (HM) 1.0 Create
 */

package cn.com.chaochuang.common.reference;

import javax.persistence.AttributeConverter;

import cn.com.chaochuang.common.dictionary.EnumDictConverter;

/**
 * @author HM
 *
 */
public class StatusFlagConverter extends EnumDictConverter<StatusFlag> implements
                AttributeConverter<StatusFlag, String> {

}
