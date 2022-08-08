package com.apache.apibookshop.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LendBookDto {
    private Long lendBookId;
    private Integer lenBookTypeUser;
    private Long lenBookCodeBook;
    private LocalDate lenBookLimitDate;
}
