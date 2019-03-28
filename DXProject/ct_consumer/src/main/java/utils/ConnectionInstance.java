package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author sam
 * 2019/3/27
 */
public class ConnectionInstance {

    private static Connection connection;

    public static synchronized Connection getConnection(Configuration configuration) {
        try {
            if (null == connection || connection.isClosed()) {
                connection = ConnectionFactory.createConnection(configuration);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
