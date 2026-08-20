package com.example.elektrostorage.inventory;

import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import com.example.elektrostorage.exception.ResourceNotFoundException;
import com.example.elektrostorage.order.OrderItem;
import com.example.elektrostorage.order.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryCountRepository inventoryCountRepository;
    private final ComponentRepository componentRepository;
    private final OrderItemRepository orderItemRepository;

    public InventoryService(InventoryCountRepository inventoryCountRepository,
                            ComponentRepository componentRepository,
                            OrderItemRepository orderItemRepository) {

        this.inventoryCountRepository = inventoryCountRepository;
        this.componentRepository = componentRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<InventoryCount> getAllCounts() {
        return inventoryCountRepository.findAll();
    }

    public InventoryCount countComponent(Long componentId,
                                         int quantity,
                                         String countedBy) {

        Component component = componentRepository.findById(componentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Component not found"));

        InventoryCount count = new InventoryCount(
                quantity,
                countedBy,
                LocalDate.now(),
                component
        );

        return inventoryCountRepository.save(count);
    }

    public List<OrderItem> getReceivedItems() {
        return orderItemRepository.findByPurchaseOrderReceivedDateIsNotNull();
    }
}