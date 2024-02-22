package com.bootcamp.hellobiqri.backend.controller;


import com.bootcamp.hellobiqri.backend.dto.ProdusenDto;
import com.bootcamp.hellobiqri.backend.entity.Produsen;
import com.bootcamp.hellobiqri.backend.service.ProdusenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produsen")
@Slf4j
public class ProdusenController {

    @Autowired
    ProdusenService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable Integer id) {
        try {
            Produsen produsen = service.findId(id);
            return ResponseEntity.ok(produsen);

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().body("Data tidak ditemukan!");
        }
    }

    @GetMapping("")
    public List<Produsen> findAll() {

        return service.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create
            (@RequestBody @Valid ProdusenDto.Create produsen, BindingResult result) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()) {
            output.put("id", null);
            output.put("status", "Creating data failed");
            output.put("error", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);
        } else {
            output.put("id", service.create(produsen));
            output.put("status", "Data has been created");
            return ResponseEntity.ok(output);

        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(
            @RequestBody @Valid ProdusenDto.Update produsen,
            BindingResult result) {
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()) {
            output.put("status", "Create data failed");
            output.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(output);
        } else {
            try {
                service.findId(produsen.getId());
                service.update(produsen);
                output.put("status", "Update data success");
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