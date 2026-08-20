package com.example.elektrostorage.assembly;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assemblies")
public class AssemblyController {

    private final AssemblyService assemblyService;

    public AssemblyController(AssemblyService assemblyService) {
        this.assemblyService = assemblyService;
    }

    @GetMapping
    public List<Assembly> getAllAssemblies() {
        return assemblyService.getAllAssemblies();
    }

    @PostMapping
    public Assembly createAssembly(@RequestBody Assembly assembly) {
        return assemblyService.createAssembly(assembly);
    }

    @PostMapping("/{assemblyId}/items")
    public AssemblyItem addItem(@PathVariable Long assemblyId,
                                @RequestParam Long componentId,
                                @RequestParam int quantity) {

        return assemblyService.addItem(assemblyId, componentId, quantity);
    }
}