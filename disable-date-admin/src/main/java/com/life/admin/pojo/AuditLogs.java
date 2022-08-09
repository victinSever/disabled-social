package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.imageio.ImageTranscoder;
import java.util.Date;

/**
 * 审核日志
 * tb_disable_date_admin_audit_logs
 */
@Data
@TableName("tb_disable_date_admin_audit_logs")
public class AuditLogs {

    private Integer id;
    private Integer type;
    private Integer auditStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;        //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date acceptTime;        //受理时间
    private String adminName;
    private Integer adminId;
}
