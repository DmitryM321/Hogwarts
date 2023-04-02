package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hogwarts.school.config.ConfigDocker;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Testcontainers
class HouseControllerTest extends ConfigDocker {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final Faculty faculty = new Faculty();

    @BeforeEach
    public void setUp() {
        faculty.setName("Gryffindor");
        faculty.setColor("red");
        facultyRepository.save(faculty);
    }
    @AfterEach
    public void resetDb() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
    }
    @Test
    void whenGetFaculty() throws Exception {
        mockMvc.perform(get("/faculty/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
           //     .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }
    @Test
    void whenCreateFaculty() throws Exception {
        mockMvc.perform(post( "/faculty")
                .content(objectMapper.writeValueAsString(faculty))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andExpect(jsonPath("$.name").value(faculty.getName()));
    }
    @Test
    void whenEditFaculty() throws Exception {
        mockMvc.perform(put("/faculty")
                .content(objectMapper.writeValueAsString(faculty))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andExpect(jsonPath("$.name").value(faculty.getName()));
    }
    @Test
    void deleteFacultyById() throws Exception {
        mockMvc.perform(delete("/faculty/" + faculty.getFacultyId()))
                .andExpect(status().isOk());
    }
    @Test
    void whenFindStudentsByFacultyId() throws Exception{
        mockMvc.perform(get("/faculty/" + faculty.getFacultyId() + "/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    @Test
    void whenFindByNameIgnoreCase() throws Exception {
        mockMvc.perform(get("/faculty?name=" + faculty.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
                .andExpect(jsonPath("$[0].color").value(faculty.getColor()));
    }
}