<%@ page import="murach.business.Cart" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Murach's Java Servlets and JSP</title>
  <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>Your cart</h1>

<table>
  <tr>
    <th>Quantity</th>
    <th>Description</th>
    <th>Price</th>
    <th>Amount</th>
    <th></th>
  </tr>

  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
  <c:forEach var="item" items="${cart.items}">
    <tr>
      <td>
        <form action="cart" method="post">
          <input type="hidden" name="productCode" value="${item.product.code}">
          <input type=text name="quantity" value="${item.quantity}" id="quantity">
          <input type="hidden" name="update" value="true">
          <input type="submit" value="Update">
        </form>
      </td>
      <td>${item.product.description}</td>
      <td>${item.product.priceCurrencyFormat}</td>
      <td>${item.totalCurrencyFormat}</td>
      <td>
        <a href="cart?productCode=${item.product.code}&amp;quantity=0">Remove Item</a>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<%Cart cart = (Cart) session.getAttribute("cart");%>
<%if(cart.getDiscount() > 0) {
    int discount = cart.getDiscount();
    String totalbill = cart.getAmountCurrencyFormat();
    String totalbilldiscount = cart.getAmountDiscountCurrencyFormat();
%>
    <td><strong>Total Bill : </strong><s><%=totalbill%></s></td><br>
    <td><strong>Discount : </strong><%=discount%>%</td><br>
    <td><strong>Total Bill After Discount :</strong> <%=totalbilldiscount%></td>
  <%} else { String totalBill = cart.getAmountCurrencyFormat();%>
    <td><strong>Total Bill :</strong><%=totalBill%></td>
<%}%>
<p><b>To change the quantity</b>, enter the new quantity
  and click on the Update button.</p>

<form action="" method="post">
  <input type="hidden" name="action" value="shop">
  <input type="submit" value="Continue Shopping">
</form>

<form action="checkout.jsp" method="post">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" value="Checkout">
</form>

</body>
</html>