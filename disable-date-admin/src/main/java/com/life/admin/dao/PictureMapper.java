package com.life.admin.dao;

import com.life.admin.pojo.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PictureMapper extends BaseMapper<Picture>{

    /**
     *    `id` VARCHAR(64)  'id',
     *  *   `pic_name` VARCHAR(30) '照片文件的命名',
     *  *   `diary_id` VARCHAR(30) '图片的来源主键id，来源可以是动态 or 图片秀',    #用UUID来给唯一标识
     *  *   `user_id` BIGINT(0)                     '发布者id',
     *  *   `pic_path` VARCHAR(100) '视频存放的路径',
     *  *   `pic_width` INT(0)                      '图片宽度',
     *  *   `pic_height` INT(0)                     '图片高度',
     *  *   `like_counts` BIGINT(0)                 '喜欢/赞美的数量',
     *  *   `status` INT(0)                         '视频状态：1-发布成功 2-未过审，管理员操作',
     *  *   `create_time` DATETIME(0)               '创建时间',
     * @param diary_id
     * @param name
     * @param diary_user_id
     * @param pic_path
     * @param status
     */
    //添加照片到照片表
    @Insert("insert into tb_disable_diary_pictures(id,diary_id,pic_name,user_id,pic_path,status) " +
            "values (#{id},#{diary_id},#{name},#{diary_user_id},#{pic_path},#{status})")
    void insertPicture(@Param("id") Integer id, @Param("diary_id") String diary_id, @Param("name") String name, @Param("diary_user_id") String diary_user_id,
                       @Param("pic_path") String pic_path, @Param("status") Integer status);

    //根据动态dirayId找出所有的配图
    @Select("select * from tb_disable_diary_pictures WHERE diary_id = #{diaryId}")
    List<Picture> getAllPictureBydiaryId(String diaryId);

    //从数据库中删除动态的所有配图
    @Delete("delete from tb_disable_diary_pictures WHERE diary_id = #{diaryId}")
    void deletePictures(String diaryId);
}
