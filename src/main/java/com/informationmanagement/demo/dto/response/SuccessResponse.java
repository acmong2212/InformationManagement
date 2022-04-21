package com.informationmanagement.demo.dto.response;

import lombok.Data;

@Data
public class SuccessResponse {
    private int status;
    private ProjectDTO data;

    public SuccessResponse(int status, ProjectDTO data) {
        this.status = status;
        this.data = data;
    }
}
