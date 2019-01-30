package itstaredu.storm;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author sam
 * 2019/1/29
 */
public class DataSourceSpout extends BaseRichSpout {

    private SpoutOutputCollector outputCollector;

    //创建收集器
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.outputCollector = spoutOutputCollector;
    }

    //发送数据
    public void nextTuple() {
        //发送数据
        outputCollector.emit(new Values("test message test message who an I"));
        //设置延迟
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //声明
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("testdata"));
    }
}
