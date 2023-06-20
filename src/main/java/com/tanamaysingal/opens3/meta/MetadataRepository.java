package com.tanamaysingal.opens3.meta;

import com.tanamaysingal.opens3.models.ObjectMetadata;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadataRepository extends JpaRepository<ObjectMetadata, UUID> {

  Optional<ObjectMetadata> findByObjkey(String fileName);

  List<ObjectMetadata> findAll();
}
