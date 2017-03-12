<%--
  Created by IntelliJ IDEA.
  User: kamel
  Date: 3/7/2017
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="0" width="90%">
    <form action="addCustomer" commandName="customerForm" >
        <tr>
            <td align="left" width="20%">customerid: </td>
            <td align="left" width="40%"><input name="customerId" size="5"/></td>
            <td align="left"><errors path="*" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input name="companyName" size="30"/></td>
            <td><errors path="*" cssClass="error"/></td>
        </tr>
        <tr>
            <td></td>
            <td align="center"><input type="submit" value="add"/></td>
            <td></td>
        </tr>
        ${errors}
    </form>
</table>
</body>
</html>
