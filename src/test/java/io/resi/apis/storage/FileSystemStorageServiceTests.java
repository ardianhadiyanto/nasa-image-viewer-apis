package io.resi.apis.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSystemStorageServiceTests {
  @Test
  void store_givenFileNameAndContentBytes_shouldPersistFileWithGivenName(@TempDir final Path dir) {
    final String fileName = "file";
    final byte[] content = "content".getBytes();
    final FileSystemStorageService sut = new FileSystemStorageService(dir.toString());

    sut.store(fileName, content);

    assertTrue(Files.exists(Paths.get(dir.toString() + File.separator + fileName)));
  }

  @Test
  void exists_givenFileNameThatExists_returnTrue(@TempDir final Path dir) {
    final String fileName = "file";
    final byte[] content = "content".getBytes();
    final FileSystemStorageService sut = new FileSystemStorageService(dir.toString());
    sut.store(fileName, content);

    final boolean actual = sut.exists(fileName);

    assertTrue(actual);
  }

  @Test
  void exists_givenNonExistingFileName_returnFalse(@TempDir final Path dir) {
    final FileSystemStorageService sut = new FileSystemStorageService(dir.toString());

    final boolean actual = sut.exists("file");

    assertFalse(actual);
  }

  @Test
  void retrieve_givenExistingFileName_returnFileAsByteArray(@TempDir final Path dir) {
    final String fileName = "file";
    final byte[] content = "content".getBytes();
    final FileSystemStorageService sut = new FileSystemStorageService(dir.toString());
    sut.store(fileName, content);

    final byte[] actual = sut.retrieve(fileName);

    assertThat(actual).isNotEmpty();
  }

  @Test
  void retrieve_givenNonExistingFileName_returnEmptyByteArray(@TempDir final Path dir) {
    final FileSystemStorageService sut = new FileSystemStorageService(dir.toString());

    final byte[] actual = sut.retrieve("file");

    assertThat(actual).isEmpty();
  }
}