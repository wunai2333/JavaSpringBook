package com.example.springboot.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassWordRequest {
    private String username;
    private String password;
    private String newPass;
}
