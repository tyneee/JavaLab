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
public class SearchController extends HttpServlet {

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
            String currentPage = request.getParameter("txtPage");
            String searchValue = request.getParameter("txtSearch");
            int pageTotal = 0;
            int icurrentPage = Integer.parseInt(currentPage);
            if (!searchValue.equals("")) {
                FoodDAO dao = new FoodDAO();
                FoodPaging page = new FoodPaging();
                CategoryDAO daoC = new CategoryDAO();
                List<CategoryDTO> resultC = daoC.loadNameCategory();
                List<FoodDTO> result = dao.searchFood(searchValue, icurrentPage, 20);
                if (result == null) {
                    request.setAttribute("NOLIST", "List is empty");
                }
                page.setListFood(result);
                int count = dao.countSearchTotalPage(searchValue);
                if (count % 20 == 0) {
                    pageTotal = count / 20;
                    page.setTotalPage(pageTotal);
                } else {
                    pageTotal = (count / 20) + 1;
                    page.setTotalPage(pageTotal);
                }
                request.setAttribute("LISTSEARCH", page);
                request.setAttribute("NAMECATE", resultC);
                request.setAttribute("VALUESEARCH", searchValue);
            } else {
                request.setAttribute("INVALID", "Search can't be blank");
            }

        } catch (Exception e) {
            log("Error at searchController " + e.getMessage());
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
