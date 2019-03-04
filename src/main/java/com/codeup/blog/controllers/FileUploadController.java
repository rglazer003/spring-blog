package com.codeup.blog.controllers;


import com.codeup.blog.FileUploadRepository;
import com.codeup.blog.models.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
@Controller
public class FileUploadController {

    private final FileUploadRepository uploadDao;

    public FileUploadController(FileUploadRepository uploadDao){
        this.uploadDao = uploadDao;
    }

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("/fileupload")
    public String showUploadFileForm() {
        return "fileupload";
    }

    @PostMapping("/fileupload")
    public String saveFile(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
            Upload upload = new Upload(filename);
            uploadDao.save(upload);
            model.addAttribute("message", "File successfully uploaded!");
            model.addAttribute("check", true);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", e);
            model.addAttribute("check", false);
        }
        return "uploadStatus";
    }

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("uploads", uploadDao.findAll());
        return "uploadGallery";
    }
}
