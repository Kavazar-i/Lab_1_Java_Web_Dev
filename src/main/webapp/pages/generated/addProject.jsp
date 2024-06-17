<!DOCTYPE html>
<html>
<head>
    <title>Add Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Add Project</h2>
    <form action="addProject" method="post">
        <div class="form-group">
            <label for="title">Project Title:</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" name="description"></textarea>
        </div>
        <div class="form-group">
            <label for="image_url">Image URL:</label>
            <input type="text" class="form-control" id="image_url" name="image_url">
        </div>
        <div class="form-group">
            <label for="project_url">Project URL:</label>
            <input type="text" class="form-control" id="project_url" name="project_url">
        </div>
        <button type="submit" class="btn btn-primary">Add Project</button>
    </form>
</div>
</body>
</html>
