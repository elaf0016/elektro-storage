package com.example.elektrostorage.component;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @PostMapping
    public Component createComponent(@RequestBody Component component) {
        return componentService.createComponent(component);
    }

    @PutMapping("/{id}/discontinue")
    public Component discontinueComponent(@PathVariable Long id) {
        return componentService.discontinueComponent(id);
    }
}