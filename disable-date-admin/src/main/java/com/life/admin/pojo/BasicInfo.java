package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * CREATE TABLE `tb_disable_date_person_basic_info`  (
 *   `person_id` BIGINT(0)                      '用户基本信息表主键id',
 *   `person_name` VARCHAR(10)                  'COMMENT '姓名',
 *   `sex` TINYINT(0)                           '性别 1-男 2-女',
 *   `age` INT(0)                               '年龄',
 *   `image_path` VARCHAR(100)                  '人脸图片上传路径',
 *   `disable_number` VARCHAR(20)               '残疾证号码',
 *   `work_addr` VARCHAR(50)                    '工作地区',
 *   `household_addr` VARCHAR(50)               '户籍地区',
 *   `marital_status` TINYINT(0)                '婚姻情况(1-未结婚 2-离异 3-已婚)',
 *   `height` INT(0)                            '身高',
 *   `weight` INT(0)                            '体重',
 *   `degree` VARCHAR(5)                        '最高学历',
 *   `income` INT(0)                            '月收入',
 *   `occupation` VARCHAR(10)                   '职业',
 *   `housing_status` VARCHAR(10)               '住房情况',
 *   `car_status` VARCHAR(10)                   '购车情况 ',
 *   `expected_marry_time` DATETIME(0)          '期待结婚时间',
 *   `person_intro` TEXT                        '自我介绍',
 *   `person_sign` TEXT                         '爱情宣言',
 *   `wechat` VARCHAR(20)                       '微信号 ',
 *   `wechat_code_images_path` VARCHAR(50)      '微信二维码上传路径',
 *   `qq` VARCHAR(13)                           'QQ账号 ',
 *   `email` VARCHAR(30)                        '邮箱',
 *   `phone` VARCHAR(11)                        '电话',
 *
 *   PRIMARY KEY (`person_id`) USING BTREE
 */

@Data
@TableName("tb_disable_date_person_basic_info")
public class BasicInfo {


    private Integer personId;
    private String personName;
    private Integer sex;
    private Integer age;
    private String imagePath;
    private String disableNumber;
    private String workAddr;
    private String householdAddr;
    private String maritalStatus;
    private Integer height;
    private Integer weight;
    private String degree;
    private Integer  income;
    private String occupation;
    private String housingStatus;
    private String carStatus;
    private Date expectedMarryTime;
    private String personIntro;
    private String personSign;
    private String wechat;
    private String wechatCodeImagesPath;
    private String qq;
    private String email;
    private String phone;


//    private Integer person_id;
//    private String person_name;
//    private Integer sex;
//    private Integer age;
//    private String image_path;
//    private String disable_number;
//    private String work_addr;
//    private String household_addr;
//    private Integer marital_status;
//    private Integer height;
//    private Integer weight;
//    private String degree;
//    private String  income;
//    private String occupation;
//    private String housing_status;
//    private String car_status;
//    private Date expected_marry_time;
//    private String person_intro;
//    private String person_sign;
//    private String wechat;
//    private String wechat_code_images_path;
//    private String qq;
//    private String email;
//    private String phone;





}
