package com.life.admin.Config;

import com.life.admin.constants.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
//——————————————————————————————————————————————diary————————————————————————————————————————————————————————————————————
    //实现交换机的定义
    @Bean
    public TopicExchange topicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.DIARY_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue insertQueue(){
        return new Queue(MqConstants.DIARY_INSERT_QUEUE,true);
    }

    @Bean
    public Queue deleteQueue(){
        return new Queue(MqConstants.DIARY_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding insertQueueBinding(){
//                            绑定     队列         到    交换机                        用的RoutingKey
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(MqConstants.DIARY_INSERT_KEY);
    }

    @Bean
    public Binding deleteQueueBinding(){
//                            绑定     队列         到    交换机                        用的RoutingKey
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(MqConstants.DIARY_DELETE_KEY);
    }
//——————————————————————————————————————————————diary end—————————————————————————————————————————————————————————————————


//——————————————————————————————————————————————picture ——————————————————————————————————————————————————————————————————

    //实现交换机的定义
    @Bean
    public TopicExchange pictureTopicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.PICTURE_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue pictureInsertQueue(){
        return new Queue(MqConstants.PICTURE_INSERT_QUEUE,true);
    }

    @Bean
    public Queue pictureDeleteQueue(){
        return new Queue(MqConstants.PICTURE_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding pictureInsertQueueBinding(){
        return BindingBuilder.bind(pictureInsertQueue()).to(pictureTopicExchange()).with(MqConstants.PICTURE_INSERT_KEY);
    }

    @Bean
    public Binding pictureDeleteQueueBinding(){
        return BindingBuilder.bind(pictureDeleteQueue()).to(pictureTopicExchange()).with(MqConstants.PICTURE_DELETE_KEY);
    }


//——————————————————————————————————————————————picture end——————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————comment ——————————————————————————————————————————————————————————————————

    //实现交换机的定义
    @Bean
    public TopicExchange commentTopicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.COMMENT_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue commentInsertQueue(){
        return new Queue(MqConstants.COMMENT_INSERT_QUEUE,true);
    }

    @Bean
    public Queue commentDeleteQueue(){
        return new Queue(MqConstants.COMMENT_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding commentInsertQueueBinding(){
        return BindingBuilder.bind(commentInsertQueue()).to(commentTopicExchange()).with(MqConstants.COMMENT_INSERT_KEY);
    }

    @Bean
    public Binding commentDeleteQueueBinding(){
        return BindingBuilder.bind(commentDeleteQueue()).to(commentTopicExchange()).with(MqConstants.COMMENT_DELETE_KEY);
    }


//——————————————————————————————————————————————comment end——————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————评论的回复 reply ——————————————————————————————————————————————————————————————————

    //实现交换机的定义
    @Bean
    public TopicExchange replyTopicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.REPLY_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue replyInsertQueue(){
        return new Queue(MqConstants.REPLY_INSERT_QUEUE,true);
    }

    @Bean
    public Queue replyDeleteQueue(){
        return new Queue(MqConstants.REPLY_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding replyInsertQueueBinding(){
        return BindingBuilder.bind(replyInsertQueue()).to(replyTopicExchange()).with(MqConstants.REPLY_INSERT_KEY);
    }

    @Bean
    public Binding replyDeleteQueueBinding(){
        return BindingBuilder.bind(replyDeleteQueue()).to(replyTopicExchange()).with(MqConstants.REPLY_DELETE_KEY);
    }


//——————————————————————————————————————————————评论的回复 reply end——————————————————————————————————————————————————————————————————

//——————————————————————————————————————————————tb_disable_users_like_videos ——————————————————————————————————————————————————————————————————

    //实现交换机的定义
    @Bean
    public TopicExchange diaryVideosTopicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.DIARYVIDEO_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue diaryVideosInsertQueue(){
        return new Queue(MqConstants.DIARYVIDEO_INSERT_QUEUE,true);
    }

    @Bean
    public Queue diaryVideosDeleteQueue(){
        return new Queue(MqConstants.DIARYVIDEO_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding diaryVideosInsertQueueBinding(){
        return BindingBuilder.bind(diaryVideosInsertQueue()).to(diaryVideosTopicExchange()).with(MqConstants.DIARYVIDEO_INSERT_KEY);
    }

    @Bean
    public Binding diaryVideosDeleteQueueBinding(){
        return BindingBuilder.bind(diaryVideosDeleteQueue()).to(diaryVideosTopicExchange()).with(MqConstants.DIARYVIDEO_DELETE_KEY);
    }


//——————————————————————————————————————————————评论的回复 reply end——————————————————————————————————————————————————————————————————

    //——————————————————————————————————————————————tb_disable_date_user_collect ——————————————————————————————————————————————————————————————————

    //实现交换机的定义
    @Bean
    public TopicExchange likeCollectTopicExchange(){       //             true:持久化
        return new TopicExchange(MqConstants.COLLECT_EXCAHGNE,true,false);
    }

    //定义两个队列
    @Bean
    public Queue likeCollectInsertQueue(){
        return new Queue(MqConstants.COLLECT_INSERT_QUEUE,true);
    }

    @Bean
    public Queue likeCollectDeleteQueue(){
        return new Queue(MqConstants.COLLECT_DELETE_QUEUE,true);
    }

    //定义绑定关系
    @Bean
    public Binding likeCollectInsertQueueBinding(){
        return BindingBuilder.bind(likeCollectInsertQueue()).to(likeCollectTopicExchange()).with(MqConstants.COLLECT_INSERT_KEY);
    }

    @Bean
    public Binding likeCollectDeleteQueueBinding(){
        return BindingBuilder.bind(likeCollectDeleteQueue()).to(likeCollectTopicExchange()).with(MqConstants.COLLECT_DELETE_KEY);
    }


//——————————————————————————————————————————————tb_disable_date_user_collect reply end——————————————————————————————————————————————————————————————————


}
