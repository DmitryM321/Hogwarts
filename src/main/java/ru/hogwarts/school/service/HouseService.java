package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class HouseService {
    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty newFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(newFaculty);
    }

    public FacultyDTO editFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        Faculty editFaculty = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(editFaculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public FacultyDTO getFaculty(Long facultyId) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(facultyId).get());
    }

    public Collection<FacultyDTO> findAllFaculties() {
        Collection<Faculty> faculties = facultyRepository.findAll();
        Collection<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
            facultyDTOs.add(facultyDTO);
        }
        return facultyDTOs;
    }

    public Collection<FacultyDTO> findAllByColor(String color) {
        Collection<Faculty> faculties = facultyRepository.findAllByColor(color);
        Collection<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
            facultyDTOs.add(facultyDTO);
        }
        return facultyDTOs;
    }
    public Collection<FacultyDTO> findByNameIgnoreCase(String name) {
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(name);
        Collection<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty faculty : faculties) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
            facultyDTOs.add(facultyDTO);
        }
        return facultyDTOs;
    }
    public Collection<StudentDTO> findStudentsByFacultyId(Long id){
            List<Student> students = facultyRepository.findById(id).get().getStudents();
            List<StudentDTO> studentsDTO = new ArrayList<>();
            for (Student student : students) {
                StudentDTO studentDTO = StudentDTO.fromStudent(student);
                studentsDTO.add(studentDTO);
            }
            return studentsDTO;
        }
    }




