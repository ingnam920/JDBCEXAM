package org.example;

import java.sql.*;

//Connection 객체를 생성
public class SelectAllTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exampledb?useUnicode=true&serverTimezone=Asia/Seoul",
                            "kshstory",
                            "u1234");
//DBMS에 접속을하고 DBMS의 연결을 다루는 커넥션 인터페이스를 구현한 객체를 리턴해준다
            // Do something with the Connection
            if(conn!=null){
                System.out.println("연결성공");
                System.out.println(conn.getClass().getName());
            }
            //준비한 것을 참조하도록 PreparedStatement를 반환
            ps=conn.prepareStatement("select deptno,dname from dept");
            // select문의 실행되면 실행된 결과는 dbms에 있다.
            // 실행된 결과를 resultset이 참조
            rs= ps.executeQuery();
            //한줄을 가지고온다 next로
            while(rs.next()){
                int deptid = rs.getInt("deptno");
                String dname = rs.getString("dname");
                System.out.println(deptid+dname);
            }
            int updatecount = ps.executeUpdate();
            System.out.println("수정개수"+updatecount);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            try {
                //rs 자원 해제
                if(rs!=null)
                    rs.close();
                //ps도 자원해제필요
                if(ps!=null)
                    ps.close();
                if(conn!=null){
                conn.close();}
            } catch (SQLException e) {
                System.out.println(""+e.getMessage());
            }

        }
    }
}
