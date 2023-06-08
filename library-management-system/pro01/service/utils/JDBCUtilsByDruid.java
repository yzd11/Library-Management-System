package service.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {
    private static DataSource ds;
//    在静态代码块完成ds初始化
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = JDBCUtilsByDruid.class.getResourceAsStream("/druid.properties");
            properties.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //编写connection方法
    public static Connection getConnetcion() throws Exception {
        Connection connection = ds.getConnection();
        return connection;
    }
    //关闭资源
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        //判读是否为空
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
