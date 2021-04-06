/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dbs.MyConnection;
import dtos.RegistrationDTO;

/**
 *
 * @author DELL
 */
public class RegistrationDAO implements Serializable{
    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs ;

    public RegistrationDAO() {
    }
    
    private void closeConnection() throws Exception{
        if(rs != null){
            rs.close();
        }
        if(pre != null){
            pre.close();
        }
        if(conn != null){
            conn.close();
        }
    }
    public String checkLogin(String userId, String password) throws Exception{
        String role = "failed";
        try{
            String sql = "Select Role From Account Where UserID = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if(rs.next()){
                role = rs.getString("Role");
            }
        }finally{
            closeConnection();
        }
        return role;
    }
    public boolean insert(String userId, String name, String role) throws Exception{
        boolean check = false;
        try{
            String sql ="Insert  into Account(UserID,Name,Role) values(?,?,?)";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1,userId);
            pre.setString(2,name);
            pre.setString(3,role);
            check = pre.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public List<RegistrationDTO> loadUserIDGG() throws Exception{
        List<RegistrationDTO>  result = null;
        RegistrationDTO dto = null;
        String user = null;
        try{
            String sql = "Select UserID From Account";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            if(rs.next()){
                user = rs.getString("UserID");
                dto = new RegistrationDTO(user);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public String loadName(String userId) throws Exception{
        String name = "";
        try{
            String sql = "Select Name From Account Where UserID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, userId);
            rs = pre.executeQuery();
            if(rs.next()){
                name = rs.getString("Name");
            }
        }finally{
            closeConnection();
        }
        return name;
    }
}
