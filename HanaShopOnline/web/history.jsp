
<%@page import="java.util.List"%>
<%@page import="dtos.CartDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h2 id="wel">WELCOME ${sessionScope.NAME}</h2>
        <form action="SearchHistoryByNameController" method="POST" style="display: flex; justify-content: center; width: 550px; margin-left: 29%;">
            <input type="hidden" name="userId" value="${sessionScope.USERID}"/>
            <p class="nameSelect">Input Product Name: </p> <input type="text" name="txtName" style="height: 30px; width: 200px;"/>
            <input type="submit" class="submitI" value="OK">
        </form>
        <form action="SearchHistoryByDateController" method="POST" style="display: flex; justify-content: center; width: 550px; margin-left: 30%;">
            <input type="hidden" name="userId" value="${sessionScope.USERID}"/>
            <p class="nameSelect">Input Date Order: </p> <input type="date" name="txtDate" style="height: 30px; width: 200px;" />
            <input type="submit" class="submitI" value="OK">
        </form>
        <div class="container-fluid" style="margin-top: 70px;text-align: center;">
            <div class="row">
                <div class="col-sm-2">
                    <p></p>

                </div>
                <div class="col-sm-8">
                    <c:if test="${not empty requestScope.LIST}">

                        <table border="0" style="margin-bottom: 50px;width: 100%;" >
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Amount</th>
                                    <th>Date Order</th>

                                </tr>
                            </thead>
                            <c:forEach var="dto" items="${requestScope.LIST}" varStatus="counter">
                                <tbody>
                                    <tr>
                                        <td style="padding: 20px 20px;">${counter.count}</td>
                                        <td style="padding: 20px 20px;">${dto.name} </td>
                                        <td style="padding: 20px 20px;">${dto.price}</td>
                                        <td style="padding: 20px 20px;">${dto.quantity}</td>
                                        <td style="padding: 20px 20px;">${dto.price * dto.quantity}</td>
                                        <td style="padding: 20px 20px;">${dto.date}</td>

                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </c:if>
                    
                    <c:if test="${not empty requestScope.LISTHISTORY}">
                        <table border="0" style="margin-bottom: 50px;width: 100%;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Amount</th>
                                    <th>Date Order</th>
                                </tr>
                            </thead>
                            <c:forEach var="dto" varStatus="counter" items="${requestScope.LISTHISTORY}">

                                <tbody>
                                    <tr>
                                        <td style="padding: 20px 20px;">${counter.count}</td>
                                        <td style="padding: 20px 20px;">${dto.name}</td>
                                        <td style="padding: 20px 20px;">${dto.price}</td>
                                        <td style="padding: 20px 20px;">${dto.quantity}</td>
                                        <td style="padding: 20px 20px;">${dto.price * dto.quantity}</td>
                                        <td style="padding: 20px 20px;">${dto.date}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>


                </div>
                <div class="col-sm-2">
                    <p></p>

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>
