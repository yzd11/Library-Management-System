package service.dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import service.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.util.List;

public class BasicDao<T> {//泛型指定具体类型
    private QueryRunner qr = new QueryRunner();
    //开发常用的dml
    //update
    public int update(String sql,Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnetcion();
            int update = qr.update(connection,sql,parameters);
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    //返回多行对象
    public List<T> queryMulti(String sql,Class<T> tClass,Object... paraments){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnetcion();
            List<T> query = qr.query(connection, sql, new BeanListHandler<>(tClass), paraments);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    //返回单行结果
    public T querySingle(String sql,Class<T> tClass,Object... paraments){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnetcion();
            T query = qr.query(connection, sql, new BeanHandler<>(tClass), paraments);
            return query;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }
    //返回单行单列
    public T queryScalar(String sql,Class<T> tClass,Object... paraments){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnetcion();
            T query = qr.query(connection, sql, new ScalarHandler<>(), paraments);
            return query;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }
    }

}
