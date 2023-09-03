package com.test;

import com.jdbc.DBUtil;

import java.sql.Connection;

public class TestJDBC {
    public static void main(String[] args) {
        Connection conn = DBUtil.getConn();
        System.out.println(conn);
        String adminname= DBUtil.getScalar("select adminName from adminInfo where id=?", 58);
        System.out.println(adminname);
    }
}
