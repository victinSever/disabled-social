package com.life.admin.service;

import com.life.admin.pojo.AuditLogs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AuditService extends IService<AuditLogs> {

    List<AuditLogs> getAuditLogs(Integer index, Integer size);
}
