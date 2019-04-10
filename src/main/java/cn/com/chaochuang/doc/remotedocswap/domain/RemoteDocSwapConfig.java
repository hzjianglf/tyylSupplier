package cn.com.chaochuang.doc.remotedocswap.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.chaochuang.common.data.domain.LongIdEntity;


/** 
 * @ClassName: RemoteDocSwapConfig 
 * @Description: ��ʱ��������
 * @author: chunshu
 * @date: 2017��8��31�� ����4:37:31  
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "config_id")) })
@Table(name = "remote_doc_swap_config")
public class RemoteDocSwapConfig extends LongIdEntity {

    //------��ɫ����
    //��������Ա
    public static final String SWAP_ROLE_ADMIN="admin";
    //��Ҫ��ǩ��
    public static final String SWAP_ROLE_JY="swap_signer_jy";
    //�칫��ǩ��
    public static final String SWAP_ROLE_BGS="swap_signer_bgs";

    //------�滻������
    public static final String REPLACE_TITLE="#title";
    public static final String REPLACE_TIME="#limitTime";
    public static final String REPLACE_UNIT="#orgName";

    //�յ�������Ҫ���Ͷ��Ÿ�ǩ����
    public static final String CONFIG_TYPE_MSG="sendMsg";
    //���Ͳ�ѯ--��ɫ��Ӧ�Ĳ�ѯȨ��
    public static final String CONFIG_TYPE_SEND_QUERY="sendQuery";
    public static final String CONFIG_TYPE_SIGN_QUERY="signByDept";

    //ǩ��ʱ���Ž�����user_id,���ʹ��,�ָ�
    public static final String RECEIVER_IDS="receiverIds";
    //ǩ��ʱ���ŷ�����user_id
    public static final String SEND_MSG_SENDER="msgSender";
    //ǩ��ʱ���Ͷ��ŵ�ģ��
    public static final String SEND_MSG_TMP="msgTemplate";
    //���췢�Ͷ���ģ��
    public static final String FORDO_SEND_MSG="fordoSendMsg";
    //���췢�Ͷ���ʱ�������ĸ����ڲ�������
    public static final String IGNORE_ORDO_SEND_MSG="ignoreFordoSendMsg";
    //�߰췢�Ͷ���ģ��
    public static final String NOTICE_SEND_MSG="noticeSendMsg";
    //��ʱ�߰췢�Ͷ���ģ��
    public static final String OVERTIME_SEND_MSG="overtimeSendMsg";
    //��ǩ���˷�������
    public static final String NOTICE_SIGNER_MSG="signerSendMsg";
    //������Ϣ������Ա
    public static final String ERROR_DATA_RECIEVER="errorDataReceiver";
    public static final String SIGN_NAME_DATA="signNameData";
    
    //��ʱ���Ĺ����ջ�ȡʧ��ʱ������Ա
    public static final String WORK_DAY_ERROR_RECIEVER="workDayErrorReceiver";

    /** �������� */
    private String 			   configName;
    /** �������� */
    private String 			   configType;
    /** �������� */
    private String 			   configValue;
    
    /** ����˵�� */
    private String 			   configComment;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigComment() {
        return configComment;
    }

    public void setConfigComment(String configComment) {
        this.configComment = configComment;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String[] getConfigValueList(){
        if(configValue==null){
            return null;
        }
        if(configValue.contains(",")) {
            return configValue.split(",");
        }
        return new String[]{configValue};
    }
}
