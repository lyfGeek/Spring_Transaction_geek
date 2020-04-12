package com.geek.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ；连接工具类。用于从数据库中获取一个连接，并且实现和线程的绑定。
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接。
     *
     * @return
     */
    public Connection getThreadConnection() {

        try {
            // 先从 ThreadLocal 中获取。
            Connection connection = threadLocal.get();
            // 判断当前线程上是否有连接。
            if (connection == null) {
                // 从数据源中获取一个连接，并且写入 ThreadLocal 中。
                connection = dataSource.getConnection();

                threadLocal.set(connection);
            }

            // 返回当前线程的上的连接。
            return connection;

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑。
     */
    public void removeConnection() {
        threadLocal.remove();
    }
}
