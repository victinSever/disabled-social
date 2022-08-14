package com.life.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE `tb_disable_date_person_detail_info`  (
 *   `person_id` BIGINT(0)  '用户主键id',
 *   `disable_type` VARCHAR(10)  '' COMMENT '残疾类别',
 *   `disable_level` TINYINT(0)  '残疾等级 1-健康 2-轻度残疾 3-中度残疾 4-重度残疾 5-极重度残疾',
 *   `is_provide` TINYINT(0)  '能否自理 1-完全自理，并能照顾对方 2-不能自理',
 *   `auxiliary_tool` VARCHAR(10)  '辅助工具',
 *   `cause` VARCHAR(30)  '健康状况原因',
 *   `is_genetic` TINYINT(0)  '子女是否遗传 1-不遗传 2-遗传',
 *   `parent_status` VARCHAR(50)  '父母状况',
 *   `broher_number` TINYINT(0)  '兄弟姐妹个数',
 *   `living_will` TINYINT(0)  '未来居住意愿 1-与自己父母同住 2-与Ta父母同住 3-两个人同居',
 *   `is_smoking` TINYINT(0)  '是否吸烟 1-吸烟 2-不吸烟',
 *   `marry_form` TINYINT(0)  '嫁娶形式 1-两头顾 2-男方家庭为主 3-女方家庭为主',
 *   `is_drinking` TINYINT(0)  '是否饮酒 1-经常喝酒 2-有时喝一些 3-不喝酒',
 *   `fertility_status` VARCHAR(10)  '生育情况',
 *   `keeping_status` VARCHAR(10)  '养宠物情况',
 *   `hobby` VARCHAR(20)  '兴趣爱好 ',
 *   `blood_type` TINYINT(0)  '血型 1-A型 2-B型 3-AB型 4-O型',
 *   `habit` VARCHAR(20)  '作息习惯 ',
 *   `company_type` TINYINT(0)  '单位类型 1-国有企业工作 2-民营企业工作 3-体制内',
 *   `company_name` VARCHAR(25)  '工作单位 ',
 *   `work_industry` VARCHAR(20)  '工作行业 ',
 *   `housing_location` VARCHAR(50)  '房产位置',
 *   `person_tag` TEXT  '我的标签',
 *   PRIMARY KEY (`person_id`) USING BTREE
 *
 */

@Data
@TableName("tb_disable_date_person_detail_info")
public class DetailInfo {

    private Integer personId;
    private String disableType;
    private Integer disableLevel;
    private Integer isProvide;
    private String auxiliaryTool;
    private String cause;
    private Integer isGenetic;
    private String parentStatus;
    private Integer brotherNumber;
    private Integer livingWill;
    private Integer isSmoking;
    private Integer marryForm;
    private Integer isDrinking;
    private String fertilityStatus;
    private String keepingStatus;
    private String hobby;
    private Integer bloodType;
    private String habit;
    private Integer companyType;
    private String companyName;
    private String workIndustry;
    private String housingLocation;
    private String personTag;



//    private Integer person_id;
//    private String disable_type;
//    private Integer is_provide;
//    private String auxiliary_tool;
//    private String cause;
//    private Integer is_genetic;
//    private String parent_status;
//    private Integer broher_number;
//    private Integer living_will;
//    private Integer is_smoking;
//    private Integer marry_form;
//    private Integer is_drinking;
//    private String fertility_status;
//    private String keeping_status;
//    private String hobby;
//    private Integer blood_type;
//    private String habit;
//    private Integer company_type;
//    private String company_name;
//    private String work_industry;
//    private String housing_location;
//    private String person_tag;

}
