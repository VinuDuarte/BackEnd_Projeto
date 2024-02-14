package com.example.tutorialv2.service;


import com.example.tutorialv2.config.FileStorageConfig;
import com.example.tutorialv2.exceptions.FileStorageException;
import com.example.tutorialv2.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

        private final Path fileStorageLocation;

        @Autowired
        public FileStorageService(FileStorageConfig config){
            Path path = Paths.get(config.getUploadDir())
                    .toAbsolutePath().normalize();

            this.fileStorageLocation = path;

            try{
                Files.createDirectories(this.fileStorageLocation);
            } catch(Exception e){
                throw new FileStorageException("Não foi possivel criar o diretorio", e);
            }
        }

      public String storeFile(MultipartFile file) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try{
                //Regra pra validar nome do arquivos com caracter ESPECIAIS
                    if (fileName.contains(" ") || fileName.matches("[!#@$%¨&*].*")){
                        throw new FileStorageException("Arquivo invalido");
                    }

                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return fileName;

            }catch (Exception e){
                throw new FileStorageException("Não foi possivel armazenar o arquivo: " + fileName, e);
            }
      }

      public Resource loadFileAsResouce (String filename) {
            try {
                Path filePath = this.fileStorageLocation.resolve(filename).normalize();
                Resource resource = new UrlResource(filePath.toUri());

                if (resource.exists()) return resource;
                else throw new MyFileNotFoundException("Arquivo não encontrado");

            } catch (Exception e){
                throw new MyFileNotFoundException("Arquivo: " + filename  + " não encontrado", e);
            }
      }












}

