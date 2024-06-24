<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.User" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.ProjectService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.Project" %>
<%
    User user = (User) session.getAttribute("user");
    Integer userId = user.getId();
    Project project = (Project) request.getAttribute("project");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Add Project3</h2>
    <form action="add_project" method="post" enctype="multipart/form-data">
        <input type="hidden" name="user_id" value="<%= userId %>">
        <div class="form-group">
            <label for="title">Project Title:</label>
            <input type="text" class="form-control" id="title" name="title" value="<%= user.getFullname() %>" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description"value="<%= user.getFullname() %>" required></textarea>
        </div>
        <div class="form-group">
            <label for="project_url">Project URL:</label>
            <input type="text" class="form-control" id="project_url" name="project_url" value="<%= user.getFullname() %>">
        </div>
        <div class="form-group">
            <label for="photo_filename">Upload Photo:</label>
            <input type="file" class="form-control-file" id="photo_filename" name="photo_filename">
        </div>
        <div class="align_center">
            <button type="submit" class="btn btn-primary">Add Project</button>
        </div>
    </form>
</div>
</body>
</html>
