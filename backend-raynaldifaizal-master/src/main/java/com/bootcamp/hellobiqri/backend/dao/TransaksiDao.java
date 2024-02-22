package com.bootcamp.hellobiqri.backend.dao;

import com.bootcamp.hellobiqri.backend.dto.ProdukDto;
import com.bootcamp.hellobiqri.backend.dto.TransaksiDto;
import com.bootcamp.hellobiqri.backend.entity.Produk;
import com.bootcamp.hellobiqri.backend.entity.Produsen;
import com.bootcamp.hellobiqri.backend.entity.Transaksi;
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
public class TransaksiDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    //findId
    public Transaksi findId(Integer id){
        String query = "SELECT transaksi.id,\n"+
                "produk.id as produk_id, \n" +
                "produk.nama as produk_nama, \n" +
                "produk.jenis as produk_jenis, \n" +
                "produk.berat as produk_berat, \n" +
                "produk.harga as produk_harga, \n" +
                "transaksi.kuantitas\n" +
                "FROM public.transaksi transaksi\n" +
                "left join produk produk on transaksi.produk_id = produk.id\n" +
                "where transaksi.id = :idTransaksi";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idTransaksi", id);

        return jdbcTemplate.queryForObject(query, map, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("id"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getString("produk_berat"));
                produk.setHarga(rs.getDouble("produk_harga"));

                transaksi.setProduk(produk);

                return transaksi;
            }
        });

    }

    //detail
    public TransaksiDto.Detail detail(Integer id){
        String query = "SELECT transaksi.id,\n" +
                "produk.nama as produk_nama, \n" +
                "produsen.nama as produsen_nama, \n" +
                "produk.harga as produk_harga, \n" +
                "transaksi.kuantitas,\n" +
                "produk.harga*transaksi.kuantitas as totalHarga\n" +
                "FROM public.transaksi transaksi\n" +
                "left join public.produk produk on transaksi.produk_id = produk.id\n" +
                "left join public.produsen produsen on produk.produsen_id = produsen.id\n" +
                "where transaksi.id = :idTransaksi";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idTransaksi", id);

        return jdbcTemplate.queryForObject(query, map, new RowMapper<TransaksiDto.Detail>() {
            @Override
            public TransaksiDto.Detail mapRow(ResultSet rs, int rowNum) throws SQLException {
                TransaksiDto.Detail transaksi = new TransaksiDto.Detail();

                transaksi.setId(rs.getInt("id"));
                transaksi.setProduk(rs.getString("produk_nama"));
                transaksi.setProdusen(rs.getString("produsen_nama"));
                transaksi.setHarga(rs.getDouble("produk_harga"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));
                transaksi.setTotalHarga(rs.getDouble("totalHarga"));

                return transaksi;
            }
        });


    }


    //findAll
    public List<Transaksi> findAll(){
        String query = "SELECT transaksi.id,\n" +
                "produk.id as produk_id, \n" +
                "produk.nama as produk_nama, \n" +
                "produk.jenis as produk_jenis, \n" +
                "produk.berat as produk_berat, \n" +
                "produk.harga as produk_harga, \n" +
                "produk.harga as produk_harga, \n" +

                "transaksi.kuantitas\n" +
                "FROM public.transaksi transaksi\n" +
                "left join produk produk on transaksi.produk_id = produk.id";

        return jdbcTemplate.query(query, new RowMapper<Transaksi>() {
            @Override
            public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
                Transaksi transaksi = new Transaksi();
                transaksi.setId(rs.getInt("id"));
                transaksi.setKuantitas(rs.getInt("kuantitas"));

                Produk produk = new Produk();
                produk.setId(rs.getInt("produk_id"));
                produk.setNama(rs.getString("produk_nama"));
                produk.setJenis(rs.getString("produk_jenis"));
                produk.setBerat(rs.getString("produk_berat"));
                produk.setHarga(rs.getDouble("produk_harga"));

                transaksi.setProduk(produk);

                return transaksi;
            }
        });
    }

    public Integer create(TransaksiDto.Create transaksi){
        String query = "INSERT INTO public.transaksi\n" +
                "(kuantitas, produk_id)\n" +
                "VALUES(:kuantitas,  :produk_id)";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kuantitas", transaksi.getKuantitas());

        map.addValue("produk_id", transaksi.getProduk_id());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, map, keyHolder);

        return (Integer) keyHolder.getKeys().get("id");
    }

    public void update(TransaksiDto.Update transaksi){
        String query = "UPDATE public.transaksi\n" +
                "SET kuantitas=:kuantitas, produk_id=:produk_id\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("kuantitas", transaksi.getKuantitas());
        map.addValue("id", transaksi.getId());

        map.addValue("produk_id", transaksi.getProduk_id());

        jdbcTemplate.update(query, map);
    }




    public void delete(Integer id){
        String query = "DELETE FROM public.transaksi\n" +
                "WHERE id=:id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        jdbcTemplate.update(query, map);

    }

}
