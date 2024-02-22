package com.bootcamp.hellobiqri.backend.controller;

import com.bootcamp.hellobiqri.backend.dto.ProdukDto;
import com.bootcamp.hellobiqri.backend.dto.ProdusenDto;
import com.bootcamp.hellobiqri.backend.entity.Produk;
import com.bootcamp.hellobiqri.backend.entity.Produsen;
import com.bootcamp.hellobiqri.backend.service.ProdukService;
import com.bootcamp.hellobiqri.backend.service.ProdusenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produk")
@Slf4j
public class ProdukController {

    @Autowired
    ProdukService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable Integer id) {
        try {
            Produk produk = service.findId(id);
            return ResponseEntity.ok(produk);

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().body("Data tidak ditemukan!");
        }
    }

    @GetMapping("")
    public List<Produk> findAll() {

        return service.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create
            (@RequestBody @Valid ProdukDto.Create produk, BindingResult result) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()) {
            output.put("id", null);
            output.put("status", "Creating data failed");
            output.put("error", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);
        } else {
            output.put("id", service.create(produk));
            output.put("status", "Data has been created");
            return ResponseEntity.ok(output);

        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(
            @RequestBody @Valid ProdukDto.Update produk,
            BindingResult result) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()) {
            output.put("status", "Create data failed");
            output.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);
        } else {
            try {
                service.findId(produk.getId());
                service.update(produk);
                output.put("status", "Create data success");
                return ResponseEntity.ok().body(output);
            } catch (EmptyResultDataAccessException e) {
                output.put("status", "Id not found");
                return ResponseEntity.badRequest().body(output);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> output = new HashMap<>();

        try {
            service.findId(id);
            service.delete(id);
            output.put("status", "Data has been deleted");

            return ResponseEntity.ok(output);
        } catch (EmptyResultDataAccessException e) {
            output.put("status", "Id not found");

            return ResponseEntity.badRequest().body(output);
        }
    }
}
