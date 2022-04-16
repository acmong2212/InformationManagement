package com.informationmanagement.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Search {
    private String code;
    private String name;
    private LocalDate timeStart;
}
