package com.life.admin.mq;

import com.life.admin.constants.MqConstants;
import com.life.admin.service.ICommentService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
/**
 * 用 Work queue（设置两个队），工作队列，可以提高消息处理速度，避免队列消息堆积
 */
@Component
public class CommentListener {

    @Autowired
    private ICommentService commentService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 异步处理&MQ监听整合
     * @param message
     */
    //监听 动态的 新增 or 修改 的业务
    //定义消费者
    //根据commenId找出评论并新增到索引库
    @RabbitListener(queues = MqConstants.COMMENT_INSERT_QUEUE)
    @Async
    public void listenCommentInsertOrUpdate1(String message) {          //队列1
        System.out.println("listenCommentInsertOrUpdate1");
        if (StringUtils.isNotBlank( message )){
            commentService.insertCommentToIndexById(message);
            System.out.println("-----------【队列1】COMMENT_INSERT_QUEUE 根据commenId找出评论并新增到索引库--->" + LocalTime.now());
            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.COMMENT_INSERT_QUEUE,message);
        }
    }

    @RabbitListener(queues = MqConstants.COMMENT_INSERT_QUEUE)
    @Async
    public void listenCommentInsertOrUpdate2(String message) {          //队列2
        if (StringUtils.isNotBlank( message )){
            commentService.insertCommentToIndexById(message);
            System.out.println("-----------【队列2】COMMENT_INSERT_QUEUE 根据commenId找出评论并新增到索引库--->" + LocalTime.now());
            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.COMMENT_INSERT_QUEUE,message);
        }
    }

    /**
     *
     String index = "tb_disable_date_diary_reply";
     String name = "diaryId";

     List<Comment> commentList = commentService.getCommentByDiayId(diary);
     index = "tb_disable_date_diary_reply";
     name = "diaryId";
     */

    /**
     * 监听 动态的 删除 的业务
     * @param
     */
    @RabbitListener(queues = MqConstants.COMMENT_DELETE_QUEUE)
    @Async
    public void listenDiaryDeleteOrUpdate1(String message){
        if (StringUtils.isNotBlank( message )){
            commentService.deleteCommentsFromIndexById(message);
            System.out.println("-----------【队列1】 COMMENT_DELETE_QUEUE --->" + LocalTime.now());
            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.COMMENT_DELETE_QUEUE,message);
        }
    }

    @RabbitListener(queues = MqConstants.COMMENT_DELETE_QUEUE)
    @Async
    public void listenDiaryDeleteOrUpdate2(String message){
        if (StringUtils.isNotBlank( message )){
            commentService.deleteCommentsFromIndexById(message);
            System.out.println("-----------【队列2】 COMMENT_DELETE_QUEUE --->" + LocalTime.now());
            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.COMMENT_DELETE_QUEUE,message);
        }
    }



}
