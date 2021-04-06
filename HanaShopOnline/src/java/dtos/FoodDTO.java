/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class FoodDTO implements Serializable {

    private int id, quantity, price;
    private String name, image, description, createDate, status, category;

    public FoodDTO() {
    }

    public FoodDTO(int id, int quantity, int price, String name, String image, String description, String createDate, String category) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.description = description;
        this.createDate = createDate;
        this.category = category;
    }
public FoodDTO(int id,int price, String name, String image, String description, String createDate, String category) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.description = description;
        this.createDate = createDate;
        this.category = category;
    }
    public FoodDTO(int id, int quantity, int price, String name, String image, String description, String createDate, String status, String category) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.image = image;
        this.description = description;
        this.createDate = createDate;
        this.status = status;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
