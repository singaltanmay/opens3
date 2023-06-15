package com.tanamaysingal.opens3.api;

import com.tanamaysingal.opens3.store.StorageService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OpenS3Controller {
  @Autowired
  private StorageService service;

  @PostMapping("/fileSystem")
  public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
    String uploadImage = service.uploadImageToFileSystem(file);
    return ResponseEntity.status(HttpStatus.OK)
        .body(uploadImage);
  }

  @GetMapping("/fileSystem/{fileName}")
  public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
    byte[] imageData=service.downloadImageFromFileSystem(fileName);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/png"))
        .body(imageData);

  }

}
