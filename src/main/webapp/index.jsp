<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

                Velkommen til Olsker Cupcakes ${sessionScope.user.username}

    </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen til Olsker Cupcakes
    </jsp:attribute>

    <jsp:body>


     <p class="d-inline"><em> Herunder sammensætter du din favorit cupcake:</em></p><br><br>

        <div style="display: flex; justify-content: center;">
        <div><form method="post">

            <select class="form-select" style="color: #6f42c1; width: 600px; text-align-last: center;" aria-label="Default select example" name="top">
                <option selected>Vælg en top</option>
                <c:forEach var="top" items="${sessionScope.cupcaketops}">
                    <option value="${top.cupcakeTopId}">${top.flavor} ${top.price},-</option>
                </c:forEach>
            </select>


            <select class="form-select" style="color: #6f42c1; width: 600px; text-align-last: center;" aria-label="Default select example" name="bottom">
                    <option selected>Vælg en bund</option>
                    <c:forEach var="bottom" items="${sessionScope.cupcakebottoms}">
                        <option value="${bottom.cupcakeBottomId}">${bottom.flavor} ${bottom.price},-</option>
                    </c:forEach>
                </select>


                <div class="btn-toolbar justify-content-between mt-4" role="toolbar">
                    <div class="btn-group" role="group">
                        <button formaction="AddCupcakeOrder" type="submit"
                                class="btn btn-primary">Læg i kurven
                        </button>
                    </div>
                    <div class="btn-group" role="group">
                        <button formmethod="get" formaction="shoppingcart" type="submit"
                                class="btn btn-primary">Gå til kurven
                        </button>
                    </div>
                </div>
        </form>
        </div>
        </div>



    </jsp:body>

</t:pagetemplate>