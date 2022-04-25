package com.informationmanagement.demo.dto.response;

import com.informationmanagement.demo.model.Project;
import com.informationmanagement.demo.model.ProjectStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    @NotNull(message = "Code cannot be empty")
    private String code;

    @NotNull(message = "Name cannot be empty")
    private String name;

    private Long teamSize;

    private String namePartner;

    @NotNull(message = "Projects take time to start")
    private LocalDate timeStart;

    private LocalDate timeFinish;

    private ProjectStatus projectStatus;

    public ProjectDTO(Project source) {
        BeanUtils.copyProperties(source,this);
    }
}
