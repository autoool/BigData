package com.itstaredu;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sam
 * 2018/11/12
 */
public class ReadFileTest {

    public static void main(String[] args) {
//        String path = "E:\\CompanyProject\\ProjectDocument\\2019资生堂\\资生堂交接\\超卖\\test.txt";
//        String path = "E:\\CompanyProject\\ProjectDocument\\2019资生堂\\资生堂交接\\问题处理\\退款\\memberId.txt";
//        String path = "E:\\CompanyProject\\ProjectDocument\\2019资生堂\\资生堂交接\\问题处理\\测试环境下单\\正式冯雪\\fengxue_member_id.txt";
//        File file = new File(path);
//        readTxtFile(file);

        String dir = "C:\\Users\\sam\\Documents\\Tencent Files\\274676067\\FileRecv\\queue.tar\\queue";
        readTxtDir(dir);

//        String datas = "70577,142897,157706,161805,172308,172865,172906,173018,173071,173126,175882,175971,176014,176082,176274,176496,176523,176539,176552,176574,176592,176603,176641,176669,176696,178093,178428,276939,276946,277387,278276,278464,278985,279683,279798,282618,284274,286146,286150,286238,287350,288483,289364,289369,293478,294939,296089,301323,304827,309844,310137,311785,311823,312195,312246,312472,312630,312890,312962,313526,314284,314697,314818,314928,317369,317644,320381,321632,324079,325030,325467,325506,325769,326949,327008,327170,328004,328571,329713,330134,331812,331922,332083,332125,334186,334292,335412,335771,337785,338588,339087,340196,340470,340724,340816,340836,341111,342439,342682,344684,344922,346794,347851,348512,348571,349446,349767,350135,350649,353011,353011,353011,354477,354640,354890,355290,355923,356975,359618,364146,364255,364523,364527,364560,365338,366804,371930,372080,372997,373566,375565,375600,375651,375875,375886,377245,377716,378257,378800,380025,383230,386883,388100,388818,388870,390517,390627,390793,390800,390841,390857,390901,390962,390993,391030,391032,391047,391112,391121,391123,391198,391203,391207,391211,391213,391285,391295,391327,391370,391379,391388,391407,391445,391449,391463,391486,391515,391518,391575,391590,391606,391609,391634,391827,391831,391847,391900,391912,391917,391921,391924,391980,391995,392005,392045,392049,392076,392100,392106,392120,392124,392125,392144,392159,392174,392183,392240,392283,392321,392363,392765,393609,394279,394576,394814,395628,396170,398275,398325,399042,400461,401666,402835,404151,404922,404989,408499,408760,408760,408840,408840,409349,409381,411423,413925";
//        String[] dataArr = datas.split(",");
//        System.out.println(dataArr.length);
    }

    public static void readTxtDir(String path){
        if (StringUtils.isEmpty(path)){
            System.out.println("目录不正确");
            return;
        }
        File fDir = new File(path);
        if (fDir.isFile()||!fDir.exists()){
            System.out.println("目录不正确");
            return;
        }
        File[] fList = fDir.listFiles();
        for (int i=0;i<fList.length;i++){
            if (fList[i].isFile()){
                readPhpFile(fList[i]);
            }
        }


    }


    public static void readPhpFile(File file) {
        try {
            String strs = "";
            String encoding = "utf-8";
            Map<String, String> mapData = new HashMap<>();
            List<String> multiData = new ArrayList<>();
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                System.out.println(file.getName());
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (lineTxt.contains("10302212")){
                        strs += lineTxt.trim() + ",";
                    }

//                    if (mapData.containsKey(lineTxt)) {
//                        multiData.add(lineTxt);
//
//                    } else {
//                    mapData.put(lineTxt, lineTxt);
//                    }
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
//            System.out.println(mapData.size());
//            System.out.println(multiData.size());
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
    public static void readTxtFile(File file) {
        try {
            String strs = "";
            String encoding = "utf-8";
            Map<String, String> mapData = new HashMap<>();
            List<String> multiData = new ArrayList<>();
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    strs += lineTxt.trim() + ",";
//                    if (mapData.containsKey(lineTxt)) {
//                        multiData.add(lineTxt);
//
//                    } else {
                    mapData.put(lineTxt, lineTxt);
//                    }
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
            System.out.println(mapData.size());
//            System.out.println(multiData.size());
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}
