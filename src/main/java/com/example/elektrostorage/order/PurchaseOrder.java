package com.example.elektrostorage.order;

import com.example.elektrostorage.supplier.Supplier;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingCode;

    private LocalDate sentDate;

    private LocalDate expectedDeliveryDate;

    private LocalDate receivedDate;

    @ManyToOne
    private Supplier supplier;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String trackingCode,
                         LocalDate expectedDeliveryDate,
                         Supplier supplier) {
        this.trackingCode = trackingCode;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}