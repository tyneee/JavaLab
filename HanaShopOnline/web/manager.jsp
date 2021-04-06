

<%@page import="dtos.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
      <!--  <link rel="stylesheet" href="style.css"> -->
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2 id="wel">WELCOME ${sessionScope.NAME}</h2>

        <div class="container-fluid" style="margin-top: 10px;text-align: center;">
            <div class="row">
                <div class="col-sm-1">
                    <div class="accorditionLink">
                        <c:url var="detailLink" value="LoadListOfAdminController">
                            <c:param name="txtPage" value="1"/>
                        </c:url>
                        <a href="${detailLink}">List Product </a>
                        <a href="LoadNameCategoryController">Create Product</a>
                    </div>
                </div>
                <div class="col-sm-11">

                    <c:set var="listPageFood" value="${requestScope.LOADLIST}"/>
                    <c:if test="${not empty listPageFood.listFood}">
                        <form  action="DeleteController" method="POST" onSubmit="return confirm('Do you want to submit?')">
                            <table border="0" style="padding: 20px 20px;">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Name</th>
                                        <th>Image</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Create Date</th>
                                        <th>Category</th>
                                        <th>Status</th>
                                        <th>Update</th>
                                        <th>Delete</th>

                                    </tr>
                                </thead>
                                <c:forEach var="dto" varStatus="counter" items="${listPageFood.listFood}">
                                    <tbody>
                                        <tr>

                                            <td> ${counter.count}</td>

                                            <td> 
                                                <c:url var="detailLink" value="update.jsp">
                                                    <c:param name="id" value="${dto.id}"/>
                                                    <c:param name="name" value="${dto.name}"/>
                                                    <c:param name="image" value="${dto.image}"/>
                                                    <c:param name="des" value="${dto.description}"/>
                                                    <c:param name="price" value="${dto.price}"/>
                                                    <c:param name="quantity" value="${dto.quantity}"/>
                                                    <c:param name="cate" value="${dto.category}"/>
                                                    <c:param name="status" value="${dto.status}"/>
                                                    <c:set var="NAMECATE" value="${requestScope.NAMECATE}"/>
                                                </c:url>
                                                <a href="${detailLink}"style="text-decoration: none;">${dto.getName()}</a>
                                            </td>
                                            <td>
                                                <img style="width: 100px;height: 100px;" src="images/${dto.image}"/>

                                            </td>
                                            <td>                
                                                ${dto.description}
                                            </td>
                                            <td>
                                                ${dto.price}
                                            </td>
                                            <td>                           
                                                ${dto.quantity}
                                            </td>
                                            <td>
                                                ${dto.createDate}
                                            </td>
                                            <td>${dto.category} </td>
                                    <form action="UpdateStatusController" method="POST" onSubmit="return confirm('Do you want to submit?')">
                                        <td>
                                            <select class="create" name="cbxStatus">
                                                <option  selected  >${dto.status}</option>
                                                <option value="Active">Active</option>
                                                <option value="Inactive">Inactive</option>
                                            </select>
                                            <font color="red" style="font-size: 20px;">
                                            ${requestScope.INVALID.statusErrorObject}
                                            </font>
                                        </td>
                                        <td>
                                            <input type="hidden" name="id" value="${dto.id}"/>
                                            <input type="submit"  value="Update"/>

                                        </td>
                                    </form>

                                    <td>
                                        <input type="checkbox" name="cbbDelete"  value="${dto.id}" />
                                    </td>

                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                            <input type="hidden" name="txtPage" value="1" />
                            <input type="submit" style="width: 100px; margin:50px 1100px;" class="submitI" value="DELETE"/>


                        </form>
                    </c:if>


                    <div class="pagingL">
                        <c:forEach var="i" begin="1" end="${listPageFood.totalPage}">
                            <c:set var="txtPage" value="${i}"/>
                            <c:url var="link" value="LoadListOfAdminController">
                                <c:param name="txtPage" value="${i}"/>
                            </c:url>
                            <a href="${link}"> ${i} </a>
                        </c:forEach>
                    </div>

                </div>

            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
