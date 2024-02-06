package com.example.demo.service.impl;

import com.example.demo.dao.LaptopDao;
import com.example.demo.model.Laptop;
import com.example.demo.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService {
    private final LaptopDao dao;
    @Override
    public String insert(Laptop model) {
        return dao.insert(model);
    }

    @Override
    public List<Laptop> getAll() {
        return dao.getAll();
    }

    @Override
    public Laptop getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public String update(Long id, Laptop model) {
        return dao.update(id,model);
    }

    @Override
    public String delete(Long id) {
        return dao.delete(id);
    }
}
