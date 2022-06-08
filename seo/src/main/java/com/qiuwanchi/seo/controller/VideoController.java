package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class VideoController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * 获取视频流
     * @param response
     * @param fileId 视频存放信息索引
     * @return
     * @author xWang
     * @Date 2020-05-20
     */
    @GetMapping("/video/{fileId}")
    public void getVideo(HttpServletRequest request,HttpServletResponse response,@PathVariable("fileId") String fileId){
        fileId = fileId.substring(0,fileId.indexOf("."));
        //视频资源存储信息
        Attachment attachment = attachmentService.getByFileId(fileId);
        response.reset();
        //获取从那个字节开始读///取文件
        String rangeString = request.getHeader("Range");

        //打开本地文件流
        String filePath = fileConfiguration.getResourceDir() + File.separator + "video" + File.separator + attachment.getFilepath().substring(9);
        log.info("播放的文件路径: {}", filePath);
        try {
            //获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            File file = new File(filePath);
            if(file.exists()){
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();
                //播放
                if(rangeString != null){
                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    //设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    //设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    //设置此次相应返回的数据范围
                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
                    //返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    //设定文件读取开始位置（以字节为单位）
                    targetFile.seek(range);
                }else {//下载
                    log.info("下载文件存：{}", filePath);
                    //设置响应头，把文件名字设置好
                    response.setHeader("Content-Disposition", "attachment; filename="+attachment.getFilename() );
                    //设置文件长度
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    //解决编码问题
                    response.setHeader("Content-Type","application/octet-stream");
                }

                byte[] cache = new byte[1024 * 300];
                int flag;
                while ((flag = targetFile.read(cache))!=-1){
                    outputStream.write(cache, 0, flag);
                }
            }else {
                log.info("文件不存在：{}", filePath);
                String message = "file:"+attachment.getFilename()+" not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }

            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            log.error("文件没有找到",e);
        } catch (IOException e) {
            log.error("。。。。。。",e);
        }
    }
}
