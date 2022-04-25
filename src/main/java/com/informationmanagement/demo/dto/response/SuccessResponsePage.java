package com.informationmanagement.demo.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class SuccessResponsePage {
    private int status;
    private Page<ProjectDTO> data;


    public SuccessResponsePage(int status, Page<ProjectDTO> search) {
        this.status = status;
        this.data = search;
    }
}
