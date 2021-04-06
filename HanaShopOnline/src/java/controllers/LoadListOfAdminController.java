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
public class LoadListOfAdminController extends HttpServlet {

    private static final String SUCCESS = "manager.jsp";
    private static final String ERROR = "error.jsp";

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
            String currentPage = request.getParameter("txtPage");
            FoodDAO dao = new FoodDAO();
            FoodPaging loadPage = new FoodPaging();
            int pageTotal = 0;
            int icurrentPage = Integer.parseInt(currentPage);
            List<FoodDTO> result = dao.loadListAdmin(icurrentPage, 20);
            loadPage.setListFood(result);
            int count = dao.countRowAll();

            if (count % 20 == 0) {
                pageTotal = count / 20;
                loadPage.setTotalPage(pageTotal);
            } else {
                pageTotal = (count / 20) + 1;
                loadPage.setTotalPage(pageTotal);
            }
            CategoryDAO daoC = new CategoryDAO();
            List<CategoryDTO> resultC = daoC.loadNameCategory();
            if (result != null) {
                url = SUCCESS;
                request.setAttribute("LOADLIST", loadPage);
                request.setAttribute("NAMECATE", resultC);
            } else {
                url = ERROR;
                request.setAttribute("ERROR", "List Food is empty");
            }
        } catch (Exception e) {
            log("Error at loadlistfoodController " + e.getMessage());
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
