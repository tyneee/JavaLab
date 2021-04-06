/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daos.FoodDAO;
import dtos.CartDTO;
import dtos.FoodDTO;
import dtos.FoodPaging;
import models.Cart;

/**
 *
 * @author DELL
 */
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("txtNameF");
            String nameKH = request.getParameter("txtName");
            int price = Integer.parseInt(request.getParameter("txtPrice"));
            String dateCre = request.getParameter("txtDateCreate");
            String image = request.getParameter("image");
            String des = request.getParameter("txtDes");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            String cate = request.getParameter("txtCate");
            String currentPage = request.getParameter("txtPage");

            HttpSession session = request.getSession();
            Cart shoppingCart = (Cart) session.getAttribute("cart");
            if (shoppingCart == null) {
                shoppingCart = new Cart(nameKH);
            }

            CartDTO dto = new CartDTO();
            dto.setId(id);
            dto.setName(name);
            dto.setQuantity(quantity);
            dto.setPrice(price);
            dto.setQuantityCart(1);
            dto.setCategory(cate);
            dto.setCreateDate(dateCre);
            dto.setDescription(des);
            dto.setImage(image);

            shoppingCart.addToCart(dto);
            session.setAttribute("cart", shoppingCart);
            FoodDAO daof = new FoodDAO();
            FoodPaging loadPage = new FoodPaging();
            int pageTotal = 0;
            int icurrentPage = Integer.parseInt(currentPage);
            List<FoodDTO> result = daof.loadListSearch(icurrentPage, 20);
            loadPage.setListFood(result);
            int count = daof.countRowActive();

            if (count % 20 == 0) {
                pageTotal = count / 20;
                loadPage.setTotalPage(pageTotal);
            } else {
                pageTotal = (count / 20) + 1;
                loadPage.setTotalPage(pageTotal);
            }
            if(result != null){
                request.setAttribute("LOADLIST", loadPage);
            }

        } catch (Exception e) {
            log("ERROR at AddToCartController" + e.getMessage());
        } finally {
            request.getRequestDispatcher("food.jsp").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
