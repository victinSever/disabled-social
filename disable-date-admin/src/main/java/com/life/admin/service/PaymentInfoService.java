package com.life.admin.service;

import com.life.admin.pojo.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PaymentInfoService extends IService<PaymentInfo> {

    List<PaymentInfo> getPaymentInfoList(Integer index, Integer size);

    boolean deletePaymentInfo(Integer orderId);
}
