package com.life.admin.constants;



//定义交换机的 常量
public class MqConstants {

//————————————————————————————————————————————————————diary————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String DIARY_EXCAHGNE = "diary.topic";

    /**
     * 监听新增和修改的队列
     */
    public final static String DIARY_INSERT_QUEUE = "diary.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String DIARY_DELETE_QUEUE = "diary.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String DIARY_INSERT_KEY = "diary.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String DIARY_DELETE_KEY = "diary.delete";
//————————————————————————————————————————————————————diary end————————————————————————————————————————————————————————————————

//————————————————————————————————————————————————————picture————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String PICTURE_EXCAHGNE = "picture.topic";

    /**
     * 监听新增和修改的队列
     */
    public final static String PICTURE_INSERT_QUEUE = "picture.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String PICTURE_DELETE_QUEUE = "picture.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String PICTURE_INSERT_KEY = "picture.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String PICTURE_DELETE_KEY = "picture.delete";

//————————————————————————————————————————————————————picture end———————————————————————————————————————————————————————————

//————————————————————————————————————————————————————comment————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String COMMENT_EXCAHGNE = "comment.topic";

    /**
     * 监听新增和修改的队列
     */
    public final static String COMMENT_INSERT_QUEUE = "comment.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String COMMENT_DELETE_QUEUE = "comment.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String COMMENT_INSERT_KEY = "comment.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String COMMENT_DELETE_KEY = "comment.delete";

//————————————————————————————————————————————————————comment end———————————————————————————————————————————————————————————

//————————————————————————————————————————————————————评论的回复 reply————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String REPLY_EXCAHGNE = "reply.topic";

    /**
     * 监听新增和修改的队列
     */
    public final static String REPLY_INSERT_QUEUE = "reply.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String REPLY_DELETE_QUEUE = "reply.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String REPLY_INSERT_KEY = "reply.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String REPLY_DELETE_KEY = "reply.delete";

//————————————————————————————————————————————————————评论的回复 reply end———————————————————————————————————————————————————————————

//    LIKEVIDEO_EXCAHGNE


//————————————————————————————————————————————————————tb_disable_diary_videos ————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String DIARYVIDEO_EXCAHGNE = "diaryVideos.topic";
    /**
     * 监听新增和修改的队列
     */
    public final static String DIARYVIDEO_INSERT_QUEUE = "diaryVideos.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String DIARYVIDEO_DELETE_QUEUE = "diaryVideos.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String DIARYVIDEO_INSERT_KEY = "diaryVideos.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String DIARYVIDEO_DELETE_KEY = "diaryVideos.delete";

//————————————————————————————————————————————————————tb_disable_diary_videos  end———————————————————————————————————————————————————————————

    //————————————————————————————————————————————————————tb_disable_date_user_collect ————————————————————————————————————————————————————————————————
    /**
     * 交换机
     */
    public final static String COLLECT_EXCAHGNE = "collect.topic";
    /**
     * 监听新增和修改的队列
     */
    public final static String COLLECT_INSERT_QUEUE = "collect.insert.queue";

    /**
     * 监听删除的队列
     */
    public final static String COLLECT_DELETE_QUEUE = "collect.delete.queue";

    /**
     * 新增或修改的RoutingKey
     */
    public final static String COLLECT_INSERT_KEY = "collect.insert";

    /**
     * 删除的RoutingKey
     */
    public final static String COLLECT_DELETE_KEY = "collect.delete";

//————————————————————————————————————————————————————tb_disable_date_user_collect  end———————————————————————————————————————————————————————————


}
