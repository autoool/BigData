package com.itstaredu.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author sam
 * 2019/2/14
 */
public class SocketWordCount {

    public static void main(String[] args) {
        //创建连接端口
        final int port = 7777;
//创建执行环境对象
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //得到套接字对象 指定：主机 端口 分隔符
        DataStreamSource<String> text = env.socketTextStream("192.168.243.129", port, "\n");
        //解析数据
        DataStream<WordWithCount> windowCount =
                text.flatMap(new FlatMapFunction<String, WordWithCount>() {
                    public void flatMap(String s, Collector<WordWithCount> collector) throws Exception {
                        for (String word : s.split("\\s")) {
                            collector.collect(new WordWithCount(word, 1L));
                        }
                    }
                }).keyBy("word")
                        .timeWindow(Time.seconds(5), Time.seconds(1)) //设置窗口长度 5s一次  1s计算一次
                        .reduce(new ReduceFunction<WordWithCount>() {
                            public WordWithCount reduce(WordWithCount arg1, WordWithCount arg2) throws Exception {
                                //按照key进行聚合
                                return new WordWithCount(arg1.word, arg1.count + arg2.count);
                            }
                        });
        //打印并设置并发度
        windowCount.print().setParallelism(1);
        //执行程序
        try {
            env.execute("SocketwindowWordCount");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
