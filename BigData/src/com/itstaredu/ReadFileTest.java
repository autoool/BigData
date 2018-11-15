package com.itstaredu;

import com.google.gson.Gson;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sam
 * 2018/11/12
 */
public class ReadFileTest {

    public static void main(String[] args) {
        String path = "E:\\CompanyProject\\project_document\\2018宝格丽\\礼品卡\\接口文档\\上线数据\\产品sku.txt";
        readTxtFile(path);
    }

    public static void readTxtFile(String filePath) {
        try {
            String strs = "";
            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    strs += "\"" + lineTxt + "\"" + ",";
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
            if (strs.length() > 0) {
                strs = strs.substring(0, strs.length() - 1);
            }
            Gson gson = new Gson();
            System.out.println(strs);
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}
