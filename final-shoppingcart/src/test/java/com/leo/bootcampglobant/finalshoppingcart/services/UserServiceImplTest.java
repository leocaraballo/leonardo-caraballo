package com.leo.bootcampglobant.finalshoppingcart.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.CannotChangeUserException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.models.User;
import com.leo.bootcampglobant.finalshoppingcart.repositories.ShoppingCartRepository;
import com.leo.bootcampglobant.finalshoppingcart.repositories.UserRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplTest {
  @InjectMocks
  private UserServiceImpl userService;
  @Mock
  private UserRepository mockedUserRepository;
  @Mock
  private ShoppingCartRepository mockedShoppingCartRepository;

  private static final List<User> testUsers = Arrays.asList(
      new User(1L, Collections.emptyList(), "Eamon", "Andrew", "eamondrew", "password", "eamondrew@gmail.com"),
      new User(2L, Collections.emptyList(), "Celine", "Monaghan", "celinee", "asdf1234", "ceeel@hotmail.com"),
      new User(3L, Collections.emptyList(), "Ivor", "Andrew", "theandrews", "random64", "drewsdrews@yahoo.com"),
      new User(4L, Collections.emptyList(), "Celine", "Fitzgerald", "fitzboss", "7jkj78f_s2", "fitz90@gmail.com")
  );

  @Before
  public void initialize() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getUserById_correctRetrieve() {
    when(mockedUserRepository.findById(anyLong())).thenReturn(Optional.of(testUsers.get(0)));

    assertSame(testUsers.get(0), userService.getUserById(1L));
    verify(mockedUserRepository).findById(1L);
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserById_userNotFound() {
    when(mockedUserRepository.findById(anyLong())).thenReturn(Optional.empty());

    try {
      userService.getUserById(1L);
    } finally {
      verify(mockedUserRepository).findById(1L);
    }
  }

  @Test
  public void getUserByUsername_correctRetrieve() {
    when(mockedUserRepository.findByUsername(anyString()))
        .thenReturn(Optional.of(testUsers.get(0)));

    User retrievedUser = userService.getUserByUsername("eamondrew");
    assertSame(testUsers.get(0), retrievedUser);
    assertEquals("eamondrew", retrievedUser.getUsername());
    verify(mockedUserRepository).findByUsername("eamondrew");
  }

  @Test(expected = UserNotFoundException.class)
  public void getUserByUsername_userNotFound() {
    when(mockedUserRepository.findByUsername(anyString())).thenReturn(Optional.empty());

    try {
      userService.getUserByUsername("non-existing__");
    } finally {
      verify(mockedUserRepository).findByUsername("non-existing__");
    }
  }

  @Test
  public void getUserByFirstName_correctRetrieve() {
    List<User> expectedUsers = Arrays.asList(
        testUsers.get(1), testUsers.get(3)
    );
    when(mockedUserRepository.findByFirstName(anyString()))
        .thenReturn(expectedUsers);

    List<User> retrievedUsers = userService.getUserByFirstName("Celine");
    assertEquals(expectedUsers, retrievedUsers);
    for (User user : retrievedUsers) {
      assertEquals("Celine", user.getFirstName());
    }
    verify(mockedUserRepository).findByFirstName("Celine");
  }

  @Test
  public void getUserByFirstName_noUsersFoundEmptyList() {
    when(mockedUserRepository.findByFirstName(anyString())).thenReturn(Collections.emptyList());

    assertTrue(userService.getUserByFirstName("non-existing__").isEmpty());
    verify(mockedUserRepository).findByFirstName("non-existing__");
  }

  @Test
  public void getUserByLastName_correctRetrieve() {
    List<User> expectedUsers = Arrays.asList(
        testUsers.get(0), testUsers.get(2)
    );
    when(mockedUserRepository.findByLastName(anyString()))
        .thenReturn(expectedUsers);

    List<User> retrievedUsers = userService.getUserByLastName("Andrew");
    assertEquals(expectedUsers, retrievedUsers);
    for (User user : retrievedUsers) {
      assertEquals("Andrew", user.getLastName());
    }
    verify(mockedUserRepository).findByLastName("Andrew");
  }

  @Test
  public void getUserByLastName_noUsersFoundEmptyList() {
    when(mockedUserRepository.findByLastName(anyString())).thenReturn(Collections.emptyList());

    assertTrue(userService.getUserByLastName("non-existing__").isEmpty());
    verify(mockedUserRepository).findByLastName("non-existing__");
  }

  @Test
  public void getAllUsers_correctRetrieve() {
    when(mockedUserRepository.findAll()).thenReturn(testUsers);

    assertEquals(testUsers, userService.getAllUsers());
    verify(mockedUserRepository).findAll();
  }

  @Test
  public void createUser_correctCreation() {
    when(mockedUserRepository.save(any())).then(e -> {
      when(mockedUserRepository.findByUsername(anyString()))
          .thenReturn(Optional.of(e.getArgument(0)));
      return e.getArgument(0);
    });

    assertEquals(testUsers.get(0), userService.createUser(testUsers.get(0)));
    assertEquals(testUsers.get(0), userService.getUserByUsername("eamondrew"));

    verify(mockedUserRepository).save(testUsers.get(0));
    verify(mockedUserRepository).findByUsername("eamondrew");
    verify(mockedShoppingCartRepository).save(any());
  }

  @Test
  public void replaceUser_correctUpdate() {
    User updatedUser = new User(1L, null, "George",
        "Harrison", null, "password", "email@mail.com");
    User expectedUser = new User(1L, Collections.emptyList(), "George",
        "Harrison", "eamondrew", "password", "email@mail.com");

    when(mockedUserRepository.findById(1L)).thenReturn(Optional.of(testUsers.get(0)));
    when(mockedUserRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

    assertEquals(expectedUser, userService.replaceUser(updatedUser));
    verify(mockedUserRepository).save(expectedUser);
  }

  @Test(expected = UserNotFoundException.class)
  public void replaceUser_userNotFound() {
    User updatedUser = new User(1L, Collections.emptyList(), "George",
        "Harrison", null, "password", "email@mail.com");

    when(mockedUserRepository.findById(anyLong())).thenReturn(Optional.empty());

    try {
      userService.replaceUser(updatedUser);
    } finally {
      verify(mockedUserRepository, never()).save(any());
    }
  }

  @Test(expected = CannotChangeUserException.class)
  public void replaceUser_noUsernameChange() {
    User updatedUser = new User(1L, Collections.emptyList(), "George",
        "Harrison", "admin", "password", "email@mail.com");

    try {
      userService.replaceUser(updatedUser);
    } finally {
      verify(mockedUserRepository, never()).save(any());
    }
  }

  @Test
  public void deleteUserById_correctDeletion() {
    assertTrue(userService.deleteUserById(1L));
    verify(mockedUserRepository).deleteById(1L);
  }

  @Test(expected = UserNotFoundException.class)
  public void deleteUserById_userNotFound() {
    doThrow(RuntimeException.class).when(mockedUserRepository).deleteById(anyLong());
    assertTrue(userService.deleteUserById(1L));
    verify(mockedUserRepository).deleteById(1L);
  }
}
