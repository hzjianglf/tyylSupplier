/*
 * FileName:    SmsBaseInfo.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2011
 * History:     2011-9-29 (pjs) 1.0 Create
 */

package cn.com.chaochuang.doc.sms.bean;

import java.util.Date;


/** 
 * @ClassName: SmsBaseInfo 
 * @Description: ���ŷ���ʵ����
 * @author: chunshu
 * @date: 2017��9��21�� ����10:06:45  
 */
public class SmsBaseInfo {
	
	/** �Ƿ���Ҫ�ظ� 0:����Ҫ */
	public static Integer REPORT_NEED = 1;
	/** �Ƿ���Ҫ�ظ� 1:��Ҫ */
    public static Integer REPORT_NOT_NEED = 0;
    /** ��ʱ���� */
    public static String IMM_SEND = "0";
    /** ��ʱ���� */
    public static String TIMING_SEND = "1";
    
    /** ���� */
	private String 		 message;
	/** ��ʱ���ͱ�־ Ĭ��Ϊ��0 ��ʱ���� 1��Ϊ��ʱ����*/
	private String 		 timingSendFlag;
	/** ��ʱ����ʱ�� */
	private Date 		 timingSendTime;
	/** ������ID */
	private Long 		 sendManId;
	/** �Ƿ���Ҫ�ظ� 0:����Ҫ  1:��Ҫ */
    private Integer 	 reqDeliveryReport;

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setTimingSendTime(Date timingSendTime) {
		this.timingSendTime = timingSendTime;
	}
	
	public Date getTimingSendTime() {
		return timingSendTime;
	}
	
	public void setTimingSendFlag(String timingSendFlag) {
		this.timingSendFlag = timingSendFlag;
	}
	
	public void setSendManId(Long sendManId) {
		this.sendManId = sendManId;
	}
	
	public Long getSendManId() {
		return sendManId;
	}
	
	public Integer getReqDeliveryReport() {
		return reqDeliveryReport;
	}
	
	public void setReqDeliveryReport(Integer reqDeliveryReport) {
		this.reqDeliveryReport = reqDeliveryReport;
	}
	
	public String getTimingSendFlag() {
		return timingSendFlag;
	}
	
}
