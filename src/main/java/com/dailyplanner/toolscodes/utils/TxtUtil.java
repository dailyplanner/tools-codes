package com.dailyplanner.toolscodes.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Component
@Log4j2
public class TxtUtil {

    public static String readTxt(File file) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s=br.readLine())!=null){
            content = content.append(s);
        }
        return content.toString();
    }

    public static void main(String[] args) {
        try {
            //通过绝对路径获取文件
            String s1  = TxtUtil.readTxt(new File("F:\\study\\fiddler.txt"));
            System.out.print(s1);

            //spring boot中文件直接放在resources目录下
            String s2 = TxtUtil.readTxt(ResourceUtils.getFile("classpath:du.txt"));
            System.out.print(s2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
