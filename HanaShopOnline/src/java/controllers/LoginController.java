/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daos.RegistrationDAO;
import dtos.RegistrationErrorObject;

/**
 *
 * @author DELL
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "index.jsp";
    private static final String USER = "index.jsp";
    private static final String INVALID = "signIn.jsp";

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
            String userId = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");

            RegistrationErrorObject error = new RegistrationErrorObject();
            boolean valid = true;
            
            if (userId.length() == 0) {
                error.setUserIdError("User ID can't be blank");
                valid = false;
            }
            if (password.length() == 0) {
                error.setPasswordError("Password can't be blank!");
                valid = false;
            }
            if (valid) {
               
                RegistrationDAO dao = new RegistrationDAO();
                String role = dao.checkLogin(userId, password);
                if (role.equals("failed")) {
                    request.setAttribute("ERROR", "Invalid email or password");
                } else {
                    HttpSession session = request.getSession();
                    String name = dao.loadName(userId);
                    session.setAttribute("USERID", userId);
                    session.setAttribute("NAME", name);
                    session.setAttribute("ROLE", role);

                    if (role.equals("admin")) {
                        url = ADMIN;
                    } else if (role.equals("user")) {
                        url = USER;
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Your role is invalid");
                    }
                }
                
            } else {
                url = INVALID;
                request.setAttribute("INVALID", error);
            }

        } catch (Exception e) {
            log("ERROR at SignInController" + e.getMessage());

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
