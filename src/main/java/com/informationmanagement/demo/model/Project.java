package com.informationmanagement.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private Long teamSize;

    private String namePartner;

    private LocalDate timeStart;

    private LocalDate timeFinish;

    @ManyToOne
    private ProjectStatus projectStatus;
}
