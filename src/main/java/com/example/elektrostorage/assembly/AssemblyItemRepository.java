package com.example.elektrostorage.assembly;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssemblyItemRepository extends JpaRepository<AssemblyItem, Long> {
    List<AssemblyItem> findByAssemblyId(Long assemblyId);
}