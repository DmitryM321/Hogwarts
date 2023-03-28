package ru.hogwarts.school.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final Faculty faculty = new Faculty();
    private final Student student  = new Student();

    @BeforeEach
    public void setUp() {
        faculty.setName("Ravenclaw");
        faculty.setColor("blue ");
        facultyRepository.save(faculty);
        student.setName("Bazilio");
        student.setAge(44);
        student.setFaculty(faculty);
        studentRepository.save(student);
    }
    @Test
    void print() {
    System.out.println(student);
    }
    @AfterEach
    public void resetDb () {
        studentRepository.deleteAll();
        }
    @Test
    void whenGetStudentById() throws Exception {
        mockMvc.perform(get("/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andExpect(jsonPath("$.facultyId").value(student.getFaculty().getFacultyId()));
    }
    @Test
    void whenCreateStudent() throws Exception {
            mockMvc.perform(post( "/students")
//                .content(objectMapper.writeValueAsString(student))
                .content(objectMapper.writeValueAsString(StudentDTO.fromStudent(student)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andExpect(jsonPath("$.facultyId").value(student.getFaculty().getFacultyId()));
        }
    @Test
    void whenEditStudent() throws Exception {
                mockMvc.perform(put("/students")
                .content(objectMapper.writeValueAsString(StudentDTO.fromStudent(student)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andExpect(jsonPath("$.facultyId").value(student.getFaculty().getFacultyId()));
        }
    @Test
    void whenDeleteStudentId() throws Exception {
            mockMvc.perform(delete("/students/{id}", student.getId()))
                    .andExpect(status().isOk());
          }
    @Test
    void whenFindFacultyByStudentId() throws Exception {
        mockMvc.perform(get("/students/" + student.getId() + "/faculty"))  //("{id}/faculty")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }
    @Test
    void whenGetCountStudent() throws Exception {
            mockMvc.perform(get("/students/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
    @Test
    void WhenGetAverageAgeStudents() throws Exception {
        mockMvc.perform(get("/students/avg"))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void whenGetYoungestStudents() throws Exception {
        mockMvc.perform(get("/students/youngest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}

