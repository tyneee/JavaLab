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
import dtos.FoodDTO;

/**
 *
 * @author DELL
 */
public class FoodDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

    public FoodDAO() {
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

    public int countRowActive() throws Exception {
        int result = 0;
        try {
            String sql = "Select count(ID) as total from Food Where Status = 'Active'";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> loadListSearch(int currentPage, int next) throws Exception {
        List<FoodDTO> result = null;
        FoodDTO dto = null;
        int id = 0;
        int price = 0;
        int quantity = 0;
        String cate = null;
        String name = null, image = null, description = null, createDate = null;
        try {
            String sql = "Select f.ID, f.Name, f.Image, f.Description, f.Price, f.Quantity, f.CreateDate, ct.Name as CategoryName "
                    + "From Food as f, Category as ct Where f.Category = ct.ID and Status = 'Active' and Quantity > 0 "
                    + "order by CreateDate OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, (currentPage - 1) * next);
            pre.setInt(2, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                price = rs.getInt("Price");
                createDate = rs.getString("CreateDate");
                cate = rs.getString("CategoryName");
                dto = new FoodDTO(id, quantity, price, name, image, description, createDate, cate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countSearchTotalPage(String searchValue) throws Exception {
        int result = 0;
        try {
            String sql = "Select count(ID) as total from Food where Name like ? or Category like ? and Status = 'Active' and Quantity > 0";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            pre.setString(2, "%" + searchValue + "%");
            rs = pre.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> searchFood(String searchValue, int currentPage, int next) throws Exception {
        List<FoodDTO> result = null;
        FoodDTO dto = null;
        int id = 0;
        int price = 0;
        String name = null, image = null, description = null, createDate = null;
        String category = null;
        try {
            String sql = "Select f.ID, f.Name, f.Image, f.Description, f.Price, f.CreateDate, ct.Name as CategoryName From Food as f, Category as ct "
                    + "Where f.Category = ct.ID and f.Name like ? and f.Status = 'Active' and f.Quantity > 0 "
                    + "order by CreateDate OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchValue + "%");
            pre.setInt(2, (currentPage - 1) * next);
            pre.setInt(3, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getInt("Price");
                createDate = rs.getString("CreateDate");
                category = rs.getString("CategoryName");
                dto = new FoodDTO(id, price, name, image, description, createDate, category);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countSearchTotalByPrice(int from, int to) throws Exception {
        int result = 0;
        try {
            String sql = "Select count(ID) as total from Food where Price between ? and ? and Status = 'Active' and Quantity > 0";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, from);
            pre.setInt(2, to);
            rs = pre.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> searchFoodByPrice(int from, int to, int currentPage, int next) throws Exception {
        List<FoodDTO> result = null;
        FoodDTO dto = null;
        int id = 0;
        int price = 0;
        String name = null, image = null, description = null, createDate = null;
        String category = null;
        try {
            String sql = "Select f.ID, f.Name, f.Image, f.Description, f.Price, f.CreateDate, ct.Name as CategoryName From Food as f, Category as ct "
                    + "Where f.Category = ct.ID and Price between ? and ? and Status = 'Active' and Quantity > 0 "
                    + "order by CreateDate OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, from);
            pre.setInt(2, to);
            pre.setInt(3, (currentPage - 1) * next);
            pre.setInt(4, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getInt("Price");
                createDate = rs.getString("CreateDate");
                category = rs.getString("CategoryName");
                dto = new FoodDTO(id, price, name, image, description, createDate, category);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countSearchTotalByCate(int cate) throws Exception {
        int result = 0;
        try {
            String sql = "Select count(ID) as total from Food where Category = ? and Status = 'Active' and Quantity > 0";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cate);
            rs = pre.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> searchFoodByCate(int cate, int currentPage, int next) throws Exception {
        List<FoodDTO> result = null;
        FoodDTO dto = null;
        int id = 0;
        int price = 0;
        String name = null, image = null, description = null, createDate = null;
        String category = null;
        try {
            String sql = "Select f.ID, f.Name, f.Image, f.Description, f.Price, f.CreateDate, ct.Name as CategoryName From Food as f, Category as ct "
                    + "Where f.Category = ct.ID and ct.ID = ? and Status = 'Active' and Quantity > 0 "
                    + "order by CreateDate OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, cate);
            pre.setInt(2, (currentPage - 1) * next);
            pre.setInt(3, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getInt("Price");
                createDate = rs.getString("CreateDate");
                category = rs.getString("CategoryName");
                dto = new FoodDTO(id, price, name, image, description, createDate, category);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertFood(String name, String image, String description, String quantity, String price, int category) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Food(Name, Image, Description, Quantity , Price, Status, Category) values(?,?,?,?,?,'Active',?)";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, image);
            pre.setString(3, description);
            pre.setString(4, quantity);
            pre.setString(5, price);
            pre.setInt(6, category);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateFood(int id, String name, String image, String description, String quantity, String price, int category, String status) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Food set Name = ?, Image = ?, Description = ?, Quantity = ?, Price = ?, Category = ?, Status = ?, CreateDate = getdate() Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, image);
            pre.setString(3, description);
            pre.setString(4, quantity);
            pre.setString(5, price);
            pre.setInt(6, category);
            pre.setString(7, status);
            pre.setInt(8, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean updateFoodNoImage(int id, String name, String description, String quantity, String price, int category, String status) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Food set Name = ?, Description = ?, Quantity = ?, Price = ?, Category = ?, Status = ?, CreateDate = getdate() Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, description);
            pre.setString(3, quantity);
            pre.setString(4, price);
            pre.setInt(5, category);
            pre.setString(6, status);
            pre.setInt(7, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateStatusFood(int id, String status) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Food set Status = ?, CreateDate = getdate() Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, status);
            pre.setInt(2, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int countRowAll() throws Exception {
        int result = 0;
        try {
            String sql = "Select count(ID) as total from Food";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<FoodDTO> loadListAdmin(int currentPage, int next) throws Exception {
        List<FoodDTO> result = null;
        FoodDTO dto = null;
        int id = 0;
        int price = 0;
        int quantity = 0;
        String cate = null;
        String name = null, image = null, description = null, createDate = null, status = null;
        try {
            String sql = "Select f.ID, f.Name, f.Image, f.Description, f.Price, f.Quantity, f.CreateDate, ct.Name as CategoryName, f.Status "
                    + "From Food as f, Category as ct Where f.Category = ct.ID "
                    + "order by CreateDate OFFSET ? ROWS FETCH NEXT ? ROW ONLY";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, (currentPage - 1) * next);
            pre.setInt(2, next);
            rs = pre.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                image = rs.getString("Image");
                description = rs.getString("Description");
                price = rs.getInt("Price");
                quantity = rs.getInt("Quantity");
                createDate = rs.getString("CreateDate");
                cate = rs.getString("CategoryName");
                status = rs.getString("Status");
                dto = new FoodDTO(id, quantity, price, name, image, description, createDate, status, cate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteByID(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Food set Status = 'Inactive' Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean updateQuantityProduct(int quantity,int id) throws Exception {
        boolean check = false;
        try {
            String sql = "Update Food set Quantity = ?, CreateDate = getdate() Where ID = ?";
            conn = MyConnection.getMyConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
