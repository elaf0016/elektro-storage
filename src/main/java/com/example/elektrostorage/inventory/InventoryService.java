package com.example.elektrostorage.inventory;

import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryCountRepository inventoryCountRepository;
    private final ComponentRepository componentRepository;

    public InventoryService(InventoryCountRepository inventoryCountRepository,
                            ComponentRepository componentRepository) {
        this.inventoryCountRepository = inventoryCountRepository;
        this.componentRepository = componentRepository;
    }

    public List<InventoryCount> getAllCounts() {
        return inventoryCountRepository.findAll();
    }

    public InventoryCount countComponent(Long componentId,
                                         int quantity,
                                         String countedBy) {

        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        InventoryCount count = new InventoryCount(
                quantity,
                countedBy,
                LocalDate.now(),
                component
        );

        return inventoryCountRepository.save(count);
    }
}