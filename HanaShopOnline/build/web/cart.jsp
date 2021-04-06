
<%@page import="dtos.CartDTO"%>
<%@page import="models.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="container-fluid" style="margin-top: 30px;text-align: center;">
            <div class="row">
                <div class="col-sm-2">
                    <p></p>

                </div>
                <div class="col-sm-8">
                    <c:set var="shop" value="${sessionScope.cart}"/>
                    <h2 id="wel">${sessionScope.NAME}'s CART</h2>
                    <c:if test="${not empty shop }">
                        <table border="0" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Food Name</th>
                                    <th>Price</th>
                                    <th>Amount</th>
                                    <th>Quantity</th>
                                    <th>Delete</th>

                                </tr>
                            </thead>



                            <tbody>
                                <c:forEach var="dto" items="${shop.cart.values()}" varStatus="counter">
                                    <tr>
                                        <td style="padding: 20px 20px;">${counter.count}</td>
                                        <td style="padding: 20px 20px;">${dto.name}</td>
                                        <td style="padding: 20px 20px;">${dto.price}</td>
                                        <td style="padding: 20px 20px;">${dto.price * dto.quantityCart}</td>
                                        <td style="padding: 20px 20px;">
                                            <form name="formUpdate" action="UpdateCartController" method="POST" onsubmit="return validateQuantity()">
                                                <input type="hidden" name="txtID" value="${dto.id}" />
                                                <input type="number"  name="txtQuantity" value="${dto.quantityCart}" />
                                                <input type="hidden"  name="txtQuantityReal" value="${dto.quantity}" />
                                                <input type="submit"  value="Update"/>
                                            </form>

                                        </td>
                                        <td style="padding: 20px 20px;">
                                            <form action="DeleteCartController" method="POST" onSubmit="return confirm('Do you want to remove?')">
                                                <input type="hidden" name="id" value="${dto.id}" />
                                                <input type="submit"  value="Delete"/>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td style="padding: 20px 20px;font-size: 30px;font-weight: 500;">Total</td>

                                    <td style="padding: 20px 20px; font-size: 30px;font-weight: 500;"> 
                                        ${shop.total}

                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div style="display: flex; justify-content: flex-end;  " >
                            <c:if test="${shop.total != 0}">
                                <form action="InsertCartController" method="POST" onSubmit="return warningMessage()" >
                                    <input type="hidden" name="userId" value="${sessionScope.USERID}"/>
                                    <input type="submit" value="Confirm" class="submitI" style="width: 100px; margin-right: 0px; margin-bottom: 50px;">
                                </form>
                            </c:if>

                        </div>
                    </c:if>



                </div>
                <div class="col-sm-2" >

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>


    </body>
</html>
