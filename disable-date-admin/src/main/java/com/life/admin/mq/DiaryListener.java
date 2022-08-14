package com.life.admin.mq;

import com.life.admin.service.IDiaryService;
import com.life.admin.constants.MqConstants;
import com.life.admin.service.IPictureService;
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
public class DiaryListener {

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private IPictureService pictureService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//—————————————————————————————————————————————————————监听 diary 动态 ——————————————————————————————————————————————————————
    /**
     * 监听 动态的 新增 or 修改 的业务
     * @param message
     */
//    //定义消费者
    @RabbitListener(queues = MqConstants.DIARY_INSERT_QUEUE)
    @Async
    public void listenDisableInsertOrUpdate1(String message) {
        System.out.println("listenDisableInsertOrUpdate1");
        if (StringUtils.isNotBlank( message )){
            diaryService.insertDiaryToIndexById(message);
            System.out.println("-----------【队列1】 DIARY_INSERT_QUEUE 同步新增 or 修改了--->" + LocalTime.now());
            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.DIARY_INSERT_QUEUE,message);
        }
    }

    @RabbitListener(queues = MqConstants.DIARY_INSERT_QUEUE)
    @Async
    public void listenDisableInsertOrUpdate2(String message) {
        System.out.println("listenDisableInsertOrUpdate2");
        if (StringUtils.isNotBlank( message )){
            diaryService.insertDiaryToIndexById(message);
            System.out.println("-----------【队列2】 DIARY_INSERT_QUEUE 同步新增 or 修改了--->" + LocalTime.now());
            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.DIARY_INSERT_QUEUE,message);
        }
    }



//    /**
//     * 监听 动态的 删除 的业务
//     * @param message
//     */
//    @RabbitListener(queues = MqConstants.DIARY_DELETE_QUEUE)
//    @Async
//    public void listenDiaryDeleteOrUpdate1(String message){
//        if (StringUtils.isNotBlank( message )){
////            diaryService.deleteDiaryFromIndexById(message);
//            System.out.println("-----------【队列1】 DIARY_DELETE_QUEUE --->" + LocalTime.now());
//            logger.info("Token时效队列 监听1（String） {} 中消息： {}",MqConstants.DIARY_DELETE_QUEUE,message);
//        }
//    }
//
//    @RabbitListener(queues = MqConstants.DIARY_DELETE_QUEUE)
//    @Async
//    public void listenDiaryDeleteOrUpdate2(String message){
//        if (StringUtils.isNotBlank( message )){
////            diaryService.deleteDiaryFromIndexById(message);
//            System.out.println("-----------【队列2】 DIARY_DELETE_QUEUE --->" + LocalTime.now());
//            logger.info("Token时效队列 监听2（String） {} 中消息： {}",MqConstants.DIARY_DELETE_QUEUE,message);
//        }
//    }

//————————————————————————————————————————————————————监听 diary 动态 ——————————————————————————————————————————————————————————


}
