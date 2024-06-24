<%@ page import="java.util.List" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.User" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.Project" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.UserService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.ProjectService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.impl.UserServiceImpl" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%
    UserService userService = new UserServiceImpl();
    ProjectService projectService = new ProjectServiceImpl();

    List<User> users = userService.getAllUsers();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Public Profiles</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .align_center {
            text-align: center;
            align-items: center;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        img {
            max-width: 200px;
            max-height: 200px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Public Profiles</h2>
    <% for (User user : users) {
        List<Project> projects = projectService.getProjectsByUserId(user.getId());
        if (projects == null) {
            projects = new ArrayList<>();
        }
    %>
    <div class="user-profile">
        <h3>User Profile: <%= user.getFullname() %></h3>
        <div class="form-group">
            <label for="fullname">Full Name:</label>
            <p id="fullname"><%= user.getFullname() %></p>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <p id="email"><%= user.getEmail() %></p>
        </div>
        <div class="form-group">
            <label for="bio">Bio:</label>
            <p id="bio"><%= user.getBio() %></p>
        </div>
        <div class="form-group">
            <label for="skills">Skills:</label>
            <p id="skills"><%= user.getSkills() %></p>
        </div>
        <h3>Projects</h3>
        <ul>
            <% for (Project project : projects) { %>
            <li>
                <h4><%= project.getTitle() %></h4>
                <p><%= project.getDescription() %></p>
                <% if (project.getPhotoFilename() != null && !project.getPhotoFilename().isEmpty()) { %>
                <img src="uploads/<%= project.getPhotoFilename() %>" alt="<%= project.getTitle() %> photo" class="img-thumbnail">
                <% } %>
                <br>
                <a href="<%= project.getProjectUrl() %>"><%= project.getProjectUrl() %></a>
                <br>
            </li>
            <% } %>
        </ul>
    </div>
    <hr>
    <% } %>
    <div class="align_center">
        <a href="index.jsp" class="btn btn-secondary btn-lg">Back</a>
    </div>
</div>
</body>
</html>
