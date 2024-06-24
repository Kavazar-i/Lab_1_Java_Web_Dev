package com.kalosha.lab.lab_1_web_dev.command.impl;

import com.kalosha.lab.lab_1_web_dev.command.Command;
import com.kalosha.lab.lab_1_web_dev.command.Router;
import com.kalosha.lab.lab_1_web_dev.entity.Project;
import com.kalosha.lab.lab_1_web_dev.exception.CommandException;
import com.kalosha.lab.lab_1_web_dev.service.ProjectService;
import com.kalosha.lab.lab_1_web_dev.service.impl.ProjectServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class AddProjectCommand implements Command {
    private static final String UPLOAD_DIRECTORY = "uploads";
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1 MB
    private static final String[] ALLOWED_FILE_TYPES = {"jpg", "jpeg", "png"};

    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        Part userIdPart = request.getPart("user_id");
        Part titlePart = request.getPart("title");
        Part descriptionPart = request.getPart("description");
        Part projectUrlPart = request.getPart("project_url");
        Part photoPart = request.getPart("photo_filename");

        String userId = new String(userIdPart.getInputStream().readAllBytes());
        String title = new String(titlePart.getInputStream().readAllBytes());
        String description = new String(descriptionPart.getInputStream().readAllBytes());
        String projectUrl = new String(projectUrlPart.getInputStream().readAllBytes());

        Project project = new Project();
        project.setUserId(Integer.parseInt(userId));
        project.setTitle(title);
        project.setDescription(description);
        project.setProjectUrl(projectUrl);

        try {
            // Save the project details first to get the project ID
            project = projectService.addProject(project);
            int projectId = project.getId();

            // Handle file upload
            if (photoPart != null && photoPart.getSize() > 0) {
                String fileName = extractFileName(photoPart);

                if (!isValidFileType(fileName)) {
                    request.setAttribute("errorMessage", "Only JPG, JPEG, and PNG files are allowed.");
                    return new Router("addProject.jsp", Router.Type.FORWARD);
                }

                if (photoPart.getSize() > MAX_FILE_SIZE) {
                    request.setAttribute("errorMessage", "File size exceeds the limit of 1 MB.");
                    return new Router("addProject.jsp", Router.Type.FORWARD);
                }

                // Create upload directory if it doesn't exist
                String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String filePath = uploadPath + File.separator + fileName;
                photoPart.write(filePath);

                // Update project with the uploaded photo filename
                projectService.updatePhotoFilename(projectId, fileName);
            }

            return new Router("profile.jsp", Router.Type.REDIRECT);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Project addition failed. Please try again.");
            return new Router("addProject.jsp", Router.Type.FORWARD);
        }
    }

    // Extracts file name from HTTP header content-disposition
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    // Validates file type based on allowed extensions
    private boolean isValidFileType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        for (String allowedType : ALLOWED_FILE_TYPES) {
            if (allowedType.equals(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
