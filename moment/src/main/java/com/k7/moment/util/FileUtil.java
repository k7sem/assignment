package com.k7.moment.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @Author Kevin
 * @Since 08/16/2018
 *
 */

public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
