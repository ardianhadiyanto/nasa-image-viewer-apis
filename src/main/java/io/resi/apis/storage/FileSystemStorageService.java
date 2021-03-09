package io.resi.apis.storage;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
class FileSystemStorageService implements StorageService {
  private final String baseStoragePath;

  FileSystemStorageService(@Value("${base.storage.path}") final String baseStoragePath) {
    this.baseStoragePath = baseStoragePath;
    initializeStorage();
  }

  private void initializeStorage() {
    final File storageDir = new File(baseStoragePath);
    if (!storageDir.exists()) {
      storageDir.mkdir();
    }
  }

  @Override
  public void store(final String fileName, final byte[] imageBytes) {
    final String path = getPath(fileName);
    try {
      FileUtils.writeByteArrayToFile(new File(path), imageBytes);
    } catch (IOException e) {
    }
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
    return baseStoragePath + File.separator + fileName;
  }
}
