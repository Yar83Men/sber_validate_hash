package com.sber.test.controller;

import com.sber.test.service.TestService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "basicAuth")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Тестовое задание СБЕР", version = "v1"))
public class TestController {
    private final TestService service;

    @PostMapping(path = "/validate-hash", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean uploadFileAndValidateHash(@RequestParam("file") MultipartFile file,
                                             @RequestParam("hash") String hash) throws IOException {
        return service.validateFileHash(file, hash);
    }
}
