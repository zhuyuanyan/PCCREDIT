package com.cardpay.pccredit.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接工具类
 * 
 * ConnUtils类声明为final类说明此类不可以被继承
 * 
 * @author hejia
 * 
 */
public final class ConnUtils {
    private static String url = "jdbc:oracle:thin:@11.23.11.25:1521/odsptdb";
    private static String user = "qkcompany";
    private static String password = "qk888888";

//  private static String url = "jdbc:oracle:thin:@11.23.11.35:1521/xfxd";
//  private static String user = "pccredit";
//  private static String password = "pccredit";
    /**
     * 说明要访问此类只能通过static或单例模式
     */
    private ConnUtils() {
    }

    // 注册驱动 (只做一次)
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 获取Connection对象
     * 
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 释放资源
     * 
     * @param rs
     * @param st
     * @param conn
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}