//package com.itstaredu.kafka;
//
//import kafka.consumer.*;
//import kafka.javaapi.consumer.ConsumerConnector;
//import kafka.message.MessageAndMetadata;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author sam
// * 2019/1/28
// */
//public class KafkaConsumer implements Runnable {
//
//    public String title;
//    public KafkaStream<byte[], byte[]> stream;
//
//    public KafkaConsumer(String title, KafkaStream<byte[], byte[]> stream) {
//        this.title = title;
//        this.stream = stream;
//    }
//
//    public void run() {
//        System.out.println("开始运行 " + title);
//        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        while (it.hasNext()) {
//            MessageAndMetadata<byte[], byte[]> data = it.next();
//            String topic = data.topic();
//            int partition = data.partition();
//            long offset = data.offset();
//            String msg = new String(data.message());
//            System.out.println(String.format(
//                    "Consumer: [%s],  Topic: [%s],  PartitionId: [%d], Offset: [%d], msg: [%s]",
//                    title, topic, partition, offset, msg));
//        }
//        System.out.println(String.format("Consumer: [%s] exiting ...", title));
//    }
//
//    public static void main(String[] args) {
//        Properties properties = new Properties();
//        properties.put("group.id", "dashuju");
//        properties.put("zookeeper.connect", "hd01:2181,hd02:2181,hd03:2181");
//        properties.put("auto.offset.reset", "largest");
//        properties.put("auto.commit.interval.ms", "1000");
//        properties.put("partition.assignment.strategy", "roundrobin");
//        ConsumerConfig config = new ConsumerConfig(properties);
//        String topic1 = "orderMq";
//        String topic2 = "paymentMq";
//        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(config);
//        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//        Map<String, List<KafkaStream<byte[], byte[]>>> topicStreamMap
//                = consumerConnector.createMessageStreams(topicCountMap);
//        List<KafkaStream<byte[], byte[]>> streams = topicStreamMap
//                .get(topic1);
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < streams.size(); i++) {
//            executorService.execute(new KafkaConsumer("消费者 " + (i + 1), streams.get(i)));
//        }
//
//    }
//}
