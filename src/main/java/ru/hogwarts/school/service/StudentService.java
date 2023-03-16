package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    public static final Logger logger = LoggerFactory.getLogger(StudentService.class.getName());

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("Create new student");
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student newStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(newStudent);
    }
    public StudentDTO editStudent(StudentDTO studentDTO) {
        logger.info("Edit student");
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFaculty(faculty);
        Student editStudent = studentRepository.save(student);
        return StudentDTO.fromStudent(editStudent);
    }
    public void deleteStudentId(Long id) {
        logger.info("Delete student");
        studentRepository.deleteById(id);
    }

    public StudentDTO getStudentById(Long id) {
        logger.info("Get student by id");
        return StudentDTO.fromStudent(studentRepository.findById(id).get());
    }

    public Collection<StudentDTO> getAllStudents() {
        logger.info("Get get all students");
            Collection<Student> students = studentRepository.findAll();
            Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public Collection<StudentDTO> findAllByAge(Integer age) {
        logger.info("Get students by age");
        Collection<Student> students = studentRepository.findAllByAge(age);
        Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public Collection<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        logger.info("Get students between minimal and maxmimal age");
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }

    public FacultyDTO findFacultyByStudentId(Long id) {
        logger.info("Find faculty by student id");
        Faculty faculty = facultyRepository.findById(getStudentById(id).getFacultyId()).get();
        return FacultyDTO.fromFaculty(faculty);
    }

    public Student findStudentById(Long id) {
        logger.info("Find student by id");
        return studentRepository.findById(id).get();
    }

    public Long getCountStudent() {
        logger.info("Get students count");
        return studentRepository.getCountStudent();
    }

    public Long getAverageAgeStudents() {
        logger.info("Get average age students");
        return studentRepository.getAverageAgeStudents();
    }

    public Collection<StudentDTO> getYoungestStudents() {
        logger.info("Get youngest students");
        Collection<Student> students = studentRepository.getYoungestStudents();
        Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public List<StudentDTO> findAll(Integer pageNumber, Integer pageSize){
        logger.info("Find all students");
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            return studentRepository.findAll(pageRequest).getContent()
                    .stream().map(StudentDTO::fromStudent)
                    .collect(Collectors.toList());
        }
    }



