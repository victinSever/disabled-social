package com.life.admin.dao;

import com.life.admin.pojo.PaymentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaymentInfoMapper extends BaseMapper<PaymentInfo> {

    @Select("select * FROM tb_disable_date_payment_info order by order_id limit #{index},#{size}")
    List<PaymentInfo> getPaymentInfoList(@Param("index") Integer index,@Param("size") Integer size);

    @Delete("delete from tb_disable_date_payment_info where order_id = #{orderId}")
    boolean deletePaymentInfo(Integer orderId);
}
