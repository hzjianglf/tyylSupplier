package cn.com.chaochuang.doc.sms.service;


import cn.com.chaochuang.doc.sms.bean.SmsBaseInfo;


/** 
 * @ClassName: SmsService 
 * @Description: ���ŷ��ʹ���
 * @author: chunshu
 * @date: 2017��9��21�� ����10:11:03  
 */
public interface SmsService {

    
    /** 
     * @Title: sendUserIdListMessage 
     * @Description: ���ŷ��ʹ���
     * @param userIds
     * @param smsBaseInfo
     * @return: void
     */
    void sendUserIdListMessage(String userIds, SmsBaseInfo smsBaseInfo);

}
