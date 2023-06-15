package com.tanamaysingal.opens3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectMetadata {

  // Automatically generated E-tag
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(nullable = false)
  private String etag;
  // File path + name + extension (eg: photos/mydog.png)
  private String objkey;
  // Filetype (extension)
  private String type;
  // Size in Bytes
  private Long size;
  // TODO: Replace with node-level storage controller
  // File path on disk
  private String filePath;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    final ObjectMetadata that = (ObjectMetadata) o;
    return etag != null && Objects.equals(etag, that.etag);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
