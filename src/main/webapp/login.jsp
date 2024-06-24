<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .have-account {
            text-align: center;  /* Center align the content */
            margin-top: 10px;   /* Add some space above the link */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <div class="have-account">
            <h2>Have no account? <a href="register.jsp">Register Form</a></h2>
        </div>
    </form>
</div>
</body>
</html>
