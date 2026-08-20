package com.example.elektrostorage.inventory;

import com.example.elektrostorage.component.Component;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class InventoryCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private String countedBy;

    private LocalDate countedDate;

    @ManyToOne
    private Component component;

    public InventoryCount() {
    }

    public InventoryCount(int quantity,
                          String countedBy,
                          LocalDate countedDate,
                          Component component) {
        this.quantity = quantity;
        this.countedBy = countedBy;
        this.countedDate = countedDate;
        this.component = component;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCountedBy() {
        return countedBy;
    }

    public LocalDate getCountedDate() {
        return countedDate;
    }

    public Component getComponent() {
        return component;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCountedBy(String countedBy) {
        this.countedBy = countedBy;
    }

    public void setCountedDate(LocalDate countedDate) {
        this.countedDate = countedDate;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}