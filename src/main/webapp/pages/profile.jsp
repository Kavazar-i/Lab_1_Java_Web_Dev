<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Profile</h2>
    <form action="profile" method="post">
        <div class="form-group">
            <label for="fullname">Full Name:</label>
            <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
        </div>
        <div class="form-group">
            <label for="bio">Bio:</label>
            <textarea class="form-control" id="bio" name="bio">${user.bio}</textarea>
        </div>
        <div class="form-group">
            <label for="skills">Skills:</label>
            <input type="text" class="form-control" id="skills" name="skills" value="${user.skills}">
        </div>
        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form>
</div>
</body>
</html>
