package com.example.elektrostorage.component;

import com.example.elektrostorage.supplier.Supplier;
import jakarta.persistence.*;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int internalNumber;

    private String name;

    private String externalPartNumber;

    private boolean discontinued;

    @ManyToOne
    private Supplier supplier;

    public Component() {
    }

    public Component(int internalNumber,
                     String name,
                     String externalPartNumber,
                     Supplier supplier) {
        this.internalNumber = internalNumber;
        this.name = name;
        this.externalPartNumber = externalPartNumber;
        this.supplier = supplier;
        this.discontinued = false;
    }

    public Long getId() {
        return id;
    }

    public int getInternalNumber() {
        return internalNumber;
    }

    public String getName() {
        return name;
    }

    public String getExternalPartNumber() {
        return externalPartNumber;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExternalPartNumber(String externalPartNumber) {
        this.externalPartNumber = externalPartNumber;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}