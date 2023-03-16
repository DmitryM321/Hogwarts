package ru.hogwarts.school.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.DTO.AppInfoDTO;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.model.AppInfo;

@Slf4j
@RestController
public class InfoController {
    @GetMapping
    public AppInfoDTO appInfo() {
        AppInfo appInfo = new AppInfo("hogwarts-school", "0.0.1", "dev");
        return AppInfoDTO.fromAppInfo(appInfo);
    }
//    public ResponseEntity<AppInfoDTO> getFaculty(@PathVariable Long facultyId) {
//        AppInfoDTO AppInfoDTO = houseService.getFaculty(facultyId);    // ?? метод
//        if (facultyDTO == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(facultyDTO);
//    }
}
