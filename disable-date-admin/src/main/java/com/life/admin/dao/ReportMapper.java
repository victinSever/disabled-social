package com.life.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.life.admin.pojo.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    //分页查询待审核的举报信息
    @Select("select * from tb_disable_users_report WHERE status = 2 ORDER by id limit #{index},#{size}")
    List<Report> getReports(@Param("index") Integer index,@Param("size") Integer size);

    //获取举报待审核的总数
    @Select("select * from tb_disable_users_report WHERE status = 2")
    List<Report> getReportsAmount();
}
