package com.homework.springboothomework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RequestMapping()
@RestController
public class ProjectController {

    @GetMapping("/")
    public String startPage(){
        return "Приложение запущено";
    }

    private ProjectInfo projectInfo;
    @Autowired
    public ProjectController(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }
    @GetMapping("/info")
    private List<ProjectInfo> getInfo() {
        return List.of(new ProjectInfo(
                "Иван Пасхин",
                "HomeWorkSpringBootApplication",
                LocalDate.of(2023, Month.JANUARY, 10),
                "Проект по обучению Spring Boot."
        ));
    }
}
