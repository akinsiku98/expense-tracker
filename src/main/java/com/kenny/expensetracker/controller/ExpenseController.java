package com.kenny.expensetracker.controller;

import com.kenny.expensetracker.model.Expense;
import com.kenny.expensetracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    // 1. List all expenses
    @GetMapping
    public List<Expense> getAll() {
        return repository.findAll();
    }

    // 2. Get one by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getOne(@PathVariable Long id) {
        return repository.findById(id)
            .map(exp -> ResponseEntity.ok(exp))
            .orElse(ResponseEntity.notFound().build());
    }

    // 3. Create a new expense
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense create(@RequestBody Expense expense) {
        return repository.save(expense);
    }

    // 4. Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(
        @PathVariable Long id,
        @RequestBody Expense expense
    ) {
        return repository.findById(id).map(existing -> {
            existing.setDescription(expense.getDescription());
            existing.setAmount(expense.getAmount());
            existing.setDate(expense.getDate());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. Delete an expense
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
