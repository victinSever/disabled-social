package com.life.admin.service.impl;

import com.life.admin.dao.AuditMapper;
import com.life.admin.pojo.AuditLogs;
import com.life.admin.service.AuditService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, AuditLogs> implements AuditService {
    @Autowired
    private AuditMapper auditMapper;


    public List<AuditLogs> getAuditLogs(Integer index, Integer size) {
        return auditMapper.getAuditLogs(index,size);
    }
}
