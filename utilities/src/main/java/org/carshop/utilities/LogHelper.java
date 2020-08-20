package org.carshop.utilities;


import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LogHelper {


    public static String logPath = SettingHelper.getProperty("txtLogPath");

    public static void Log(String bizCode, String msg, String threadId) throws IOException {
        String p = getFile(bizCode, threadId);

        File file = new File(p);
        FileWriter writer = new FileWriter(file, true);
        writer.append(String.format("--------日志时间:%s---------", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        writer.append("\r\n");
        writer.append(msg);
        writer.append("\r\n");
        writer.append("--------日志结束---------");
        writer.append("\r\n");
        writer.flush();
        writer.close();
        org.springframework.core.io.FileSystemResource f = new org.springframework.core.io.FileSystemResource("");
        f.createRelative("");

    }


    private static String getFile(String bizCode, String threadId) throws IOException {

        String strDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String strHour = new SimpleDateFormat("HH").format(new Date());
        String filePath = filePath = logPath + "/" + bizCode + "/" + strDate;

        //上层文件夹创建 bizcode
        File dir = new File(logPath + "/" + bizCode);
        if (dir.exists() == false) {
            boolean d = dir.mkdir();
        }
        //下层文件夹创建, 日期
        dir = new File(filePath);
        if (dir.exists() == false) {
            boolean d = dir.mkdir();
        }

        String fileName = "";
        if (StringUtils.isEmpty(threadId)) {

            fileName = strHour + ".txt";
        } else {
            fileName = strHour + "_" + threadId + ".txt";
        }

        File file = new File(filePath + "/" + fileName);
        if (file.exists() == false) {
            file.createNewFile();
        }

        return filePath + "/" + fileName;
    }


}
