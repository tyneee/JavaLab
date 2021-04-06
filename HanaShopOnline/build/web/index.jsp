<%@page import="daos.RegistrationDAO"%>
<%@page import="dtos.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Hana Shop</title>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <c:set var="mail" value="${sessionScope.USERID}"/>
        <h2 id="wel">WELCOME ${sessionScope.NAME} </h2>

        <div class="container-fluid" style="margin-top: 70px;text-align: center;">
            <div class="row">
                <div class="col-sm-2">
                    <p></p>

                </div>
                <div class="col-sm-4" >
                    <img src="images/shop.jpg" style="  height:50%;
                                                        border: 10px solid transparent;
                                                        padding: 15px;
                                                        border-image:20% round;"/>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>



                </div>
                <div class="col-sm-4">
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <p>Thank you for coming and choosing our service</p>
                    <img src="images/shop1.jpg" style=" height:50%;
                                                       border: 10px solid transparent;
                                                       padding: 15px;
                                                       border-image:20% round;"/>

                </div>
                <div class="col-sm-2">
                    <p></p>

                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
