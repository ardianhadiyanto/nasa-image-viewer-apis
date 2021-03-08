package io.resi.apis.rover;

import io.resi.apis.date.Date;
import io.resi.apis.date.provider.DateProvider;
import io.resi.apis.rover.provider.RoverProvider;
import io.resi.apis.storage.StorageService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoverServiceTests {
  @Test
  void getRoverImage_whenImageNotExistsInStorage_shouldCallTheProvider() {
    final StorageService storageService = mock(StorageService.class);
    when(storageService.exists(any())).thenReturn(false);
    final RoverProvider roverProvider = mock(RoverProvider.class);
    when(roverProvider.getRoverImage(any())).thenReturn(new byte[0]);
    final RoverService sut = new RoverService(roverProvider, mock(DateProvider.class), storageService);

    sut.getRoverImage("some url");

    verify(roverProvider, times(1)).getRoverImage(any());
  }

  @Test
  void getRoverImage_whenImageNotExistsInStorage_shouldStoreImageToStorageAfterRequestingIt() {
    final StorageService storageService = mock(StorageService.class);
    when(storageService.exists(any())).thenReturn(false);
    final RoverService sut = new RoverService(mock(RoverProvider.class), mock(DateProvider.class), storageService);

    sut.getRoverImage("some url");

    verify(storageService, times(1)).store(any(), any());
  }

  @Test
  void getRoverImage_whenImageExistsInStorage_shouldRetrieveImageFromStorage() {
    final StorageService storageService = mock(StorageService.class);
    when(storageService.exists(any())).thenReturn(true);
    final RoverService sut = new RoverService(mock(RoverProvider.class), mock(DateProvider.class), storageService);

    sut.getRoverImage("some url");

    verify(storageService, times(1)).retrieve(any());
  }

  @Test
  void getAvailableRoverImages_givenRoverAndDate_shouldRequestRoverImage() {
    final RoverProvider roverProvider = mock(RoverProvider.class);
    when(roverProvider.getRovers()).thenReturn(Collections.singletonList("rover"));
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.getAvailableDates()).thenReturn(Collections.singletonList(new Date(3, 8, 2021)));
    final RoverService sut = new RoverService(roverProvider, dateProvider, mock(StorageService.class));

    sut.getAvailableRoverImages();

    verify(roverProvider, times(1)).getRoverImages(any(), any());
  }
}
