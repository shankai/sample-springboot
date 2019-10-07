package io.github.shankai.springboot.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@RestController
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) throws IOException {

		String originFileName = file.getOriginalFilename();
		log.info(">>> origin filename: {}", originFileName);

		File newFile = new File("/var/tmp/springboot/upload/" + originFileName);
		boolean result = newFile.createNewFile();
		log.info(">>> target file create result:{}", result);
		FileOutputStream output = new FileOutputStream(newFile);
		output.write(file.getBytes());
		output.flush();
		output.close();
		return "upload success";
	}

	@RequestMapping(value = "/download")
	public ResponseEntity<Object> download(@RequestParam("fileName") String fileName) throws FileNotFoundException {

		File file = new File("/var/tmp/springboot/upload/" + fileName);
		InputStreamResource isr = new InputStreamResource(new FileInputStream(file));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
		headers.set("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.set("Pragma", "no-cache");
		headers.set("Expires", "0");

		ResponseEntity<Object> entity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(isr);
		return entity;

	}
}
