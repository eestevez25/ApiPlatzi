package com.apache.apibookshop.domain.service;

import com.apache.apibookshop.domain.dto.LendBookDto;
import com.apache.apibookshop.domain.repository.LendBookDomainRepository;
import com.apache.apibookshop.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LendBookService {

    private int DAYS_FOR_LIMIT_DATE = 8;
    @Autowired
    private LendBookDomainRepository lendBookDomainRepository;

    public List<LendBookDto> getAll(){
        return lendBookDomainRepository.getAll();
    }

    public Optional<LendBookDto> getLendBook(Long id){
        return lendBookDomainRepository.getLendBook(id);
    }

    public Optional<List<LendBookDto>> getByTypeUser(Integer typeUser){
        return lendBookDomainRepository.getByTypeUser(typeUser);
    }

    public String save(LendBookDto lendBookDomain){
        Integer userType = lendBookDomain.getLenBookTypeUser() ;
        if(!isValidUser(userType)){
            return "El Usuario no puede prestar libros";
        }
        if(isIndependent(userType)){
            LocalDate now = LocalDate.now();
            if(hasLendBooks(userType, now)){
                return "El Usuario Tiene ya un libro prestado, no es posible sacar otro hasta no devolver el que ya tiene";
            }
        }
        lendBookDomain.setLenBookLimitDate(getLimitDate());
        return "La fecha de entrega del libro es : " + lendBookDomainRepository.save(lendBookDomain).getLenBookLimitDate().toString();
    }

    public boolean delete(Long id){
        return getLendBook(id).map(lendBookDomain -> {
            lendBookDomainRepository.delete(id);
            return true;
        }).orElse(false);
    }

    private boolean isValidUser(Integer userType){
        return (userType != null &&
                (userType.intValue() == UserType.INDEPENDENT.getType() ||
                userType.intValue() == UserType.EMPLOYEE.getType() ||
                userType.intValue() == UserType.LEADER.getType()));
    }

    private boolean isIndependent(Integer userType){
        return (userType != null && userType.intValue() == UserType.INDEPENDENT.getType());
    }

    private LocalDate getLimitDate(){
        LocalDate localDate = LocalDate.now();
        return localDate.plusDays(DAYS_FOR_LIMIT_DATE);
    }

    private boolean hasLendBooks(Integer userType, LocalDate limitDate){
        return lendBookDomainRepository.isExistTypeUserAndLimitDate(userType,limitDate);
    }
}
