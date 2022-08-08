package com.apache.apibookshop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    LEADER(1),
    EMPLOYEE(2),
    INDEPENDENT(3);

    private int type;
}
