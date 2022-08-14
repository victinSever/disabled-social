package com.life.admin.service;

import com.life.admin.pojo.Diary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IDiaryService extends IService<Diary> {

    //获取（没有审核通过的）动态列表
    List<Diary> getVideoDiaryList(Integer index, Integer size);

    //获取（待审核的非视频类的）动态列表
    List<Diary> getNotVideoDiaryList(Integer index, Integer size);

    //获取未审核通过的视频类动态数目
    Integer getVideoDiarys();

    //获取待审核的图文类动态数目
    Integer getNotVideoDiarys();

    //更新动态审核结果
    void updateAuditDiaryResult(String diaryId, Integer isDeleted, String ps);

    //更新举报状态
    void reportResult(Integer id, Integer status,String ps);

    //同步动态到es
    void insertDiaryToIndexById(String message);
}
