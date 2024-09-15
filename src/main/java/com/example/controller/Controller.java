package com.example.controller;

import com.example.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;

import java.io.IOException;

@RestController
@RequestMapping("/api/s3")
@Slf4j
public class Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload/{bucketName}")
    public ResponseEntity<?> uploadFile(@PathVariable String bucketName,
                                        @RequestParam("file") MultipartFile file) {

        try {
            return ResponseEntity.ok(s3Service.uploadFile(file, bucketName));
        } catch (AwsServiceException | IOException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/create-bucket/{bucketName}")
    public ResponseEntity<?> createBucket(@PathVariable String bucketName) {
        try {
            return ResponseEntity.ok(s3Service.createBucket(bucketName));
        } catch (AwsServiceException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @GetMapping("/delete-bucket/{bucketName}")
    public ResponseEntity<?> deleteBucket(@PathVariable String bucketName) {
        try {
            return ResponseEntity.ok(s3Service.deleteBucket(bucketName));
        } catch (AwsServiceException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

}

