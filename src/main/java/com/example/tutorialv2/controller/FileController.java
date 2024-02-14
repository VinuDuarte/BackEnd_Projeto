package com.example.tutorialv2.controller;


import com.example.tutorialv2.data.vo.v1.UploadFileResponseVo;
import com.example.tutorialv2.service.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Tag(name = "File Enpoints")
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private Logger logger = Logger.getLogger(FileController.class.getName());

    @Autowired
    private FileStorageService service;


    @PostMapping(value = "/uploadFile")
    public UploadFileResponseVo uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Store dos arquivos em Disco!!!!");

        var filename = service.storeFile(file);
        String fileDonloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/downloadFile/")
                .path(filename)
                .toUriString();

        return
                new UploadFileResponseVo(
                        filename,
                        fileDonloadUri,
                        file.getContentType(),
                        file.getSize());
    }


    @PostMapping(value = "/uploadMultipleFiles")
    public List<UploadFileResponseVo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        logger.info("Store dos arquivos em Disco!!!!");

        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/downloadFile/{filename:.+}")
    public ResponseEntity<Resource> downloadFile (@PathVariable String filename, HttpServletRequest request) {
        logger.info("Downloadd do arquivo: " + filename);

        Resource resource = service.loadFileAsResouce(filename);
        String contentType = "";

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e){
            logger.info("nÃO foi possivel baixar o arquivo");
        }

        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

      }

}
