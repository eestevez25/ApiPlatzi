package com.apache.apibookshop.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    private String userName;
    private String password;
}
