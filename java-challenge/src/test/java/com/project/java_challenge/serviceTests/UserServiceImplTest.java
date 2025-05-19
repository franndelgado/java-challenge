package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.UserRegisterDTO;
import com.project.java_challenge.models.Role;
import com.project.java_challenge.models.User;
import com.project.java_challenge.repositories.RoleRepository;
import com.project.java_challenge.repositories.UserRepository;
import com.project.java_challenge.security.TokenJwtConfig;
import com.project.java_challenge.services.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Role roleAdmin;
    private Role roleUser;

    @BeforeEach
    void setUp() {
        roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        roleUser = new Role();
        roleUser.setName("ROLE_USER");
    }

    @Test
    void shouldReturnAllUsers(){
        List<User> users = List.of(new User(1L, "user1", "password123"));

        when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUsername());
    }

    @Test
    void shouldCheckIfUserExists() {
        when(userRepository.existsByUsername("user1")).thenReturn(true);
        assertTrue(userService.existsByUsername("user1"));
    }

    @Test
    void shouldThrowExceptionIfUsernameExists() {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("user1");

        when(userRepository.existsByUsername("user1")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(dto));
    }

    @Test
    void shouldSaveUserWithRoleAndEncodedPassword() {
        User user = new User(1L, "user1", "password123");
        user.setAdmin(true);

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(roleAdmin));
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        User savedUser = userService.save(user);

        assertEquals("encodedPassword123", savedUser.getPassword());
        assertEquals(2, savedUser.getRoles().size());
    }

    @Test
    void shouldThrowExceptionIfRoleAdminNotFound() {
        UserRegisterDTO dto = new UserRegisterDTO("user1", "password123", true);

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));

        assertThrows(IllegalArgumentException.class,
                () -> userService.registerUser(dto));
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        UserRegisterDTO dto = new UserRegisterDTO("user1", "password123", true);

        when(userRepository.existsByUsername("user1")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(roleAdmin));
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        User result = userService.registerUser(dto);

        assertEquals("encodedPassword123", result.getPassword());
        assertEquals(2, result.getRoles().size());
        assertEquals("user1", result.getUsername());
    }

    @Test
    void shouldGenerateValidToken() {
        String token = Jwts.builder()
                .subject("testUser")
                .signWith(TokenJwtConfig.SECRET_KEY)
                .compact();
        assertNotNull(token);
    }
}