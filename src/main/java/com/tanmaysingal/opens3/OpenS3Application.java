package com.tanmaysingal.opens3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenS3Application {

  public static String STORAGE_NODE_BASE_URL;

  public static void main(String[] args) {
    SpringApplication.run(OpenS3Application.class, args);
  }

  @Value("${opens3.storagenode.url}")
  private void setStorageNodeBaseUrl(String baseUrl) {
    baseUrl = baseUrl.trim();
    if (!baseUrl.endsWith("/")) {
      baseUrl += "/";
    }
    OpenS3Application.STORAGE_NODE_BASE_URL = baseUrl;
  }

}
