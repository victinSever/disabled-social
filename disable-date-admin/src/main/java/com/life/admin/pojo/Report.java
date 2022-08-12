package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_disable_users_report")
public class Report {
    private Integer id;
    private String userId;
    private String reportedUserId;
    private String reportedId;
    private String reason;
    private Integer type;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private String ps;
}
