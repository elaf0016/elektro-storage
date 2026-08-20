package com.example.elektrostorage.assembly;

import com.example.elektrostorage.component.Component;
import jakarta.persistence.*;

@Entity
public class AssemblyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Assembly assembly;

    @ManyToOne
    private Component component;

    public AssemblyItem() {
    }

    public AssemblyItem(int quantity,
                        Assembly assembly,
                        Component component) {
        this.quantity = quantity;
        this.assembly = assembly;
        this.component = component;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Assembly getAssembly() {
        return assembly;
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

    public void setAssembly(Assembly assembly) {
        this.assembly = assembly;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}