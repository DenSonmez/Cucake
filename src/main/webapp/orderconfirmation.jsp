<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Ordre bekræftet!
    </jsp:attribute>

<jsp:body>

    <div class="container py-4">

    <div class="row align-items-md-stretch">
        <div class="col-md-8">
            <div class="h-100 p-5 bg-light border rounded-3">

                <h2>Din samlede ordre til afhentning:</h2>
                <form method="post">
                    <table class="table table-striped mt-4">
                        <thead>

                        <tr>
                            <td class="text-start align-middle" style="color: #6f42c1"><h5>Cupcake</h5>
                            </td>
                            <td class="text-center align-middle" style="color: #6f42c1"><h5>Top</h5>
                            </td>
                            <td class="text-center align-middle" style="color: #6f42c1"><h5>Bottom</h5>
                            </td>
                        </tr>

                        </thead>
                        <c:forEach var="cupcake" items="${sessionScope.confirm_order.cupcakes}" varStatus="loop">
                            <tr>
                                <td class="text-start align-middle"> Cupcake #${loop.count} </td>
                                <td class="text-center align-middle">${cupcake.cupcakeTopFlavor}</td>
                                <td class="text-center align-middle">${cupcake.cupcakeBottomFlavor}</td>
                                <td class="text-end">
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>

                <h5><em>En ordrebekræftelse er sendt til din email - såfremt denne er brugt ved bestilling.</em></h5>

            </div>

        </div>
        <div class="col-md-4">
            <div class="h-48 p-5 bg-light border rounded-3">
                <h5>Tak fordi du køber lokalt og støtter Olsker Cupcakes!</h5><br>
                <h7>Smid os gerne lidt kærlighed på sociale medier</h7>
                <div class="col text-end">
                    <img src="images/facebook.png" width="30px;" class="img-fluid"/>
                    <img src="images/insta.png" width="30px;" class="img-fluid"/>
                </div>
            </div>

            <div class="h-48 p-5 bg-light border rounded-3 mt-2">
            <div class="h-48 p-5 bg-light border rounded-3 mt-2">
                <h5>Vidste du.. </h5>
                <h7>.. at Olsker Cupcakes er et lokalt ejet og bæredygtigt bageri?</h7><br>
                <h7>.. at vi er et sjette generations bageri med rødder tilbage til forrige århundrede?</h7><br>
                <h7>.. at vi udelukkende bruger økologiske råvarer til vores cupcakes?</h7>
            </div>
            </div>
        </div>
    </div>

        <footer class="pt-3 mt-4 text-muted border-top">
        </footer>
    </div>

</jsp:body>

</t:pagetemplate>
