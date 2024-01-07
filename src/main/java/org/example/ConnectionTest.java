package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//Connection 객체를 생성
public class ConnectionTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps =null;

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
            //insert into dept(deptno,dname,loc) values (값,'값','값');
            //conn야 지금 연결된 DBMS에 이 SQL을 준비해줘 . 그런데 물음표부분은 남겨놔
            //준비한 것을 참조하도록 PreparedStatement를 반환
            ps=conn.prepareStatement("insert into dept(deptno,dname,loc) values (?,?,?)");
            //바인딩까지 하면 sql 실행할 준비
            ps.setInt(1,4);
            ps.setString(2,"Role_TEST");
            ps.setString(3,"Daegi");
            //SQL실행 . excuteUpdate - insert , update delete ㄸ 사용 -> 건수가 리턴됨
            int updatecount = ps.executeUpdate();
            System.out.println("수정개수"+updatecount);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            try {
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
