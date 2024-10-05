package com.example.s3FileUploader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example"} )
@Slf4j
public class S3FileUploaderApplication {
	@Autowired
	private S3Client s3Client;;

	public static void main(String[] args) {
		SpringApplication.run(S3FileUploaderApplication.class, args);
	}


	@Bean
	public CommandLineRunner run() {
		return args -> {
			if (args.length > 0) {
				String filePath = args[0];  // Capture the first argument (file name)
				System.out.println("Uploading file: " + filePath);
				Path path = Paths.get(filePath);
				File file = path.toFile();
				PutObjectRequest putObjectRequest = PutObjectRequest.builder()
						.bucket("k8slab8")
						.key(file.getName())
						.build();

				s3Client.putObject(putObjectRequest, path);
				log.info("File {} uploaded successfully.", file.getName());
			} else {
				log.error("Please provide the file path as an argument.");
			}
		};
	}

}
