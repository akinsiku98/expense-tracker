package com.kenny.expensetracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;
  private Double amount;
  private LocalDate date;
  public Expense() {}
  public Expense(String description, Double amount, LocalDate date) {
    this.description = description;
    this.amount = amount;
    this.date = date;
  }
  // getters & setters
}
