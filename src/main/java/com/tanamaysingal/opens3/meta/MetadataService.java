package com.tanamaysingal.opens3.meta;

import com.tanamaysingal.opens3.models.ObjectMetadata;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

  @Autowired
  private MetadataRepository metadataRepository;

  public ObjectMetadata save(ObjectMetadata metadata) {
    return metadataRepository.save(metadata);
  }

  public List<ObjectMetadata> listObjects() {
    return metadataRepository.findAll();
  }

  public Optional<ObjectMetadata> findByKey(String key) {
    return metadataRepository.findByObjkey(key);
  }
}
