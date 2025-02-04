package org.example.service;

import org.example.repository.FOCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FOCategoryService {
    @Autowired
    private FOCategoryRepository foCategoryRepository;
}
