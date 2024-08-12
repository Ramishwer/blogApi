package com.blog.blog.serviceImpl;

import com.blog.blog.services.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name=file.getOriginalFilename();
        log.info("FileName:"+name);

        String randomID= UUID.randomUUID().toString();

        log.info("randomID:"+randomID);

        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));

        log.info("fileName1:"+fileName1);


        String fullPath=path+ File.separator+fileName1;

        File f=new File(path);

        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(fullPath));

        return name;
    }

    @Override
    public InputStream getSource(String path, String fileName) throws FileNotFoundException {

        String fullPath=path+ File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
