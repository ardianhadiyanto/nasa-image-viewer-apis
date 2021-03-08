package io.resi.apis.storage;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
class FileSystemStorageService implements StorageService {
  private static final String DIR_PATH = "storage";

  @PostConstruct
  private void init() {
    final File storageDir = new File(DIR_PATH);
    if (!storageDir.exists()) {
      storageDir.mkdir();
    }
  }

  @Override
  public boolean store(final String fileName, final byte[] imageBytes) {
    final String path = getPath(fileName);
    try {
      FileUtils.writeByteArrayToFile(new File(path), imageBytes);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean exists(final String fileName) {
    final String path = getPath(fileName);
    return new File(path).exists();
  }

  @Override
  public byte[] retrieve(final String fileName) {
    final String path = getPath(fileName);
    final File file = new File(path);
    try {
      return FileUtils.readFileToByteArray(file);
    } catch (IOException e) {
      return new byte[0];
    }
  }

  private String getPath(final String fileName) {
    return DIR_PATH + File.separator + fileName;
  }
}
