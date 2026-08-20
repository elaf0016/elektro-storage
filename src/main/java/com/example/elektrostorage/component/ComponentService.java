package com.example.elektrostorage.component;

import com.example.elektrostorage.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Component createComponent(Component component) {
        return componentRepository.save(component);
    }

    public Component discontinueComponent(Long id) {
        Component component = componentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found"));

        component.setDiscontinued(true);

        return componentRepository.save(component);
    }
}