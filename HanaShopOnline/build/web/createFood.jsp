
<%@page import="dtos.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
       <!-- <link rel="stylesheet" href="style.css"> -->
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2 id="wel">WELCOME ${sessionScope.NAME} MANAGER</h2>

        <div class="container" style="margin-top: 10px;text-align: center;">
            <div class="row">
                <div class="col-sm-2">
                    <div class="accorditionLink">
                        <c:url var="detailLink" value="LoadListOfAdminController">
                            <c:param name="txtPage" value="1"/>
                        </c:url>
                        <a href="${detailLink}">List Product </a>
                        <a href="createFood.jsp">Create Product</a>
                    </div>
                </div>
                <div class="col-sm" >
                    <c:if test="${requestScope.SUCCESS != null}">
                        <font color="red">
                        ${requestScope.SUCCESS}
                        </font>
                    </c:if>
                    <form name="myForm" action="InsertController" method="POST" enctype="multipart/form-data" onsubmit="return validateFormInsert()" >
                        <p>Name</p> <input class="create" type="text" name="txtName"/> </br>

                        <p>Image</p> <input class="create" type="file" name="txtImage" accept=".jpg,.png"/></br>
                        <p>Description</p>  <input class="create" type="text" name="txtDescription"/></br>

                        <p>Price</p> <input class="create" type="text" name="txtPrice"/></br>

                        <p>Quantity</p> <input class="create" type="text" name="txtQuantity"/></br>
                        <c:if test="${not empty requestScope.NAMECATE}">

                            <select class="create" name="slCategory" >
                                <c:forEach var="dtoC" items="${requestScope.NAMECATE}">

                                    <option value="${dtoC.name}">${dtoC.name}</option>
                                </c:forEach>

                            </select></br>

                        </c:if>
                        <input type="submit" style="width: 90px;" class="submitI" value="CREATE"/>
                    </form>

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
