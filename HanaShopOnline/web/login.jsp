
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
   <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"> 
       -->
    </head>
    <body>
          
        <form action="LoginController" method="POST">
   
            Username:  <input class="form-control" type="text" name="txtUserID" value="${param.txtUserID}" style="width: 390px; height: 30px;
                                                                                                            "/> 
            <c:if test="${requestScope.INVALID.userIdError != null}">
                <font color="red">
                ${requestScope.INVALID.userIdError}
                </font>
            </c:if> 

            <br/>
            Password: <input class="form-control" type="password" name="txtPassword" style="width: 390px; height: 30px; "/> 
            <c:if test="${requestScope.INVALID.passwordError != null}">
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
            </c:if> 
            <br/>
            <input type="hidden" name="txtPage" value="1" />
            <input class="btn btn-primary" type="submit" name="action" value="Login" />
        
        </form>
            
       <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/HanaShopOnline/login-google&response_type=code
           &client_id=1094506801663-mgcj8am6mf7rd931vkvo2ivgalf20m7i.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>
    </body>
</html>
