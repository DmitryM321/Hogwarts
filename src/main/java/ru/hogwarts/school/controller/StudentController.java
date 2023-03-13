package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.DTO.StudentDTO;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
//        StudentDTO studentDTO = studentService.getStudentById(id);    // ?? метод
//        if (studentDTO == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(studentDTO);
//    }
//
//    @PostMapping
//    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
//        return studentService.createStudent(studentDTO);
//    }
//    @PutMapping
//    public ResponseEntity<StudentDTO> editStudent(@RequestBody StudentDTO studentDTO) {
//        StudentDTO foundStudent = studentService.editStudent(studentDTO);
//        if (foundStudent == null) {
//            ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(foundStudent);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity deleteStudentId(@PathVariable Long id) {
//        studentService.deleteStudentId(id);
//        return ResponseEntity.ok().build();
//    }
////    @GetMapping
////    public ResponseEntity<Collection<Student>> findAllByAge(@RequestParam int age) {
////        return ResponseEntity.ok(studentService.findAllByAge(age));
////    }
////    @GetMapping
////    public ResponseEntity<Collection<Student>> findByAgeBetween(
////            @RequestParam(required = false) int min, int max) {
////        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
////    }
//    @GetMapping
//    public ResponseEntity<Collection<StudentDTO>> getStudents(
//                @RequestParam(required = false) Integer age,
//                @RequestParam(required = false) Integer min,
//                @RequestParam(required = false) Integer max) {
//            if (age != 0) {
//                return ResponseEntity.ok(studentService.findAllByAge(age));
//            }
//            if (min != 0 && max != 0 && max > min) {
//                return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
//            }
//        return ResponseEntity.notFound().build();
//        }
    @GetMapping("{id}/faculty")
    public ResponseEntity<FacultyDTO> findFacultyByStudentId(Long id) {
       return ResponseEntity.ok(studentService.findFacultyByStudentId(id));
    }
    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upLoadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws Exception{
        if(avatar.getSize() >= 1024*300){
        return ResponseEntity.badRequest().body("Big file, not good");
        }
        avatarService.upLoadAvatar(id, avatar);
        return ResponseEntity.ok().build();

    }
    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downLoadAvatar(@PathVariable Long id){
        Avatar avatar = avatarService.findAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
    @GetMapping(value = "/{id}/avatar")
    public void downLoadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();){
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}



