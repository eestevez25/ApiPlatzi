package com.apache.apibookshop.persistence.mapper;

import com.apache.apibookshop.domain.dto.LendBookDto;
import com.apache.apibookshop.persistence.entity.LendBook;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LendBookMapper {
    @Mappings({
            @Mapping(source = "id", target ="lendBookId"),
            @Mapping(source = "typeUser", target ="lenBookTypeUser"),
            @Mapping(source = "codeBook", target ="lenBookCodeBook"),
            @Mapping(source = "limitDate", target ="lenBookLimitDate")
    })
    LendBookDto toLendBookDomain(LendBook lendBook);
    List<LendBookDto> toLendBookDomainList(List<LendBook> lendBookList);

    @InheritInverseConfiguration
    //@Mapping(target = "", ignore = true)
    LendBook toLendBook (LendBookDto lendBookDto);

}
