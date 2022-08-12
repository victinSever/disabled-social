package com.life.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.life.admin.pojo.Report;

import java.util.List;

public interface ReportService extends IService<Report> {

    //分页查询待审核的举报信息
    List<Report> getReports(Integer index, Integer size);

    //获取举报待审核的总数
    Integer getReportsAmount();
}
