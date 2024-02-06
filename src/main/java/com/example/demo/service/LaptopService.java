package com.example.demo.service;

import com.example.demo.model.Laptop;

import java.util.List;

public interface LaptopService {
    String insert (Laptop model);
    List<Laptop> getAll();
    Laptop getById(Long id);
    String update (Long id, Laptop model);
    String delete(Long id);

}
