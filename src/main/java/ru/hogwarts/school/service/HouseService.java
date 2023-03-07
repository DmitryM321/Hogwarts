package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class HouseService {
    private Map<Long, Faculty> faculties = new HashMap<>();
    private Long generatedFacultytId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculties.put(generatedFacultytId, faculty);
        generatedFacultytId++;
        return faculty;
    }
    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        faculties.put(facultyId, faculty);
        return faculty;
    }

    public Faculty deleteFacultyId(Long facultyId) {
        return faculties.remove(facultyId);
    }
}


