package com.tanmaysingal.opens3.store;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

  void store(MultipartFile file) throws IOException;

  byte[] fetch(String key) throws IOException;

}
