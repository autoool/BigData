package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author sam
 * 2019/3/28
 * 1、NameSPace 命名空间
 * 2、createTable 创建表
 * 3、isTable  判断表是否存在
 * 4、Region  RowKey
 */
public class HbaseUtil {

    //初始化命名空间
    public static void initNameSpace(Configuration configuration, String name) throws Exception {
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();
        //命名空间描述器
        NamespaceDescriptor descriptor = NamespaceDescriptor.create(name)
                .addConfiguration("author", "sam")
                .build();
        admin.createNamespace(descriptor);
        close(admin, connection);
    }

    //关闭
    private static void close(Admin admin, Connection connection) throws Exception {
        if (admin != null) {
            admin.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 创建表
     *
     * @param conf
     * @param tableName
     * @param regions
     * @param columnFamily
     */

    public static void createTable(Configuration conf, String tableName, int regions, String... columnFamily) throws Exception {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        if (isExistTable(conf, tableName)) {
            return;
        }
        //表描述器
        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
        for (String cf : columnFamily) {
            //列表述器
            htd.addFamily(new HColumnDescriptor(cf));
        }
        admin.createTable(htd, genSplitKeys(regions));
        close(admin, connection);
    }

    /**
     * 创建分区键
     *
     * @param regions
     * @return
     */
    private static byte[][] genSplitKeys(int regions) {
        //存放分区键的数组
        String[] keys = new String[regions];
        //格式化分区键
        DecimalFormat df = new DecimalFormat("00");
        for (int i = 0; i < regions; i++) {
            keys[i] = df.format(i) + "|";
        }
        byte[][] splitKeys = new byte[regions][];
        //排序
        TreeSet<byte[]> treeSet = new TreeSet<>(Bytes.BYTES_COMPARATOR);
        for (int i = 0; i < regions; i++) {
            treeSet.add(Bytes.toBytes(keys[i]));
        }
        Iterator<byte[]> iterator = treeSet.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            byte[] next = iterator.next();
            splitKeys[index++] = next;
        }
        return splitKeys;
    }

    /**
     * 判断表是否存在
     *
     * @param conf
     * @param tableName
     */
    public static boolean isExistTable(Configuration conf, String tableName) throws Exception {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        boolean result = admin.tableExists(TableName.valueOf(tableName));
        close(admin, connection);
        return result;
    }

    /**
     * 生成分区键 param1_param2_
     *
     * @param regionCode
     * @param caller
     * @param buildTime
     * @param callee
     * @param flag
     * @param duration   通话持续时间
     * @return
     */
    public static String getRowKey(String regionCode, String caller, String buildTime, String callee, String flag, String duration) {
        StringBuilder sb = new StringBuilder();
        sb.append(regionCode + "_")
                .append(caller + "_")
                .append(buildTime + "_")
                .append(callee + "_")
                .append(flag + "_")
                .append(duration + "_");
        return sb.toString();
    }

    /**
     * 生成分区号
     *
     * @param caller
     * @param buildTime
     * @param regions
     * @return
     */
    public static String getRegionCode(String caller, String buildTime, int regions) {
        int len = caller.length();
        String lastPhone = caller.substring(len - 4);
        String ym = buildTime.replaceAll("-", "")
                .replaceAll(":", "")
                .replaceAll(" ", "")
                .substring(0, 6);
        Integer x = Integer.valueOf(lastPhone) ^ Integer.valueOf(ym);
        int y = x.hashCode();
        int regionCode = y % regions;
        DecimalFormat df = new DecimalFormat("00");
        return df.format(regionCode);
    }


}
