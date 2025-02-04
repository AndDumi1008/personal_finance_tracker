package org.example.service;

import org.example.repository.FOCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FOCurrencyService {
    @Autowired
    private FOCurrencyRepository foCurrencyRepository;
}
