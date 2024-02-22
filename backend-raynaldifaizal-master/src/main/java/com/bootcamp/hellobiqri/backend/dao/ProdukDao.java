package com.bootcamp.hellobiqri.backend.dao;

import com.bootcamp.hellobiqri.backend.dto.ProdukDto;
import com.bootcamp.hellobiqri.backend.dto.ProdusenDto;
import com.bootcamp.hellobiqri.backend.entity.Produk;
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
public class ProdukDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Produk findId(Integer id){
        String query = "SELECT produk.id,\n" +
                        "produk.nama,\n" +
                        "produk.jenis,\n" +
                        "produk.berat,\n" +
                        "produk.harga,\n" +
                        "produsen.id as produsen_id,\n" +
                        "produsen.nama as produsen_nama,\n" +
                        "produsen.kode as produsen_kode,\n" +
                        "produsen.alamat as produsen_alamat\n" +
                        "FROM public.produk produk\n" +
                        "left join produsen produsen on produk.produsen_id = produsen.id\n" +
                        "where produk.id = :idProduk";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idProduk", id);

        return jdbcTemplate.queryForObject(query, map, new RowMapper<Produk>() {
            @Override
            public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("id"));
                produk.setNama(rs.getString("nama"));
                produk.setJenis(rs.getString("jenis"));
                produk.setBerat(rs.getString("berat"));
                produk.setHarga(rs.getDouble("harga"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsen_id"));
                produsen.setNama(rs.getString("produsen_nama"));
                produsen.setKode(rs.getString("produsen_kode"));
                produsen.setAlamat(rs.getString("produsen_alamat"));

                produk.setProdusen(produsen);


                return produk;
            }
        });

    }


    public List<Produk> findAll(){
        String query = "SELECT produk.id,\n" +
                    "produk.nama,\n" +
                    "produk.jenis,\n" +
                    "produk.berat,\n" +
                    "produk.harga,\n" +
                    "produsen.id as produsen_id,\n" +
                    "produsen.nama as produsen_nama,\n" +
                    "produsen.kode as produsen_kode,\n" +
                    "produsen.alamat as produsen_alamat\n" +
                    "FROM public.produk produk\n" +
                    "left join produsen produsen on produk.produsen_id = produsen.id";
        return jdbcTemplate.query(query, new RowMapper<Produk>() {
            @Override
            public Produk mapRow(ResultSet rs, int rowNum) throws SQLException {
                Produk produk = new Produk();
                produk.setId(rs.getInt("id"));
                produk.setNama(rs.getString("nama"));
                produk.setJenis(rs.getString("jenis"));
                produk.setBerat(rs.getString("berat"));
                produk.setHarga(rs.getDouble("harga"));

                Produsen produsen = new Produsen();
                produsen.setId(rs.getInt("produsen_id"));
                produsen.setNama(rs.getString("produsen_nama"));
                produsen.setKode(rs.getString("produsen_kode"));
                produsen.setAlamat(rs.getString("produsen_alamat"));

                produk.setProdusen(produsen);


                return produk;
            }
        });
    }


    public Integer create(ProdukDto.Create produk){
        String query = "INSERT INTO public.produk\n" +
                "(nama, jenis, berat, harga, produsen_id)\n" +
                "VALUES(:nama, :jenis, :berat, :harga, :produsen_id)";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("harga", produk.getHarga());

        map.addValue("produsen_id", produk.getProdusen_id());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, map, keyHolder);

        return (Integer) keyHolder.getKeys().get("id");
    }



    public void update(ProdukDto.Update produk){
        String query = "UPDATE public.produk\n" +
                "SET nama=:nama, jenis=:jenis, berat=:berat, harga=:harga, produsen_id=:produsen_id\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", produk.getNama());
        map.addValue("jenis", produk.getJenis());
        map.addValue("berat", produk.getBerat());
        map.addValue("harga", produk.getHarga());
        map.addValue("id", produk.getId());
        map.addValue("produsen_id", produk.getProdusen_id());

        jdbcTemplate.update(query, map);
    }

    public void delete(Integer id){
        String query = "DELETE FROM public.produk\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        jdbcTemplate.update(query, map);

    }
}
