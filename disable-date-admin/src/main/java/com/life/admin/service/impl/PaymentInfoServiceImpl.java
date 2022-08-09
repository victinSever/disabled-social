package com.life.admin.service.impl;

import com.life.admin.dao.PaymentInfoMapper;
import com.life.admin.pojo.PaymentInfo;
import com.life.admin.service.PaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    public List<PaymentInfo> getPaymentInfoList(Integer index, Integer size) {
        List<PaymentInfo> list = paymentInfoMapper.getPaymentInfoList( index,  size);
        return list;
    }

    @Override
    public boolean deletePaymentInfo(Integer orderId) {
        return paymentInfoMapper.deletePaymentInfo(orderId);
    }
}
