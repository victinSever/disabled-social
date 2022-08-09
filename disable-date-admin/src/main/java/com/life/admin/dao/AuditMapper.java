package com.life.admin.dao;

import com.life.admin.pojo.AuditLogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuditMapper extends BaseMapper<AuditLogs> {

    @Select("select * from tb_disable_date_admin_audit_logs order by id limit #{index},#{size}")
    List<AuditLogs> getAuditLogs(@Param("index") Integer index,@Param("size") Integer size);
}
