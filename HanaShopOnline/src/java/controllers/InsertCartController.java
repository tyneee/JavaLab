/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daos.CartDAO;
import daos.FoodDAO;
import dtos.CartDTO;
import dtos.OrderDTO;
import models.Cart;

/**
 *
 * @author DELL
 */
public class InsertCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "cart.jsp";

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
        String url = ERROR;
        try {
          HttpSession session = request.getSession();
            String userID = request.getParameter("userId");
            Cart shoppingCart = (Cart) session.getAttribute("cart");
            CartDAO dao = new CartDAO();
            FoodDAO daoF = new FoodDAO();
            if (shoppingCart != null) {
//           



                // random cho don hang
                Random random = new Random();
                int idR = random.nextInt(999999);
                List<OrderDTO> result = dao.loadIdOrder();
                if (result.isEmpty()) {
                    if (dao.insertOrder(idR, userID)) {
                        for (CartDTO dto : shoppingCart.getCart().values()) {
                            if (dao.insertOrderDetail(idR, dto.getId(), dto.getQuantityCart())) {
                                int idF = Integer.parseInt(dto.getId());
                                if (!daoF.updateQuantityProduct(dto.getQuantity() - dto.getQuantityCart(), idF)) {
                                }
                            }
                            ///
                        }
                        url = SUCCESS;
                        session.setAttribute("cart", null);
                        request.setAttribute("SUCCESS", "Order Successfull");
                    }
                } else {
                    int id = 0;
                    for (OrderDTO dto : result) {
                        id = dto.getId();
                        if (id == idR) {
                            url = SUCCESS;
                        } else {
                            if (dao.insertOrder(idR, userID)) {
                                for (CartDTO dtos : shoppingCart.getCart().values()) {
                                    if (dao.insertOrderDetail(idR, dtos.getId(), dtos.getQuantityCart())) {
                                        int idF = Integer.parseInt(dtos.getId());
                                        if (!daoF.updateQuantityProduct(dtos.getQuantity() - dtos.getQuantityCart(), idF)) {
                                        }
                                    }
                                }
                                url = SUCCESS;
                                session.setAttribute("cart", null);
                                request.setAttribute("SUCCESS", "Order Successfull");
                            }
                        }
                    }
                }
            } else {
                url = ERROR;
                request.setAttribute("ERROR", "Cart is empty!");
            }

        } catch (Exception e) {
            log("Error at InsertCartController " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
