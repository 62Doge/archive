package com.bootcamp.hellobiqri.backend.dao;

import com.bootcamp.hellobiqri.backend.dto.ProdusenDto;
import com.bootcamp.hellobiqri.backend.entity.Produsen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ProdusenDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Produsen findId(Integer id){
        String query = "SELECT id, nama, kode, alamat\n" + "FROM public.produsen where id = :idProdusen";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idProdusen", id);

        return jdbcTemplate.queryForObject(query, map, new RowMapper<Produsen>() {
            @Override
            public Produsen mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("id"));
                produsen.setNama(rs.getString("nama"));
                produsen.setKode(rs.getString("kode"));
                produsen.setAlamat(rs.getString("alamat"));
                return produsen;
            }
        });

    }


    public List<Produsen> findAll(){
        String query = "SELECT id, nama, kode, alamat\n" + "FROM public.produsen";
        return jdbcTemplate.query(query, new RowMapper<Produsen>() {
            @Override
            public Produsen mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("id"));
                produsen.setNama(rs.getString("nama"));
                produsen.setKode(rs.getString("kode"));
                produsen.setAlamat(rs.getString("alamat"));

                return produsen;
            }
        });
    }


    public Integer create(ProdusenDto.Create produsen){
        String query = "INSERT INTO public.produsen\n" +
                "(nama, kode, alamat)\n" +
                "VALUES(:nama, :kode, :alamat)";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produsen.getNama());
        map.addValue("kode", produsen.getKode());
        map.addValue("alamat", produsen.getAlamat());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, map, keyHolder);

        return (Integer) keyHolder.getKeys().get("id");
    }



    public void update(ProdusenDto.Update produsen){
        String query = "UPDATE public.produsen\n" +
                "SET nama=:nama, kode=:kode, alamat=:alamat\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produsen.getNama());
        map.addValue("kode", produsen.getKode());
        map.addValue("alamat", produsen.getAlamat());
        map.addValue("id", produsen.getId());

        jdbcTemplate.update(query, map);
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.produsen\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        jdbcTemplate.update(query, map);

    }
}
