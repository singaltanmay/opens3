package com.tanamaysingal.opens3.store;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {


  String uploadImageToFileSystem(MultipartFile file) throws IOException;

  String getFilePath(MultipartFile file);

  byte[] downloadImageFromFileSystem(String fileName) throws IOException;

}
