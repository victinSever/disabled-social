package com.life.admin.common;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class Save {

    /**
     * 保存图片到服务器
     * @param customaryFile 需要压缩的图片文件
     * @return   返回 照片 在服务器的存储路径
     */
    public String savePicture(File customaryFile ,String name ) throws IOException {
        String path = "D:\\Users\\Picture\\"+name;   //保存到硬盘的路径 （后面可能会因为实际情况会将其改为动态，设置参数传递获取）
        File compressAfter = new File(path);

        // scale是一个double类型的数字，缩放比例，大于1就是变大，小于1就是缩小
        //将图片按照原比例压缩到原来的0.7倍的内存大小
        Thumbnails.of(
                new FileInputStream(customaryFile))
                .scale(0.7)
                .toFile(compressAfter);
        System.out.println("保存成功");
        return path;
    }

    /**
     * 保存视频到服务器
     * @param file
     * @return  返回视频在服务器的存储路径
     */
    public String savevideo(File file){

        return "1";
    }



}
