<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
      Ordreoversigt
    </jsp:attribute>

    <jsp:attribute name="footer">
        Bestillingsliste:
    </jsp:attribute>

    <jsp:body>

        <div class="container py-4">

            <div class="row align-items-md-stretch">
                <div class="col-md-8">
                    <div class="h-100 p-5 bg-light border rounded-3">

                        <h2>Her er din bestilling - tak fordi du køber lokalt!</h2>
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
                                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Pris</h5>
                                    </td>
                                    <td class="text-end"></td>
                                </tr>
                                </thead>
                                <c:forEach var="cupcake" items="${sessionScope.current_order.cupcakes}"
                                           varStatus="loop">
                                    <tr>
                                        <td class="text-start align-middle"> Cupcake #${loop.count} </td>
                                        <td class="text-center align-middle">${cupcake.cupcakeTopFlavor}</td>
                                        <td class="text-center align-middle">${cupcake.cupcakeBottomFlavor}</td>
                                        <td class="text-center align-middle">${cupcake.price} kr.</td>
                                        <td class="text-end">


                                            <button formaction="removecupcakeorder" type="submit"
                                                    class="ms-2 btn btn-primary btm-sm" name="index"
                                                    value="${loop.index}">Fjern

                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </form>


                    </div>

                </div>
                <div class="col-md-4">
                    <div class="h-48 p-5 bg-light border rounded-3">
                        <c:if test="${sessionScope.user != null}">
                            <h2>Hej ${sessionScope.user.username}!</h2>
                        </c:if><br/>

                        <c:if test="${sessionScope.user == null}">
                            <h2> Har du en bruger? </h2>
                        </c:if><br/>


                        <p>
                            <c:if test="${sessionScope.user == null}">
                        <p> Login her: <a href="login.jsp">Login</a></p>
                        <p> Eller registrere dig her: <a href="register.jsp">Register</a></p>
                        </c:if>


                        <c:if test="${sessionScope.user != null}">
                            Her er din nuværende saldo: <strong>${sessionScope.user.saldo},- </strong>
                        </c:if><br/>

                        <c:if test="${sessionScope.user.saldo > 0}">
                        <em><p><strong>OBS!</strong> - Hvis din saldo er positiv, så vil denne blive brugt som primær
                            betalingsform</p></em>

                            Her er din nye saldo efter dette køb:
                            <strong><u> ${sessionScope.user.saldo - sessionScope.current_order.totalPrice}</u>,- </strong>
                        </c:if><br/>


                    </div>
                    <div class="h-48 p-5 bg-light border rounded-3 mt-2">

                        <ul class="list-group mb-3">
                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h2 class="my-0">Betalingsoversigt</h2>
                                </div>
                            </li>

                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">Pris </h6>
                                </div>
                                <span class="text-muted">${sessionScope.current_order.totalPrice},-</span>
                            </li>

                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <h6 class="my-0">Heraf moms</h6>
                                </div>
                                <span class="text-muted">${sessionScope.current_order.totalPriceVAT} ,-</span>
                            </li>

                            <li class="list-group-item d-flex justify-content-between lh-sm">
                                <div>
                                    <span>Pris i alt (inkl. moms)</span>
                                </div>
                                <strong>${sessionScope.current_order.totalPrice},-</strong>
                            </li>
                        </ul>
                        <form method="get" action="orderconfirmation">
                            <button type="submit" class="btn btn-success">Bekræft bestilling</button>
                        </form>
                    </div>


                </div>
            </div>

            <footer class="pt-3 mt-4 text-muted border-top">
            </footer>
        </div>

    </jsp:body>

</t:pagetemplate>
