package com.tanmaysingal.opens3.api;

import com.tanmaysingal.opens3.meta.MetadataService;
import com.tanmaysingal.opens3.models.ObjectMetadata;
import com.tanmaysingal.opens3.store.StorageService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
  private StorageService storageService;

  @Autowired
  private MetadataService metadataService;

  @GetMapping("/")
  public List<ObjectMetadata> listObjects() throws IOException {
    return metadataService.listObjects();
  }

  @PostMapping("/object")
  public ResponseEntity<?> store(@RequestParam("file") MultipartFile file) throws IOException {
    storageService.store(file);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/object/{key}")
  public ResponseEntity<?> fetch(@PathVariable String key) throws IOException {
    byte[] object = storageService.fetch(key);
    if (object == null || object.length == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(object);

  }

}
