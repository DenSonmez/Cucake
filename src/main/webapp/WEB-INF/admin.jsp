<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <jsp:attribute name="header">
         Admin menu
    </jsp:attribute>

    <jsp:attribute name="footer">
        Admin menu
    </jsp:attribute>

    <jsp:body>

        <form method="post">
            <select class="form-select" style="color: #6f42c1" aria-label="Default select example" name="user">
                <option selected>Vælg en bruger</option>
                <c:forEach var="user" items="${requestScope.userList}">
                    <option value="${user.username}">${user.username} ${user.saldo},-</option>
                </c:forEach>
            </select>

            <br>
            <label for="money" class="form-label">Hvor meget skal det indsættes?</label>
            <input type="text" class="form-control" id="money" name="money" required> <br/>

            <button formaction="admin" type="submit" class="btn btn-primary">Submit</button>

        </form>

        <form method="post">
            <table class="table table-striped mt-4">
                <thead>
                <tr>
                    <td class="text-start align-middle" style="color: #6f42c1"><h5>Order ID</h5>
                    </td>
                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Total pris</h5>
                    </td>
                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Timestamp</h5>
                    </td>
                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Username</h5>
                    </td>
                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Er order hentet?</h5>
                    </td>
                    <td class="text-center align-middle" style="color: #6f42c1"><h5>Cupcake mængde</h5>
                    </td>
                    <td class="text-end"></td>
                </tr>
                </thead>
                <c:forEach var="order" items="${requestScope.orderList}"
                           varStatus="loop">
                    <tr>
                        <td class="text-start align-middle">${order.orderId} </td>
                        <td class="text-center align-middle">${order.totalPrice}</td>
                        <td class="text-center align-middle">${order.orderCreationTimestamp}</td>
                        <td class="text-center align-middle">${order.username}</td>
                        <td class="text-center align-middle">${order.orderActive}</td>
                        <td class="text-end align-middle">${order.orderAmount}</td>
                        <td class="text-end">

                            <button formaction="removeorder" type="submit"
                                    class="ms-2 btn btn-primary btm-sm" name="orderid"
                                    value="${order.orderId}">Fjern

                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </form>
    </jsp:body>

</t:pagetemplate>
