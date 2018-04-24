package kr.co.bit.dao;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.GuestBookVO;
import org.springframework.stereotype.Repository;

import java.awt.color.CMMException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@Repository
public class GuestBookDAO {

    public void insert(GuestBookVO vo) {

        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "insert into GUESTBOOK (NO, NAME, PASSWORD, CONTENT, REG_DATE) values (seq.nextval,?,?,?,sysdate)";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,vo.getName());
            preparedStatement.setString(2,vo.getPassword());
            preparedStatement.setString(3,vo.getContent());
            System.out.println(vo.getName());
            System.out.println(vo.getPassword());
            System.out.println(vo.getContent());

            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("DB 삽입 성공");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,null);
        }

    }

    public boolean delete(int no, String password) {
        boolean flag = false;
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "delete from GUESTBOOK where NO='"+no+"'"+ "and password = '"+password+"'";
        try {
            preparedStatement = connection.prepareStatement(sql);

            int count = preparedStatement.executeUpdate();
            if(count>0){
                System.out.println("DB 게시물 삭제 성공");
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,null);
        }
        return flag;
    }

    public ArrayList<GuestBookVO> searchAll() {
        ArrayList<GuestBookVO> list = null;
        GuestBookVO vo = null;
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from GUESTBOOK order by no desc";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while(resultSet.next()) {
                vo = new GuestBookVO();

                vo.setNo(resultSet.getInt(1));
                vo.setName(resultSet.getString(2));
                vo.setPassword(resultSet.getString(3));
                vo.setContent(resultSet.getString(4));
                vo.setDate(resultSet.getString(5));

                list.add(vo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionManager.connectClose(connection,preparedStatement,resultSet);
        }
        return list;
    }
}
