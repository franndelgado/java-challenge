package com.project.java_challenge.controllerTests;

import java.util.List;

import com.project.java_challenge.dtos.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.project.java_challenge.controllers.UserController;
import com.project.java_challenge.models.User;
import com.project.java_challenge.services.UserService;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldReturnListOfUsers() throws Exception{
        User user = new User(1L, "user1", "password123");
        List<User> users = List.of(user);

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$[0].username").value("user1"));
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        User user = new User(1L, "user1", "password123");
        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                       "username": "user1",
                       "password": "password123"
                }      
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    void shouldReturnBadRequestWhenUserIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                       "username": "",
                       "password": ""
                }      
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.password").exists());
    }

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO("user1", "password123", true);
        User user = new User(1L, "user1", "password123");

        when(userService.registerUser(any(UserRegisterDTO.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("user1"));
    }

    @Test
    void shouldReturnBadRequestWhenRegisterWithInvalidDTO() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                       "username": "",
                       "password": ""
                }      
                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.password").exists());
    }

    @Test
    void shouldReturnBadRequestWhenUsernameAlreadyExist() throws Exception {
        UserRegisterDTO dto = new UserRegisterDTO("user1", "password123", true);

        when(userService.registerUser(any(UserRegisterDTO.class))).thenThrow(new IllegalArgumentException("Invalid user"));

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.username").value("Invalid user"));

    }
}