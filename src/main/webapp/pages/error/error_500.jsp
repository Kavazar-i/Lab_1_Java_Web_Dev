<%--
  Created by IntelliJ IDEA.
  User: zaharkalosha
  Date: 26/03/2024
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page   contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500</title>
</head>
<body>
Request from : ${pageContext.errorData.requestURI} is failed <br/>
Servlet name : ${pageContext.errorData.servletName} <br/>
Status code : ${pageContext.errorData.statusCode} <br/>
Exception type : ${pageContext.exception.message} <br/>
<br/><br/><br/>
Message from exception : ${pageContext.exception.message}
</body>
</html>
