<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #3c1361">
        <div class="container">
            <a class="navbar-brand" href="index">
                <img src="${pageContext.request.contextPath}/images/cupcakepluslogo.png" width="800px;"
                     class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">

                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link" style="color: #ffffff"
                           href="${pageContext.request.contextPath}/login.jsp">Login</a>
                        <a class="nav-item nav-link" style="color: #ffffff"
                           href="${pageContext.request.contextPath}/register.jsp">Register</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link" style="color:  #ffffff"
                           href="${pageContext.request.contextPath}/logout">Log out</a>
                    </c:if>

                    <a class="nav-item nav-link" style="color: #ffffff"
                       href="${pageContext.request.contextPath}/shoppingcart"> <img style="color: white"
                                                                                    src="images/Bag2.svg" width="32"
                                                                                    height="32"> </a>
                    <br>
                    <div class="position-absolute top-50 end-0 mt-5">
                        <h5 style="color: #ffffff"> ${sessionScope.user.email}</h5>
                    </div>


                    <c:if test="${sessionScope.user.role.equalsIgnoreCase(\"admin\")}">
                        <div class="position-absolute top-50 end-0 mt-4">
                            <a href="admin">
                                <h5 style="color: #ffffff"> ${sessionScope.user.role}</h5></a>
                        </div>
                    </c:if>




                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1>
        <jsp:invoke fragment="header"/>
    </h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3">
    <hr/>
    <div class="row mt-3">
        <div class="col text-start">
            <h5>VIRKSOMHEDS<br>
                INFORMATION</h5>
            Olsker Cupcakes<br>
            Rønnevej 51.<br>
            3770. Allinge<br>
            CVR: 28456722
        </div>
        <div class="col text-center">
            <h5>KONTAKT<br>
                OS</h5>
            <a href="kontakt">Kontakt os her</a><br/>
            Email: info@olskercupcakes.dk<br>
            Tlf.nr: +4543434343
        </div>
        <div class="col text-end">
            <img src="images/facebook.png" width="30px;" class="img-fluid"/>
            <img src="images/insta.png" width="30px;" class="img-fluid"/>
            <img src="images/linkedin.png" width="30px;" class="img-fluid"/>
        </div>


    </div>
</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>