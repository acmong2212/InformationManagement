package com.informationmanagement.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Search {
    private String codeProject;
    private String nameProject;
    private LocalDate timeStart;
}
