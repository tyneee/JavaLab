<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Hana Shop</title>
      <!--  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
             integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="style.css">
      -->
    </head>

    <body>
        <nav id="nav-1">
            <div class="container-fluid">
                <div class="row"  >
                    <div class="col-sm-4">
                        <a class="link-1" href="index.jsp">HANA SHOP</a>

                    </div>

                    <div class="col-sm-1">
                        <form  action="LoadListFoodController" method="POST">
                            <input class="link-1" type="submit" value="FOOD"/>
                            <input type="hidden" name="txtPage" value="1" />

                        </form>
                    </div>
                    <div class="col-sm-1">
                        <c:if test="${sessionScope.ROLE eq 'user'}">
                            <form  action="HistoryController" method="POST">
                                <input  type="hidden" name="txtUser" value="${sessionScope.USERID}"/>
                                <input class="link-1" type="submit" value="HISTORY"/>
                            </form>
                        </c:if>
                    </div>

                    <div class="col-sm-">
                        <c:if test="${sessionScope.ROLE eq 'admin'}">
                            <form  action="LoadListOfAdminController" method="POST">
                                <input class="link-1" type="submit" value="MANAGER"/>
                                <input type="hidden" name="txtPage" value="1" />
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.ROLE eq 'user'}">
                            <form  action="cart.jsp" method="POST">
                                <input class="link-1" type="submit" value="CART"/>
                                <input type="hidden" name="txtPage" value="1" />

                            </form>
                        </c:if>

                    </div>
                    <div class="col-sm-1">
                        <c:if test="${sessionScope.ROLE != null}">
                            <form action="LogoutController" method="POST">
                                <input class="link-1" type="submit" value="LOGOUT"/>
                            </form>
                        </c:if>

                        <c:if test="${sessionScope.ROLE == null}">
                            <form action="login.jsp" method="POST">
                                <input class="link-1" type="submit" value="LOGIN"/>
                            </form>
                        </c:if>
                    </div>
                    <div class="col-sm" >
                        <form action="SearchController" method="POST" style="display: flex; height: 30px; width: 390px;">
                            <input type="hidden" name="txtPage" value="1"/>
                            <input type="text" class="txtSearch" placeholder="Search here" name="txtSearch" />
                            <input type="submit" class="link-1" value="SEARCH"/>
                        </form>
                    </div>
                </div>

            </div>

        </nav>