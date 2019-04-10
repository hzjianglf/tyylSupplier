package cn.com.chaochuang.pubaffairs.leave.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.com.chaochuang.common.data.domain.StringUuidEntity;
import cn.com.chaochuang.common.user.domain.SysDepartment;
import cn.com.chaochuang.common.user.domain.SysUser;
import cn.com.chaochuang.pubaffairs.car.reference.CarStatus;
import cn.com.chaochuang.pubaffairs.car.reference.CarStatusConverter;
import cn.com.chaochuang.pubaffairs.leave.reference.LeaveType;
import cn.com.chaochuang.pubaffairs.leave.reference.LeaveTypeConverter;

/**
 * @author dengl 2018.08.08
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id")) })
public class OaLeave extends StringUuidEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4101026717189406653L;
	
	/** 申请日期 */
    private Date        	createDate;

    /** 申请人ID */
    private Long          	creatorId;
    
    @ManyToOne
    @JoinColumn(name = "creatorId" , insertable = false, updatable = false)
    private SysUser         creator; 
    
    /** 申请人名称 */
    private String          creatorName;
    
    /** 申请人部门ID */
    private Long          	deptId;
    @ManyToOne
    @JoinColumn(name = "deptId", updatable = false, insertable = false)
    // 找不到引用的外键数据时忽略
    @NotFound(action = NotFoundAction.IGNORE)
    private SysDepartment 	dept;
    
    /** 申请人部门名称 */
    private String          deptName;
    
    /** 假期类型 */
    @Convert(converter = LeaveTypeConverter.class)
    private LeaveType       leaveType;

    /** 请假事由 */
    private String   		reason;

    /** 请假天数 */
    private BigDecimal   	leaveDay;

    /** 休假开始时间 */
    private Date	        beginTime;

    /** 休假结束时间 */
    private Date	        endTime;
    
    /** 状态 */
    @Convert(converter = CarStatusConverter.class)
    private CarStatus       status;

    /** 版本号 */
    private Integer         version_;
    
    /** 流程定义ID */
    private String          flowId;
    
    /** 流程实例ID */
    private String          flowInstId;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(BigDecimal leaveDay) {
		this.leaveDay = leaveDay;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public CarStatus getStatus() {
		return status;
	}

	public void setStatus(CarStatus status) {
		this.status = status;
	}

	public Integer getVersion_() {
		return version_;
	}

	public void setVersion_(Integer version_) {
		this.version_ = version_;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowInstId() {
		return flowInstId;
	}

	public void setFlowInstId(String flowInstId) {
		this.flowInstId = flowInstId;
	}
    
}
