package com.bootcamp.hellobiqri.backend.service;

import com.bootcamp.hellobiqri.backend.dao.ProdusenDao;
import com.bootcamp.hellobiqri.backend.dto.ProdusenDto;
import com.bootcamp.hellobiqri.backend.entity.Produsen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdusenService {

    @Autowired
    ProdusenDao dao;

    public Produsen findId(Integer id)
        throws EmptyResultDataAccessException {
            return dao.findId(id);
    }

    public List<Produsen> findAll(){

        return dao.findAll();
    }


    @Transactional
    public Integer create(ProdusenDto.Create produsen){
        return dao.create(produsen);
    }

    @Transactional
    public void update(ProdusenDto.Update produsen){
        dao.update(produsen);
    }

    @Transactional
    public void delete(Integer id){
        dao.delete(id);
    }

}
