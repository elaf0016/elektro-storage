package com.example.elektrostorage.order;

import com.example.elektrostorage.component.Component;
import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    private Component component;

    public OrderItem() {
    }

    public OrderItem(int quantity,
                     PurchaseOrder purchaseOrder,
                     Component component) {
        this.quantity = quantity;
        this.purchaseOrder = purchaseOrder;
        this.component = component;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
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

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}