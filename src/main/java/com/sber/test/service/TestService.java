package com.sber.test.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
@Log4j2
public class TestService {
    private final String LOCATION = "";

    private String downloadFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Files.copy(file.getInputStream(), Path.of(LOCATION + file.getOriginalFilename()));
        }
        return file.getOriginalFilename();
    }

    public boolean validateFileHash(MultipartFile file, String hash) throws IOException {
        File downloadFile = new File(downloadFile(file));
        if(Files.exists(downloadFile.toPath())) {
            InputStream inputStream = Files.newInputStream(downloadFile.toPath());
            String md5 = DigestUtils.md5Hex(inputStream);
            Files.deleteIfExists(downloadFile.toPath());
            return hash.equals(md5);
        }
        return false;
    }
}

