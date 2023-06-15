package com.tanamaysingal.opens3.meta;

import com.tanamaysingal.opens3.models.ObjectMetadata;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

  @Autowired
  private MetadataRepository metadataRepository;

  public ObjectMetadata store(ObjectMetadata metadata) {
    return metadataRepository.save(metadata);
  }

  public Optional<ObjectMetadata> findByName(String fileName) {
    return metadataRepository.findByObjkey(fileName);
  }
}
