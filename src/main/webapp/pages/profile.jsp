<%@ page import="java.util.List" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.User" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.Project" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.ProjectService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl" %>
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
    <h2>Profile</h2>
    <form action="update_profile" method="post">
        <div class="form-group">
            <label for="fullname">Full Name:</label>
            <input type="text" class="form-control" id="fullname" name="fullname" value="<%= user.getFullname() %>">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
        </div>
        <div class="form-group">
            <label for="bio">Bio:</label>
            <textarea class="form-control" id="bio" name="bio"><%= user.getBio() %></textarea>
        </div>
        <div class="form-group">
            <label for="skills">Skills:</label>
            <input type="text" class="form-control" id="skills" name="skills" value="<%= user.getSkills() %>">
        </div>
        <div class="align_center">
            <input type="hidden" name="user_id" value="<%= user.getId() %>">
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </div>
    </form>
    <form action="logout" method="post">
        <div class="align_center">
            <button type="submit" class="btn btn-primary">Logout</button>
        </div>
    </form>
    <h3>Projects</h3>
    <ul>
        <% for (Project project : projects) { %>
        <li>
            <h4><%= project.getTitle() %>
            </h4>
            <p><%= project.getDescription() %>
            </p>
            <% if (project.getPhotoFilename() != null && !project.getPhotoFilename().isEmpty()) { %>
            <img src="../uploads/<%= project.getPhotoFilename() %>" alt="<%= project.getTitle() %> photo"
                 class="img-thumbnail">
            <% } %>
            <br>
            <a href="<%= project.getProjectUrl() %>"><%= project.getProjectUrl() %>
            </a>
            <br>
            <form action="updateProject.jsp" method="get">
                <input type="hidden" name="project_id" value="<%= project.getId() %>">
                <div>
                    <button class="btn btn-secondary">Update Project</button>
                </div>
            </form>
        </li>
        <% } %>
    </ul>
    <div class="align_center">
        <a href="addProject.jsp" class="btn btn-primary align_center">Add Project</a>
    </div>
</div>
</body>
</html>
