<!DOCTYPE html>
<html>
<head>
    <title>Public Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>${user.fullname}'s Profile</h2>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Bio:</strong> ${user.bio}</p>
    <p><strong>Skills:</strong> ${user.skills}</p>
    <h3>Projects</h3>
    <c:forEach var="project" items="${projects}">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">${project.title}</h5>
                <p class="card-text">${project.description}</p>
                <img src="${project.image_url}" class="card-img-top" alt="Project Image">
                <a href="${project.project_url}" class="btn btn-primary">View Project</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
