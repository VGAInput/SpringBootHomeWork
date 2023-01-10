package com.homework.springboothomework.controllers;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjectInfo {

    private String studentName;
    private String projectName;
    private LocalDate projectCreationDate;
    private String projectDescription;

    public ProjectInfo(){

    }

    public ProjectInfo(String studentName, String projectName, LocalDate projectCreationDate, String projectDescription) {
        this.studentName = studentName;
        this.projectName = projectName;
        this.projectCreationDate = projectCreationDate;
        this.projectDescription = projectDescription;
    }

    public LocalDate getProjectCreationDate() {
        return projectCreationDate;
    }

    public void setProjectCreationDate(LocalDate projectCreationDate) {
        this.projectCreationDate = projectCreationDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "studentName='" + studentName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectCreationDate='" + projectCreationDate + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
