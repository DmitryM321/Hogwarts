package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }
    public StudentDTO createStudent(StudentDTO studentDTO) {
            Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
            Student student = studentDTO.toStudent();
            student.setFaculty(faculty);
            Student newStudent = studentRepository.save(student);
            return StudentDTO.fromStudent(newStudent);
        }
    public StudentDTO editStudent(StudentDTO studentDTO) {
            Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
            Student student = studentDTO.toStudent();
            student.setFaculty(faculty);
            Student editStudent = studentRepository.save(student);
            return StudentDTO.fromStudent(editStudent);
        }
    public void deleteStudentId(Long id) {
            studentRepository.deleteById(id);
        }

    public StudentDTO getStudentById(Long id) {
            return StudentDTO.fromStudent(studentRepository.findById(id).get());
        }

    public Collection<StudentDTO> getAllStudents() {
            Collection<Student> students = studentRepository.findAll();
            Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public Collection<StudentDTO> findAllByAge(Integer age) {
            Collection<Student> students = studentRepository.findAllByAge(age);
            Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public Collection<StudentDTO> findByAgeBetween(Integer min, Integer max) {
            Collection<Student> students = studentRepository.findByAgeBetween(min, max);
            Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
    public FacultyDTO findFacultyByStudentId(Long id) {
            Faculty faculty = facultyRepository.findById(getStudentById(id).getFacultyId()).get();
            return FacultyDTO.fromFaculty(faculty);
        }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id).get();
    }
}
