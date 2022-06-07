package cn.qiuwanchi.cms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils {

    public static void copy(File sourceFile, File targetFile){
        FileInputStream fileInputStream = null;

        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(sourceFile);
            fileOutputStream = new FileOutputStream(targetFile);

            byte[] buf = new byte[1024 * 1024];
            int count = 0;
            while((count = fileInputStream.read(buf)) != -1){

                fileOutputStream.write(buf, 0, count);
            }
        }catch (Exception e){
            System.out.println("复制文件失败:" + e.toString());
        }
        finally {

            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                }catch (Exception e){
                    System.out.println("关闭fileOutputStream失败:" + e.toString());
                }

            }
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                }catch (Exception e){
                    System.out.println("关闭fileInputStream失败:" + e.toString());
                }

            }
        }
    }

}
