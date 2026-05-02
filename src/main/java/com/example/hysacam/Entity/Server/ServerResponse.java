package com.example.hysacam.Entity.Server;

import lombok.Data;

@Data
public class ServerResponse {
    private String message ;
    private Boolean status ;


    public ServerResponse(String message,Boolean status) {
        this.message = message;
        this.status = status;
    }

    public ServerResponse() {
    }
}
