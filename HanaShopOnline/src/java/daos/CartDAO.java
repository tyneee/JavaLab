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
import dtos.CartDTO;
import dtos.OrderDTO;

/**
 *
 * @author DELL
 */
public class CartDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public CartDAO() {
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

    public boolean insertOrder(int id,String userID) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into [Order](ID, UserId, Status) values(?,?,'Active')";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, userID);

            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDTO> loadIdOrder() throws Exception {
        int id = 0;
        List<OrderDTO> result = null;
        OrderDTO dto = null;
        try {
            String sql = "select ID from [Order]";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                id = rs.getInt("ID");
                dto = new OrderDTO(id);
                result.add(dto);
            }
            
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertOrderDetail(int orderId, String foodId, int quantity) throws Exception {
        boolean check = false;
        int idF = Integer.parseInt(foodId);
        try {
            String sql = "Insert into Order_Detail(OrderID, FoodID, Quantity) values(?,?,?)";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, orderId);
            pre.setInt(2, idF);
            pre.setInt(3, quantity);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public List<CartDTO> loadHisoryOfUser(String user)throws Exception{
        List<CartDTO> result = null;
        CartDTO dto = null;
        String name = null;
        int price = 0;
        int quan =0;
        String date = null;
        try{
            String sql ="Select f.Name as NameF,f.Price as PriceF, od.Quantity as QuantityF, o.DateOrder as Date From [Order] as o, Order_Detail as od , Food as f , Account as ac "
                    + "Where o.ID = od.OrderID and o.UserId like ? and f.ID = od.FoodID and o.UserId = ac.UserID";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+ user+"%");
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                name = rs.getString("NameF");
                price = rs.getInt("PriceF");
                quan = rs.getInt("QuantityF");
                date = rs.getString("Date");
                dto = new CartDTO(quan, price, name, date);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public List<CartDTO> searchHistoryByName(String user, String name)throws Exception{
        List<CartDTO> result = null;
        CartDTO dto = null;
        int price = 0;
        int quan =0;
        String date = null;
        try{
            String sql ="Select f.Name as NameF,f.Price as PriceF, od.Quantity as QuantityF, o.DateOrder as Date From [Order] as o, Order_Detail as od , Food as f , Account as ac "
                    + "Where o.ID = od.OrderID and o.UserId like ? and f.ID = od.FoodID and o.UserId = ac.UserID and f.Name like ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+ user+"%");
            pre.setString(2, "%"+ name+"%");
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                name = rs.getString("NameF");
                price = rs.getInt("PriceF");
                quan = rs.getInt("QuantityF");
                date = rs.getString("Date");
                dto = new CartDTO(quan, price, name, date);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public List<CartDTO> searchHistoryByDate(String user, String date)throws Exception{
        List<CartDTO> result = null;
        CartDTO dto = null;
        int price = 0;
        String name = null;
        String dateO = null;
        int quan =0;
        try{
            String sql ="Select f.Name as NameF,f.Price as PriceF, od.Quantity as QuantityF, o.DateOrder as Date "
                    + "From [Order] as o, Order_Detail as od , Food as f , Account as ac "
                    + "Where o.ID = od.OrderID and o.UserId like ? and f.ID = od.FoodID and o.UserId = ac.UserID "
                    + "and o.DateOrder BETWEEN ? AND ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+ user+"%");
            pre.setString(2, date +" 00:00:00");
            pre.setString(3, date + " 23:59:59");
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                name = rs.getString("NameF");
                price = rs.getInt("PriceF");
                quan = rs.getInt("QuantityF");
                dateO = rs.getString("Date");
                dto = new CartDTO(quan, price, name, dateO);
                result.add(dto);
            }
        }finally{
            closeConnection();
        }
        return result;
    }
}
