package com.example.elektrostorage.inventory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryCount> getAllCounts() {
        return inventoryService.getAllCounts();
    }

    @PostMapping("/count")
    public InventoryCount countComponent(@RequestParam Long componentId,
                                         @RequestParam int quantity,
                                         @RequestParam String countedBy) {

        return inventoryService.countComponent(componentId, quantity, countedBy);
    }
}