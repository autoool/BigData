package com.itstaredu.ch08.workcount;

import com.itstaredu.util.LogUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author sam
 * 2018/10/28
 * 2004 google:dfs/bigtable/mapreduce
 * <p>
 * 用户输入数据
 * 用户处理的方式
 * 用户指定结果数据存储位置
 */
public class HdfsWordCount {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
            URISyntaxException, InterruptedException {

        Properties pro = new Properties();
        pro.load(HdfsWordCount.class.getClassLoader().getResourceAsStream("job.properties"));

        Path inpath = new Path(pro.getProperty("IN_PATH"));
        Path outpathFile = new Path(pro.getProperty("OUT_PATH"));

        Class<?> mapperClass = Class.forName(pro.getProperty("MAPPER_CLASS"));
        Mapper mapper = (Mapper) mapperClass.newInstance();
        Context context = new Context();

        //hdfs客户端
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.243.129:9000"), conf, "root");
        //读取用户输入文件
        RemoteIterator<LocatedFileStatus> status = fs.listFiles(inpath, false);
        while (status.hasNext()) {
            LocatedFileStatus file = status.next();
            FSDataInputStream in = fs.open(file.getPath());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                mapper.map(line, context);
            }
            bufferedReader.close();
            in.close();
        }
        Path outPath = new Path("/ws/out/");
        if (!fs.exists(outPath)) {
            fs.mkdirs(outPath);
        }

        //缓存结果放入hdfs
        HashMap<Object, Object> contextMap = context.getContextMap();
        FSDataOutputStream dataOutputStream = fs.create(outpathFile);
        Set<Map.Entry<Object, Object>> entries = contextMap.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            dataOutputStream.write((entry.getKey().toString() + "\t" + entry.getValue() + "\n").getBytes());
            LogUtils.log(entry.getKey().toString() + "\t" + entry.getValue() + "\n");
        }
        dataOutputStream.close();
        fs.close();
        LogUtils.log("数据统计完成");

    }
}
