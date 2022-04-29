package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.Attachment;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.utils.FileConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Log4j2
@Controller
public class VideoController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("/video/{fileId}.mp4")
    public void getVideo(HttpServletRequest request,HttpServletResponse response,@PathVariable("fileId") String fileId){
        //视频资源存储信息
        Attachment attachment = attachmentService.getByFileId(fileId);
        response.reset();
        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");

        //打开本地文件流
        String filePath = fileConfiguration.getResourceDir() + "uploads/" + attachment.getFilepath();
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
                String message = "file:"+attachment.getFilename()+" not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }

            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

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
