package itstaredu.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author sam
 * 2019/1/29
 * storm 词频统计
 */
public class StormWordDriver {

    public static void main(String[] args) {
        //创建拓扑
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //指定设置
        topologyBuilder.setSpout("DataSourceSpout", new DataSourceSpout(), 1);
        topologyBuilder.setBolt("WordCountSplitBolt", new WordCountSplitBolt(), 4).fieldsGrouping("DataSourceSpout", new Fields("testdata"));
        topologyBuilder.setBolt("WordcountBolt", new WordcountBolt(), 2).fieldsGrouping("WordCountSplitBolt", new Fields("word"));

        //创建配置信息
        Config config = new Config();
        //提交任务
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordcount", config, topologyBuilder.createTopology());

    }
}
