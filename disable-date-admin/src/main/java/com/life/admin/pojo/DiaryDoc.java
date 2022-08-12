package com.life.admin.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class DiaryDoc {

    private Integer id;
    private String diaryId;
    private String diaryUserId;
    private String diaryUserName;
    private String diaryTitle;
    private String diaryContent;
    private Integer diaryKind;
    private Integer diaryCategoryId;
    private String diaryCategoryName;
    private String diaryTags;
    private Integer diaryStatus;
    private Integer diaryComment;
    private Integer diaryViews;
    private Integer diaryLove;
    private Integer diaryCollect;
    private Integer enableLook;
    private Integer enableComment;
    private  Integer isDeleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;
    private Integer isReport;       //举报 0-不举报 1-举报
    private String ps;

    private List<String> suggestion;  //给用户做自动补全的一些内容

    public DiaryDoc(Diary diary) {

        this.id = diary.getId();
        this.diaryId = diary.getDiaryId();
        this.diaryUserId = diary.getDiaryUserId();
        this.diaryUserName = diary.getDiaryUserName();
        this.diaryTitle = diary.getDiaryTitle();
        this.diaryContent = diary.getDiaryContent();
        this.diaryKind = diary.getDiaryKind();
        this.diaryCategoryId = diary.getDiaryCategoryId();
        this.diaryCategoryName = diary.getDiaryCategoryName();
        this.diaryTags = diary.getDiaryTags();
        this.diaryStatus = diary.getDiaryStatus();
        this.diaryComment = diary.getDiaryComment();
        this.diaryViews = diary.getDiaryViews();
        this.diaryLove = diary.getDiaryLove();
        this.diaryCollect = diary.getDiaryCollect();
        this.enableLook = diary.getEnableLook();
        this.enableComment = diary.getEnableComment();
        this.isDeleted = diary.getIsDeleted();
        this.createTime = diary.getCreateTime();
        this.updateTime = diary.getUpdateTime();
        this.isReport = diary.getIsReport();
        this.ps = diary.getPs();

        //diaryContent 和 diaryTitle 变成集合放到suggestion里
        if(this.diaryTitle.contains("/")){
            //business有多个值，需要切割
            String[] arr = this.diaryTitle.split("/");
            //添加元素
            this.suggestion = new ArrayList<String>();
            Collections.addAll(this.suggestion,arr);
        }else
            this.suggestion = Arrays.asList(this.diaryContent,this.diaryTitle);
    }



}
