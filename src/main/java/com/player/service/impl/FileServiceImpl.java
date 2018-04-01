package com.player.service.impl;

import com.google.common.collect.Lists;
import com.player.service.FileService;
import com.player.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){

        //拿到上传文件的原始文件名
        String filename = file.getOriginalFilename();
        //拓展名
        //abc.jpg --> jpg
        String fileExtensionName = filename.substring(filename.lastIndexOf(".")+1);
        //上传文件的最终名字
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件名：{},上传路径：{}，新文件：{}",filename,path,uploadFileName);

        //获取路径下的文件夹
        File fileDir = new File(path);
        //如果该文件夹不存在则创造该文件夹
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        //该文件夹下的文件
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到服务器
            //删除本地文件
           targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件失败");
            return null;
        }
    return targetFile.getName();
    }
}
