package com.informationmanagement.demo.dto.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class SuccessResponsePage {
    private int status;
    private int totalRecord;
    private Page<ProjectDTO> data;


    public SuccessResponsePage(int status, int totalRecord ,Page<ProjectDTO> search) {
        this.status = status;
        this.totalRecord = totalRecord;
        this.data = search;
    }
}
