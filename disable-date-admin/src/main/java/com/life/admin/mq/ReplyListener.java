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
public class ReplyListener {

    @Autowired
    private ICommentService commentService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //—————————————————————————————————————————————————————监听 reply  ——————————————————————————————————————————————————————
    /**
     * 监听 动态的 新增 or 修改 的业务
     * @param message == replyId
     */
    //定义消费者
    //根据replyId找出回复并新增到索引库
    @RabbitListener(queues = MqConstants.REPLY_INSERT_QUEUE)
    @Async
    public void listenReplyInsertOrUpdate1(String message) {
        if (StringUtils.isNotBlank( message )){
            commentService.insertReplyToIndexById(message);
            System.out.println("-----------【队列1】REPLY_INSERT_QUEUE 根据replyId找出评论回复并新增到索引库--->" + LocalTime.now());
            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.REPLY_INSERT_QUEUE,message);
            //            if (!this.jwtService.tokenValid( userId ))//更新Token时效
            //                throw new MyException( ExceptionEnum.EXCEPTION_ANALYSIS_TOKEN );
        }
    }

    @RabbitListener(queues = MqConstants.REPLY_INSERT_QUEUE)
    @Async
    public void listenReplyInsertOrUpdate2(String message) {
        if (StringUtils.isNotBlank( message )){
            commentService.insertReplyToIndexById(message);
            System.out.println("-----------【队列2】REPLY_INSERT_QUEUE 根据replyId找出评论回复并新增到索引库--->" + LocalTime.now());
            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.REPLY_INSERT_QUEUE,message);
        }
    }


    /**
     * 监听 动态的 删除 的业务
     */
    @RabbitListener(queues = MqConstants.REPLY_DELETE_QUEUE)
    @Async
    public void listenReplyDeleteOrUpdate1(String message){
        if (StringUtils.isNotBlank( message )){
            commentService.deleteRepliesFromIndexById(message);
            System.out.println("-----------【队列1】 REPLY_DELETE_QUEUE --->" + LocalTime.now());
            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.REPLY_DELETE_QUEUE,message);
        }
    }

    @RabbitListener(queues = MqConstants.REPLY_DELETE_QUEUE)
    @Async
    public void listenReplyDeleteOrUpdate2(String message){
        if (StringUtils.isNotBlank( message )){
            commentService.deleteRepliesFromIndexById(message);
            System.out.println("-----------【队列2】 REPLY_DELETE_QUEUE --->" + LocalTime.now());
            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.REPLY_DELETE_QUEUE,message);
        }
    }



}
