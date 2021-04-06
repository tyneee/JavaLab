
<%@page import="dtos.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2 id="wel">WELCOME ${sessionScope.NAME} COME WITH US</h2>
        <div>
            <form action="SearchByPriceController" method="POST" style="display: flex; justify-content: center;">
                <input type="hidden" name="txtPage" value="1"/>
                <p class="nameSelect"> Select Price: </p>
                <p class="nameSelect"> From: </p> <input style="height: 30px; width: 100px; " type="text" name="txtFrom" value="${param.txtFrom}"/>
                <p class="nameSelect"> To: </p> <input style="height: 30px; width: 100px;" type="text" name="txtTo" value="${param.txtTo}"  />
                <input type="submit" class="submitI" value="OK">
            </form>
            <form action="SearchByCateController" method="POST" style="display: flex; justify-content: center; width: 50%; margin-left: 25%;">
                <input type="hidden" name="txtPage" value="1"/>
                <p style="width: 150px;letter-spacing: 2px;font-size: 17px;">Select category:</p> 

                <select class="create" name="slCategory" style="width: 16%;">

                    <option value="Food">Food</option>
                    <option value="Drink">Drink</option>

                </select></br>


                <input type="submit" class="submitI" value="OK">
            </form>
            <c:if test="${requestScope.INVALID != null}">
                <font color="red" style="text-align: center;">
                ${requestScope.INVALID}
                </font>

            </c:if>
        </div>
        <div class="container-fluid" style="margin-top: 70px;text-align: center;">
            <div class="row">
                <div class="col-sm-2" style="display: inline">


                </div>
                <div class="col-sm-8">
                    <c:set var="listPageFood" value="${requestScope.LOADLIST}"/>
                    <c:if test="${not empty listPageFood.listFood}">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Create Date</th>
                                    <th>Category</th>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                        <th>Selected</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <c:forEach var="dto" varStatus="counter" items="${listPageFood.listFood}">

                                <tbody>
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.name}</td>
                                        <td><img style="width: 100px;height: 100px;" src="images/${dto.image}"/></td>
                                        <td>${dto.description}</td>
                                        <td>${dto.price}</td>
                                        <td>${dto.createDate}</td>
                                        <td>${dto.category}</td>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                            <td>
                                                <form action="OrderController" method="POST">
                                                    <input type="hidden" name="txtName" value="${sessionScope.NAME}"/>
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input type="hidden" name="txtNameF" value="${dto.name}"/>
                                                    <input type="hidden" name="image" value="${dto.image}"/>
                                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}"/>
                                                    <input type="hidden" name="txtDes" value="${dto.description}"/>
                                                    <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                                    <input type="hidden" name="txtDateCreate" value="${dto.createDate}"/>
                                                    <input type="hidden" name="txtCate" value="${dto.category}"/>
                                                    <input type="hidden" name="txtPage" value="1" />
                                                    <input type="submit" class="submitI" value="ADD"/>
                                                </form>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div class="pagingL">
                        <c:forEach var="i" begin="1" end="${listPageFood.totalPage}">
                            <c:set var="txtPage" value="${i}"/>
                            <c:url var="link" value="LoadListFoodController">
                                <c:param name="txtPage" value="${i}"/>
                            </c:url>
                            <a href="${link}"> ${i} </a>
                        </c:forEach>
                    </div>


                    <c:set var="listFoodSearch" value="${requestScope.LISTSEARCH}"/>
                    <c:if test="${not empty listFoodSearch.listFood}">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Create Date</th>
                                    <th>Category</th>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                        <th>Selected</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <c:forEach var="dto" varStatus="counter" items="${listFoodSearch.listFood}">

                                <tbody>

                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.name}</td>
                                        <td><img style="width: 100px;height: 100px;" src="images/${dto.image}"/></td>
                                        <td>${dto.description}</td>
                                        <td>${dto.price}</td>
                                        <td>${dto.createDate}</td>
                                        <td>${dto.category}</td>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                            <td>
                                                <form action="OrderController" method="POST">
                                                    <input type="hidden" name="txtName" value="${sessionScope.NAME}"/>
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input type="hidden" name="txtNameF" value="${dto.name}"/>
                                                    <input type="hidden" name="image" value="${dto.image}"/>
                                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}"/>
                                                    <input type="hidden" name="txtDes" value="${dto.description}"/>
                                                    <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                                    <input type="hidden" name="txtDateCreate" value="${dto.createDate}"/>
                                                    <input type="hidden" name="txtCate" value="${dto.category}"/>
                                                    <input type="hidden" name="txtPage" value="1" />
                                                    <input type="submit" class="submitI" value="ADD"/>
                                                </form>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div  class="pagingL">
                        <c:forEach var="i" begin="1" end="${listFoodSearch.totalPage}">
                            <c:set var="txtPage" value="${i}"/>
                            <c:url var="link" value="SearchController">
                                <c:param name="txtPage" value="${i}"/>
                                <c:param name="txtSearch" value="${requestScope.VALUESEARCH}"/>
                            </c:url>
                            <a href="${link}"> ${i} </a>
                        </c:forEach>
                    </div>


                    <c:set var="listPrice" value="${requestScope.LISTPRICE}"/>
                    <c:if test="${not empty listPrice.listFood}">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Create Date</th>
                                    <th>Category</th>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                        <th>Selected</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <c:forEach var="dto" varStatus="counter" items="${listPrice.listFood}">

                                <tbody>

                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.name}</td>
                                        <td><img style="width: 100px;height: 100px;" src="images/${dto.image}"/></td>
                                        <td>${dto.description}</td>
                                        <td>${dto.price}</td>
                                        <td>${dto.createDate}</td>
                                        <td>${dto.category}</td>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                            <td>
                                                <form action="OrderController" method="POST">
                                                    <input type="hidden" name="txtName" value="${sessionScope.NAME}"/>
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input type="hidden" name="txtNameF" value="${dto.name}"/>
                                                    <input type="hidden" name="image" value="${dto.image}"/>
                                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}"/>
                                                    <input type="hidden" name="txtDes" value="${dto.description}"/>
                                                    <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                                    <input type="hidden" name="txtDateCreate" value="${dto.createDate}"/>
                                                    <input type="hidden" name="txtCate" value="${dto.category}"/>
                                                    <input type="hidden" name="txtPage" value="1" />
                                                    <input type="submit" class="submitI"  value="ADD"/>
                                                </form>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div  class="pagingL">
                        <c:forEach var="i" begin="1" end="${listPrice.totalPage}">
                            <c:set var="txtPage" value="${i}"/>
                            <c:url var="link" value="SearchByPriceController">
                                <c:param name="txtPage" value="${i}"/>
                                <c:param name="txtFrom" value="${requestScope.FROM}"/>
                                <c:param name="txtTo" value="${requestScope.TO}"/>
                            </c:url>
                            <a href="${link}"> ${i} </a>
                        </c:forEach>
                    </div>

                    <c:set var="listCate" value="${requestScope.LISTCATE}"/>
                    <c:if test="${not empty listCate.listFood}">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Create Date</th>
                                    <th>Category</th>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                        <th>Selected</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <c:forEach var="dto" varStatus="counter" items="${listCate.listFood}">

                                <tbody>

                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${dto.name}</td>
                                        <td><img style="width: 100px;height: 100px;" src="images/${dto.image}"/></td>
                                        <td>${dto.description}</td>
                                        <td>${dto.price}</td>
                                        <td>${dto.createDate}</td>
                                        <td>${dto.category}</td>
                                        <c:if test="${sessionScope.ROLE eq 'user'}">
                                            <td>
                                                <form action="OrderController" method="POST">
                                                    <input type="hidden" name="txtName" value="${sessionScope.NAME}"/>
                                                    <input type="hidden" name="id" value="${dto.id}"/>
                                                    <input type="hidden" name="txtNameF" value="${dto.name}"/>
                                                    <input type="hidden" name="image" value="${dto.image}"/>
                                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}"/>
                                                    <input type="hidden" name="txtDes" value="${dto.description}"/>
                                                    <input type="hidden" name="txtPrice" value="${dto.price}"/>
                                                    <input type="hidden" name="txtDateCreate" value="${dto.createDate}"/>
                                                    <input type="hidden" name="txtCate" value="${dto.category}"/>
                                                    <input type="hidden" name="txtPage" value="1" />
                                                    <input type="submit" class="submitI" value="ADD"/>
                                                </form>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <div  class="pagingL">
                        <c:forEach var="i" begin="1" end="${listCate.totalPage}">
                            <c:set var="txtPage" value="${i}"/>
                            <c:url var="link" value="SearchByCateController">
                                <c:param name="txtPage" value="${i}"/>
                                <c:param name="slCategory" value="${requestScope.CATE}"/>
                            </c:url>
                            <a href="${link}"> ${i} </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-sm-2">
                    <p></p>

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
