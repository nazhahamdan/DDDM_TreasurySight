package com.example.demo.Controller;


import com.example.demo.Entities.Transaction;
import com.example.demo.Service.TransactionService;
import com.example.demo.dto.RawTransaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    //  GET transactions par entreprise
    @GetMapping("/entreprise/{idEntreprise}")
    public List<Transaction> getByEntreprise(@PathVariable Long idEntreprise) {
        return service.getByEntreprise(idEntreprise);
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public Transaction getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //  CREATE
    @PostMapping("/entreprise/{idEntreprise}")
    public Transaction create(
            @RequestBody RawTransaction dto,
            @PathVariable int idEntreprise
    ) {
        return service.create(dto, idEntreprise);
    }

    //  UPDATE
    @PutMapping("/{id}")
    public Transaction update(
            @PathVariable Long id,
            @RequestBody RawTransaction dto
    ) {
        return service.update(id, dto);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
