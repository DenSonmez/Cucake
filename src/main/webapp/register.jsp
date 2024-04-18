<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Register
    </jsp:attribute>

    <jsp:attribute name="footer">
            Register
    </jsp:attribute>

    <jsp:body>

        <h3>You can register here</h3>

        <c:if test="${requestScope.message != null}">
            <p>${requestScope.message}</p>
        </c:if>


        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <form method="post" action="register">
                        <div class="mb-2">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="mb-2">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-2">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-2">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmpassword" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Register</button>
                    </form><br/>

                    <div class="d-inline">
                        <p> Or login here: <a
                                href="login.jsp">Log in</a></p>
                    </div>

                </div>
            </div>
        </div>
        <br>


    </jsp:body>
</t:pagetemplate>