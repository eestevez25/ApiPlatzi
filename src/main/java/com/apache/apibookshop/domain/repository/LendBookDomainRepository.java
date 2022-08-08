package com.apache.apibookshop.domain.repository;

import com.apache.apibookshop.domain.dto.LendBookDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LendBookDomainRepository {
    List<LendBookDto> getAll();
    Optional<List<LendBookDto>> getByTypeUser(Integer typeUser);
    Optional<LendBookDto> getLendBook(Long id);
    LendBookDto save(LendBookDto lendBookDomain);
    void delete(Long id);

    boolean isExistTypeUserAndLimitDate(Integer typeUser, LocalDate limitDate);
}
