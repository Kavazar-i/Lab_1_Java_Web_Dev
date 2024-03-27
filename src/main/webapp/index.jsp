<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Multiply Digit</title>
</head>
<body>
<h1>
    Multiply a Digit
</h1>
<br/>
<form action="controller" method="get">
    <input type="hidden" name="command" value="login"/>
  Username: <input type="text" name="username" value=""/>
    <br/>
  Password: <input type="password" name="pass" value=""/>
    <br/>
    <input type="submit" name="sub" value="Push"/>
    <br/>
    ${login_error_msg}
</form>
</body>
</html>