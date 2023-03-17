package ru.hogwarts.school.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.DTO.AppInfoDTO;
import ru.hogwarts.school.DTO.FacultyDTO;
import ru.hogwarts.school.model.AppInfo;


@RestController
public class InfoController {

    @Value("${app.env}")
    private String appEnv;

    @GetMapping
    public AppInfoDTO appInfo() {
        AppInfo appInfo = new AppInfo("hogwarts-school", "0.0.1", appEnv);
        return AppInfoDTO.fromAppInfo(appInfo);
    }
}
