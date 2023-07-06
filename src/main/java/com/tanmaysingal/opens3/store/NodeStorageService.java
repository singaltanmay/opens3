package com.tanmaysingal.opens3.store;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Retrofit;

@Service
public class NodeStorageService implements StorageService {

  private static final Logger LOGGER = LogManager.getLogger(NodeStorageService.class);

  public NodeStorageService() {
    Retrofit retrofit = NodeStorageApi.getRetrofitInstance();
  }

  @Override
  public void store(MultipartFile file) {
    NodeStorageApi.store(file);
  }

  @Override
  public byte[] fetch(String key) {
    try {
      return NodeStorageApi.fetch(key);
    } catch (IOException e) {
      LOGGER.error(e);
      return null;
    }
  }
}
