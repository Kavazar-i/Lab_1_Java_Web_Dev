<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1>Hello, (forward) ${user}</h1>
<hr/>
Hi, (redirect/forward) ${user_name}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>