package org.example.service;

import org.example.repository.FOTransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FOTransactionTypeService {

    @Autowired
    private FOTransactionTypeRepository foTransactionTypeRepository;
}
