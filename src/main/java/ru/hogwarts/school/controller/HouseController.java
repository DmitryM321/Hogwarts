package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.HouseService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("faculty")
@RestController
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getStudent(@PathVariable Long id) {
        Faculty faculty = houseService.getfacultyById(id);    // ?? метод
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return houseService.createFaculty(faculty);
    }
    @PutMapping()
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = houseService.editFaculty(faculty);
        if(foundFaculty == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return houseService.deleteFaculty(id);
    }
    @GetMapping
    public ResponseEntity<Collection<Faculty>> filerColorFaculty(@RequestParam String color){
        return ResponseEntity.ok(houseService.filerColorFaculty(color).stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList()));
    }
    }

