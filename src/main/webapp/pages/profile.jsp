<%@ page import="java.util.List" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.User" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.Project" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.dao.ProjectDao" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.ProjectService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.exception.ServiceException" %>
<%@ page import="java.util.ArrayList" %>
<%
    User user = (User) session.getAttribute("user");
    ProjectService projectService = new ProjectServiceImpl();
    List<Project> projects = projectService.getProjectsByUserId(user.getId());
    if (projects == null) {
        projects = new ArrayList<>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Profile</h2>
    <form action="update_profile" method="post">
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
    <h3>Projects</h3>
    <ul>
        <c:forEach var="project" items="${projects}">
            <li>
                <h4>"${project.name}"</h4>
                <p>"${project.description}"</p>
                <a href="${project.url}">${project.url}</a>
            </li>
        </c:forEach>
    </ul>

    <h3>Add Project</h3>
    <form action="add_project" method="post" <%--enctype="multipart/form-data"--%>>
        <div class="form-group">
            <label for="name">Project Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description" required></textarea>
        </div>
        <div class="form-group">
            <label for="url">URL:</label>
            <input type="url" class="form-control" id="url" name="url" required>
        </div>
        <div class="form-group">
            <label for="file">Upload Photo:</label>
            <input type="file" class="form-control-file" id="file" name="file">
        </div>
        <button type="submit" class="btn btn-primary">Add Project</button>
    </form>
</div>
</body>
</html>
