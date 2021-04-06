/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import daos.CategoryDAO;
import daos.FoodDAO;
import dtos.FoodDTO;
import dtos.FoodPaging;
import org.apache.tomcat.util.http.fileupload.RequestContext;

/**
 *
 * @author DELL
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "manager.jsp";

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
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
              FileItemFactory factory = new DiskFileItemFactory();
              ServletFileUpload upload = new ServletFileUpload(factory);
              List items = null;
              try {
               items = upload.parseRequest(request);
              } catch (FileUploadException e) {
                    e.printStackTrace();
               }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            String RealPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                            File saveFile = new File(RealPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                String name = (String) params.get("txtName");
                String id = (String) params.get("id");
                String description = (String) params.get("txtDescription");
                String price = (String) params.get("txtPrice");
                String quantity = (String) params.get("txtQuantity");
                String category = (String) params.get("slCategory");
                String status = (String) params.get("cbxStatus");
                FoodDAO dao = new FoodDAO();
                CategoryDAO daoC = new CategoryDAO();
                FoodPaging loadPage = new FoodPaging();
                int cateId = daoC.loadId(category);
                int idP = Integer.parseInt(id);
                int pageTotal = 0;
                if (fileName.equals("")) {
                    boolean check1 = dao.updateFoodNoImage(idP, name, description, quantity, price, cateId, status);
                    if (check1) {
                        List<FoodDTO> result1 = dao.loadListAdmin(1, 20);
                        loadPage.setListFood(result1);
                        int count1 = dao.countRowAll();

                        if (count1 % 20 == 0) {
                            pageTotal = count1 / 20;
                            loadPage.setTotalPage(pageTotal);
                        } else {
                            pageTotal = (count1 / 20) + 1;
                            loadPage.setTotalPage(pageTotal);
                        }

                        if (result1 != null) {
                            url = SUCCESS;
                            request.setAttribute("LOADLIST", loadPage);
                        } else {
                            url = ERROR;
                            request.setAttribute("ERROR", "List Food is empty");
                        }
                    }
                } else {
                    boolean check = dao.updateFood(idP, name, fileName, description, quantity, price, cateId, status);
                    if (check) {
                        List<FoodDTO> result = dao.loadListAdmin(1, 20);
                        loadPage.setListFood(result);
                        int count = dao.countRowAll();
                        if (count % 20 == 0) {
                            pageTotal = count / 20;
                            loadPage.setTotalPage(pageTotal);
                        } else {
                            pageTotal = (count / 20) + 1;
                            loadPage.setTotalPage(pageTotal);
                        }
                        if (result != null) {
                            url = SUCCESS;
                            request.setAttribute("LOADLIST", loadPage);
                        } else {
                            url = ERROR;
                            request.setAttribute("ERROR", "List Food is empty");

                        }

                    }
                }

            }
        } catch (Exception e) {
            log("Error at UpdateController " + e.getMessage());
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
