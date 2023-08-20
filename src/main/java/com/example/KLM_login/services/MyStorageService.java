package com.example.KLM_login.services;

import com.example.KLM_login.models.DatabaseFile;
import com.example.KLM_login.repositories.DatabaseFileRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class MyStorageService implements StorageService {

    private final DatabaseFileRepository fileRepository;
    private final StringEncryptor encryptor;

    @Autowired
    public MyStorageService(DatabaseFileRepository fileRepository, StringEncryptor encryptor) {
        this.fileRepository = fileRepository;
        this.encryptor = encryptor;
    }

    @Override
    public void init() {
    }

    @Override
    public void store(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        byte[] data = file.getBytes();

        if(!Objects.equals(fileType, "application/octet-stream")) {
            String content = new String(data, StandardCharsets.UTF_8);
            String encryptedContent = encryptor.encrypt(content);
            byte[] encryptedData = encryptedContent.getBytes(StandardCharsets.UTF_8);

            DatabaseFile dbFile = new DatabaseFile();
            dbFile.setFileName(fileName);
            dbFile.setFileType(fileType);
            dbFile.setData(encryptedData);

            fileRepository.save(dbFile);
        };
    }
    @Override
    public Stream<Path> loadAll(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Path load(String filename){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Resource loadAsResource(String filename){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void deleteAll(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
