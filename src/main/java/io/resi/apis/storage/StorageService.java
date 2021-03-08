package io.resi.apis.storage;

public interface StorageService {
  boolean store(final String fileName, final byte[] image);
  boolean exists(final String fileName);
  byte[] retrieve(final String fileName);
}
