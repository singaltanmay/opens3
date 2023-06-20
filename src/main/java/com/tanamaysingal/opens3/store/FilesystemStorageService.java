package com.tanamaysingal.opens3.store;

import com.tanamaysingal.opens3.meta.MetadataService;
import com.tanamaysingal.opens3.models.ObjectMetadata;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesystemStorageService implements StorageService {

  @Value("${opens3.storage.path}")
  private String drivePath;
  @Autowired
  private MetadataService metadataService;

  public String uploadImageToFileSystem(MultipartFile file) throws IOException {
    String filePath = getFilePath(file);
    ObjectMetadata fileData = metadataService.save(ObjectMetadata.builder().filePath(filePath).objkey(file.getOriginalFilename()).type(file.getContentType()).build());
    file.transferTo(new File(filePath));
    if (fileData != null) {
      return "file uploaded successfully : " + filePath;
    }
    return null;
  }

  public String getFilePath(MultipartFile file) {
    return drivePath + (drivePath.endsWith("/") ? "" : "/") + file.getOriginalFilename();
  }

  public byte[] downloadImageFromFileSystem(String fileName) {
    Optional<ObjectMetadata> fileData = metadataService.findByKey(fileName);
    String filePath = fileData.get().getFilePath();
    byte[] images = new byte[0];
    try {
      images = Files.readAllBytes(new File(filePath).toPath());
    } catch (IOException e) {
      return null;
    }
    return images;
  }

}
