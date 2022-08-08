package com.apache.apibookshop.persistence;

import com.apache.apibookshop.domain.dto.LendBookDto;
import com.apache.apibookshop.domain.repository.LendBookDomainRepository;
import com.apache.apibookshop.persistence.crud.LendBookCrudRepository;
import com.apache.apibookshop.persistence.entity.LendBook;
import com.apache.apibookshop.persistence.mapper.LendBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class LendBookRepository implements LendBookDomainRepository {
    @Autowired
    private LendBookCrudRepository lendBookCrudRepository;
    @Autowired
    private LendBookMapper lendBookMapper;

    @Override
    public List<LendBookDto> getAll(){
        List<LendBook> lendBookList = (List<LendBook>) lendBookCrudRepository.findAll();
        return lendBookMapper.toLendBookDomainList(lendBookList);
    }

    @Override
    public Optional<List<LendBookDto>> getByTypeUser(Integer typeUser){
        Optional<List<LendBook>> lendBookList = lendBookCrudRepository.findByTypeUser(typeUser);
        return lendBookList.map(lendBook -> lendBookMapper.toLendBookDomainList(lendBook));
    }

    @Override
    public Optional<LendBookDto> getLendBook(Long id){
        return lendBookCrudRepository.findById(id).map(lendBook -> lendBookMapper.toLendBookDomain(lendBook));
    }

    @Override
    public LendBookDto save(LendBookDto lendBookDomain) {
        LendBook lendBook = lendBookMapper.toLendBook(lendBookDomain);
        return lendBookMapper.toLendBookDomain(lendBookCrudRepository.save(lendBook));
    }

    @Override
    public void delete(Long id){

        lendBookCrudRepository.deleteById(id);
    }

    @Override
    public boolean isExistTypeUserAndLimitDate(Integer typeUser, LocalDate limitDate) {
        return lendBookCrudRepository.findByTypeUserAndLimitDateGreaterThan(typeUser, limitDate) != null;
    }

}
