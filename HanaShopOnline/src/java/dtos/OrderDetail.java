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
public class OrderDetail implements Serializable{
    private int idFood, idOrder, quantity;

    public OrderDetail() {
    }

    public OrderDetail(int idFood, int idOrder, int quantity) {
        this.idFood = idFood;
        this.idOrder = idOrder;
        this.quantity = quantity;
    }
    
    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
