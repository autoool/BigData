package itstaredu.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sam
 * 2019/1/30
 */
public class WordcountBolt extends BaseRichBolt {

    private OutputCollector collector;
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    //累加求和
    public void execute(Tuple tuple) {
        //获取数据
        String str = tuple.getStringByField("word");
        Integer count = tuple.getIntegerByField("sum");
        //业务处理
        if (map.containsKey(str)) {
            Integer sum = map.get(str);
            map.put(str, sum + count);
        } else {
            map.put(str, count);
        }
        System.err.println("当前数据统计 " + str + "个数 " + map.get(str));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
