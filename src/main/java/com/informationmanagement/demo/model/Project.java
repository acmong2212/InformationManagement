package com.informationmanagement.demo.model;

import com.informationmanagement.demo.dto.response.ProjectDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Min(value = 2, message = "Code phai lon hon 2 ki tu")
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "team_size")
    private long teamSize;

    @Column(name = "name_partner")
    private String namePartner;

    @Column(name = "time_start")
    private LocalDate timeStart;

    @Column(name = "time_finish")
    private LocalDate timeFinish;

    @Column(name = "is_deleted")
    private boolean deleted;

    @ManyToOne
    private ProjectStatus projectStatus;

    public Project(ProjectDTO source) {
        BeanUtils.copyProperties(source,this);
    }

    public Project() {
    }

    public void update(ProjectDTO source) {
        BeanUtils.copyProperties(source,this,"code");
    }
}
