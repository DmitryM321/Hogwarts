package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;

@RequestMapping("faculty")
@RestController
public class HouseController {
    private final HouseService houseService;
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long facultyId) {
        FacultyDTO facultyDTO = houseService.getFaculty(facultyId);    // ?? метод
        if (facultyDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }
    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyDTO facultyDTO) {
        return houseService.createFaculty(facultyDTO);
    }
    @PutMapping
    public ResponseEntity<FacultyDTO> editFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO foundFaculty = houseService.editFaculty(facultyDTO);
        if(foundFaculty == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
        houseService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/facultes")
    public ResponseEntity<Collection<FacultyDTO>> findFaculties(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String name) {
        if(color != null && !color.isBlank()) {
            return ResponseEntity.ok(houseService.findAllByColor(color));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(houseService.findByNameIgnoreCase(name));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{facultyId}/students")
    public ResponseEntity<Collection<StudentDTO>> findStudentsByFacultyId (@PathVariable Long facultyId) {
        return ResponseEntity.ok(houseService.findStudentsByFacultyId(facultyId));
    }

    public ResponseEntity<Collection<FacultyDTO>> findAllByColor(@RequestParam String color){
        return ResponseEntity.ok(houseService.findAllByColor(color));
    }
    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> findByNameIgnoreCase(@RequestParam String name) {
        return ResponseEntity.ok(houseService.findByNameIgnoreCase(name));
    }
}



