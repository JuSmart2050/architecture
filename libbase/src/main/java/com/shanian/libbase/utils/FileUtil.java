package com.shanian.libbase.utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {

    public static String getSDPath(){
        String sdDir = "";
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        //判断sd卡是否存在
        if(sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "jetpack";//获取跟目录
        }
        return sdDir;
    }

    public static String getJsonData(String fileName) {
        String result = "";
        try {
            FileInputStream f = new FileInputStream(new File(getSDPath(), fileName));
            BufferedReader bis = new BufferedReader(new InputStreamReader(f));
            String line = "";
            while ((line = bis.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getJsonData(File file) {
        String result = "";
        try {
            FileInputStream f = new FileInputStream(file);
            BufferedReader bis = new BufferedReader(new InputStreamReader(f));
            String line = "";
            while ((line = bis.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
