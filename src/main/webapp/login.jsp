<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
                 Login
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>

    <jsp:body>

        <h3>You can log in here</h3>


        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <form method="post" action="login">
                        <div class="mb-2">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-2">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form><br/>
                    <p> Or register here: <a href="register.jsp">Register</a></p>
                </div>
            </div>
        </div>


        <%--     <form action="login" method="post">
                 <label for="username">Username: </label>
                 <input type="text" id="username" name="username"/>
                 <label for="password">Password: </label>
                 <input type="password" id="password" name="password"/>
                 <input type="submit"  value="Log in"/>
             </form>--%>




    </jsp:body>
</t:pagetemplate>