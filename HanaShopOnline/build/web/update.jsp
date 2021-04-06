<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="dtos.CategoryDTO"%>
<%@page import="daos.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
     <!--   <link rel="stylesheet" href="style.css"> -->
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2 id="wel">WELCOME ${sessionScope.NAME}</h2>

        <div class="container" style="margin-top: 10px;text-align: center;">
            <div class="row">

                <div class="col-sm" >
                    <form  name="myForm" action="UpdateController" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">
                        <p>Name</p> <input class="create" type="text" name="txtName" value="${param.name}" /> </br>
                        <p>Image</p>
                        <img style="width: 100px;height: 100px;" name="paramImage" src="images/${param.image}" />
                        <input class="create" type="file" name="txtImage" accept=".jpg,.png" />
                        <p>Description</p>  <input class="create" type="text" name="txtDescription" value="${param.des}"/></br>

                        <p>Price</p> <input class="create" type="text" name="txtPrice" value="${param.price}"/></br>

                        <p>Quantity</p> <input class="create" type="text" name="txtQuantity" value="${param.quantity}"/></br>


                        <p>Category</p> 
                        <select class="create" name="slCategory">
                            <option selected>${param.cate}</option>
                            <option value="Food">Food</option>
                            <option value="Drink">Drink</option>

                        </select></br>

                        <p>Status: </p> <select class="create" name="cbxStatus">
                            <option  selected >${param.status}</option>
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select></br>
                        <p id="demo5"></p>
                        <input type="hidden" name="id" value="${param.id}"/>
                        <input type="submit" style="width: 90px;" class="submitI" value="UPDATE" />
                    </form>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
