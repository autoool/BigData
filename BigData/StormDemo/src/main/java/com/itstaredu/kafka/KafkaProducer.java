package com.itstaredu.kafka;


import java.util.Properties;

/**
 * @author sam
 * 2019/1/28
 */
public class KafkaProducer {


    private static String topic = "test";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.243.129:9092");
        //发送消息是否应答
        props.put("acks", "all");
        //配置发送消息失败重试
        props.put("retries", 0);
        //配置批处理消息大小
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("metadata.broker.list", 33554432);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
//        for (int i = 0; i < 1000; i++) {
//            producer.send(new KeyedMessage<String, String>(topic, i + " ", "appid " + UUID.randomUUID() + "itcast"));
//        }
//        System.out.println("run producer");
    }

//    private KafkaProducer() {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.243.129:9092,192.168.243.130:9092，192.168.243.131:9092");
//        props.put("acks", "all");
//        props.put("retries", 3);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producer = new Producer<String, String>(new ProducerConfig(props));
//
//    }
}
