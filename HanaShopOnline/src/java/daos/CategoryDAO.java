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
import dtos.CategoryDTO;

/**
 *
 * @author DELL
 */
public class CategoryDAO implements Serializable{
     private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public CategoryDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    public List<CategoryDTO> loadNameCategory() throws Exception{
        String name = null;
        CategoryDTO dto = null;
        List<CategoryDTO> result = null;
        try{
            String sql = "Select Name From Category";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                name = rs.getString("Name");
                dto = new CategoryDTO(name);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public int loadId(String name )throws Exception{
        int id = 0;
        try{
            String sql ="select ID from Category where Name like ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+name+"%");
            rs = pre.executeQuery();
            if(rs.next()){
                id = rs.getInt("ID");
            }
        }finally{
            closeConnection();
        }
        return id;
    }
    }
