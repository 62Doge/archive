package com.bootcamp.hellobiqri.backend.service;

import com.bootcamp.hellobiqri.backend.dao.TransaksiDao;
import com.bootcamp.hellobiqri.backend.dto.ProdukDto;
import com.bootcamp.hellobiqri.backend.dto.TransaksiDto;
import com.bootcamp.hellobiqri.backend.entity.Produk;
import com.bootcamp.hellobiqri.backend.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransaksiService {

    @Autowired
    TransaksiDao dao;

    public Transaksi findId(Integer id)
            throws EmptyResultDataAccessException {
        return dao.findId(id);
    }

    public List<Transaksi> findAll(){

        return dao.findAll();
    }

    public TransaksiDto.Detail detail(Integer id) throws EmptyResultDataAccessException{

        return dao.detail(id);
    }

    @Transactional
    public Integer create(TransaksiDto.Create transaksi){
        return dao.create(transaksi);
    }

    @Transactional
    public void update(TransaksiDto.Update transaksi){
        dao.update(transaksi);
    }

    @Transactional
    public void delete(Integer id){
        dao.delete(id);
    }
}
