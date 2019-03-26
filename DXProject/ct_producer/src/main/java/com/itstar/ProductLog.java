package com.itstar;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sam
 * 2019/3/26
 */
public class ProductLog {

    private List<String> phoneList = new ArrayList<>();
    private Map<String, String> phoneNameMap = new HashMap<>();
    private String startTime = "2018-01-01";
    private String endTime = "2018-12-31";

    private void initPhone() {
        phoneList.add("17078388295");
        phoneList.add("13980337439");
        phoneList.add("14575535933");
        phoneList.add("19902496992");
        phoneList.add("18549641558");
        phoneList.add("17005930322");
        phoneList.add("18468618874");
        phoneList.add("18576581848");
        phoneList.add("15978226424");
        phoneList.add("15542823911");
        phoneList.add("17526304161");
        phoneList.add("15422018558");
        phoneList.add("17269452013");
        phoneList.add("17764278604");
        phoneList.add("15711910344");
        phoneList.add("15714728273");
        phoneList.add("16061028454");
        phoneList.add("16264433631");
        phoneList.add("17601615878");
        phoneList.add("15897468949");

        phoneNameMap.put("17078388295", "李雁");
        phoneNameMap.put("13980337439", "卫艺");
        phoneNameMap.put("14575535933", "仰莉");
        phoneNameMap.put("19902496992", "陶欣悦");
        phoneNameMap.put("18549641558", "施梅梅");
        phoneNameMap.put("17005930322", "金虹霖");
        phoneNameMap.put("18468618874", "魏明艳");
        phoneNameMap.put("18576581848", "华贞");
        phoneNameMap.put("15978226424", "华啟倩");
        phoneNameMap.put("15542823911", "仲采绿");
        phoneNameMap.put("17526304161", "卫丹");
        phoneNameMap.put("15422018558", "戚丽红");
        phoneNameMap.put("17269452013", "何翠柔");
        phoneNameMap.put("17764278604", "钱溶艳");
        phoneNameMap.put("15711910344", "钱琳");
        phoneNameMap.put("15714728273", "缪静欣");
        phoneNameMap.put("16061028454", "焦秋菊");
        phoneNameMap.put("16264433631", "吕访琴");
        phoneNameMap.put("17601615878", "沈丹");
        phoneNameMap.put("15897468949", "褚美丽");
    }

    /**
     * 数据生成对应记录
     *
     * @return
     */
    public String product() {
        String caller = null;
        String callee = null;
        String callerName = null;
        String calleeName = null;

        int callerIndex = (int) (Math.random() * phoneList.size());
        caller = phoneList.get(callerIndex);
        callerName = phoneNameMap.get(caller);

        while (true) {
            int calleeIndex = (int) (Math.random() * phoneList.size());
            callee = phoneList.get(calleeIndex);
            calleeName = phoneNameMap.get(callee);
            if (!caller.equals(callee)) {
                break;
            }
        }

        //随机通话时间
        String buildTime = randomBuildTime(startTime, endTime);
        DecimalFormat df = new DecimalFormat("0000");
        String duration = df.format((int) (30 * 60 * Math.random()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(caller + ",").append(callee + ",").append(buildTime + ",").append(duration);
        return stringBuilder.toString();
    }

    /**
     * 通话时间  2018-09-12 12:12:12
     */

    public String randomBuildTime(String startTime, String endTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
            Date startDate = dateFormat.parse(startTime);
            Date endDate = dateFormat.parse(endTime);
            if (endDate.getTime() <= startDate.getTime()) {
                return null;
            }

            //随机通话时间
            long randomTS = startDate.getTime() + (long) ((endDate.getTime() - startDate.getTime()) * Math.random());
            Date resultDate = new Date(randomTS);
            SimpleDateFormat resultFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            String resultTimeStr = resultFormat.format(resultDate);
            return resultTimeStr;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeLog(String filePath) {
        String log = "";
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath, true),"UTF-8");
            while (true) {
                log = product();
                System.out.println(log);
                outputStreamWriter.write(log + "\n");
                outputStreamWriter.flush();
                Thread.sleep(500);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        args = new String[]{"E:\\Personal\\BigData\\data-source\\caller.log"};

        if (args == null || args.length < 0) {
            System.out.println("没有路径");
            return;
        }
        ProductLog productLog = new ProductLog();
        productLog.initPhone();
        productLog.writeLog(args[0]);
    }
}
