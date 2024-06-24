<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.User" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.ProjectService" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.entity.Project" %>
<%@ page import="com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl" %>
<%
    User user = (User) session.getAttribute("user");
    Integer userId = user.getId();
    String projectIdAsString = request.getParameter("project_id"); // Retrieve project ID from request parameters

    ProjectService projectService = new ProjectServiceImpl();
    Project project = null;
    if (projectIdAsString != null) {
        int projectId = Integer.parseInt(projectIdAsString); // Convert project ID to integer
        project = projectService.getProjectById(projectId); // Fetch project details using project ID
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Project</title>
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
    <h2>Update Project</h2>
    <form action="update_project" method="post" enctype="multipart/form-data">
        <input type="hidden" name="project_id" value="<%= project.getId() %>">
        <div class="form-group">
            <label for="title">Project Title:</label>
            <input type="text" class="form-control" id="title" name="title"
                <% if (project.getTitle() != null) { %>
                   value="<%= project.getTitle() %>"
                <% } %>
                   required
            >
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description"
                      required><%= project.getDescription() %></textarea>
        </div>
        <div class="form-group">
            <label for="project_url">Project URL:</label>
            <input type="text" class="form-control" id="project_url"
                   name="project_url"
                <% if (project.getProjectUrl() != null) { %>
                   value="<%= project.getProjectUrl() %>"
                <% } %>
            >
        </div>
        <div class="form-group">
            <% if (project.getPhotoFilename() != null && !project.getPhotoFilename().isEmpty()) { %>
            <label for="photo_filename">Current Photo:</label>
            <br>
            <img src="../uploads/<%= project.getPhotoFilename() %>" alt="<%= project.getTitle() %> photo"
                 class="img-thumbnail">
            <br>
            <% } %>
            <label for="photo_filename">Upload Photo:</label>
            <input type="file" class="form-control-file" id="photo_filename" name="photo_filename">
        </div>
        <div class="align_center">
            <button type="submit" class="btn btn-primary">Update Project</button>
        </div>
    </form>
</div>
</body>
</html>
