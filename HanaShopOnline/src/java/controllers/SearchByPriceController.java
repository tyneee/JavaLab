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
import daos.CategoryDAO;
import daos.FoodDAO;
import dtos.CategoryDTO;
import dtos.FoodDTO;
import dtos.FoodPaging;

/**
 *
 * @author DELL
 */
public class SearchByPriceController extends HttpServlet {

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
            String page = request.getParameter("txtPage");
            String from = request.getParameter("txtFrom");
            String to = request.getParameter("txtTo");

            boolean valid = true;
            if (!from.matches("^[0-9]{5}$")) {
                request.setAttribute("INVALID", "From Price must be integer type with format EX: 15000");
                valid = false;
            }
            if (!to.matches("^[0-9]{5}$")) {
                request.setAttribute("INVALID", "To Price must be integer type with format EX: 15000");
                valid = false;
            }
            if (from.isEmpty()) {
                request.setAttribute("INVALID", "From  price can't be blank");
                valid = false;

            }
            if (to.isEmpty()) {
                request.setAttribute("INVALID", "To  price can't be blank");
                valid = false;

            }
            int ifrom = Integer.parseInt(from);
            int ito = Integer.parseInt(to);
            int pageTotal = 0;
            int currentPage = Integer.parseInt(page);
            if (valid) {
                FoodDAO dao = new FoodDAO();
                FoodPaging loadPage = new FoodPaging();

                List<FoodDTO> result = dao.searchFoodByPrice(ifrom, ito, currentPage, 20);
                if (result == null) {
                    request.setAttribute("INVALID", "List is empty");
                }
                loadPage.setListFood(result);
                int count = dao.countSearchTotalByPrice(ifrom, ito);
                if (count % 20 == 0) {
                    pageTotal = count / 20;
                    loadPage.setTotalPage(pageTotal);
                } else {
                    pageTotal = (count / 20) + 1;
                    loadPage.setTotalPage(pageTotal);
                }

                request.setAttribute("LISTPRICE", loadPage);

            }
            request.setAttribute("FROM", from);
            request.setAttribute("TO", to);

        } catch (Exception e) {
            log("Error at SearchByPriceController " + e.getMessage());
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
