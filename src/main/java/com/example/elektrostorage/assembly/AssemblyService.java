package com.example.elektrostorage.assembly;

import com.example.elektrostorage.component.Component;
import com.example.elektrostorage.component.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyService {

    private final AssemblyRepository assemblyRepository;
    private final AssemblyItemRepository assemblyItemRepository;
    private final ComponentRepository componentRepository;

    public AssemblyService(AssemblyRepository assemblyRepository,
                           AssemblyItemRepository assemblyItemRepository,
                           ComponentRepository componentRepository) {
        this.assemblyRepository = assemblyRepository;
        this.assemblyItemRepository = assemblyItemRepository;
        this.componentRepository = componentRepository;
    }

    public List<Assembly> getAllAssemblies() {
        return assemblyRepository.findAll();
    }

    public Assembly createAssembly(Assembly assembly) {
        return assemblyRepository.save(assembly);
    }

    public AssemblyItem addItem(Long assemblyId, Long componentId, int quantity) {

        Assembly assembly = assemblyRepository.findById(assemblyId)
                .orElseThrow(() -> new RuntimeException("Assembly not found"));

        Component component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        AssemblyItem item = new AssemblyItem(quantity, assembly, component);

        return assemblyItemRepository.save(item);
    }
}