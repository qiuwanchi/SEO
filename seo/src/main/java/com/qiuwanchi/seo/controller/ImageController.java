package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Log4j2
@Controller
public class ImageController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    /**
     * image 或者视频的 网络全路径
     * @param fileId
     * @param request
     * @param response
     */
    @GetMapping("/image/{fileId}")
    public void image(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response){
        try {
            fileId = fileId.substring(0,fileId.indexOf("."));
            Attachment attachment = attachmentService.getByFileId(fileId);
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + attachment.getFilename());
            //打开本地文件流
            String filePath = fileConfiguration.getResourceDir() + "uploads/" + attachment.getFilepath();
            InputStream inputStream = new FileInputStream(filePath);
            //激活下载操作
            OutputStream os = response.getOutputStream();
            //循环写入输出流
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        }catch (Exception e) {
           log.error(e);
        }
    }

}
