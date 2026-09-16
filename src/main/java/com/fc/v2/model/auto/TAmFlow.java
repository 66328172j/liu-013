package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 隐患整改单对象 t_am_flow
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_am_flow")
@ApiModel(value = "TAmFlow", description = "隐患整改单")
public class TAmFlow implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 整改单号 */
    @TableField("biz_no")
    @ApiModelProperty(value = "整改单号")
    private String bizNo;

    /** 设施编号 */
    @TableField("device_code")
    @ApiModelProperty(value = "设施编号")
    private String deviceCode;

    /** 隐患描述 */
    @TableField("hazard_desc")
    @ApiModelProperty(value = "隐患描述")
    private String hazardDesc;

    /** 隐患等级 1一般 2重大 */
    @TableField("hazard_level")
    @ApiModelProperty(value = "隐患等级 1一般 2重大")
    private Integer hazardLevel;

    /** 当前环节 0..3 */
    @TableField("stage")
    @ApiModelProperty(value = "当前环节 0..3")
    private Integer stage;

    /** 流程状态 0待发起 1在办 2已办结 */
    @TableField("status")
    @ApiModelProperty(value = "流程状态 0待发起 1在办 2已办结")
    private Integer status;

    /** 处置说明 */
    @TableField("content")
    @ApiModelProperty(value = "处置说明")
    private String content;

    /** 最近一次流转动作 */
    @TableField("last_action")
    @ApiModelProperty(value = "最近一次流转动作")
    private String lastAction;

    /** 删除标记 0正常 1删除 */
    @TableField("del_flag")
    @ApiModelProperty(value = "删除标记 0正常 1删除")
    private Integer delFlag;

    /** 创建者 */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /** 更新者 */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /** 更新时间 */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 备注 */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getHazardDesc() {
        return hazardDesc;
    }

    public void setHazardDesc(String hazardDesc) {
        this.hazardDesc = hazardDesc;
    }

    public Integer getHazardLevel() {
        return hazardLevel;
    }

    public void setHazardLevel(Integer hazardLevel) {
        this.hazardLevel = hazardLevel;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
