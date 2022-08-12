package com.life.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.life.admin.dao.ReportMapper;
import com.life.admin.pojo.Report;
import com.life.admin.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    private  ReportMapper reportMapper;

    //分页查询待审核的举报信息
    public List<Report> getReports(Integer index, Integer size) {
        return reportMapper.getReports( index,  size);
    }

    //获取举报待审核的总数
    public Integer getReportsAmount() {
        List<Report> reports = reportMapper.getReportsAmount();
        Integer total = reports.size();
        return total;
    }
}
