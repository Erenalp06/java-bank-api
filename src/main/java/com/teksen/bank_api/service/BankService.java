package com.teksen.bank_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teksen.bank_api.entity.Bank;
import com.teksen.bank_api.repository.BankRepository;

@Service
public class BankService {

    private final BankRepository bankRepository;


    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Bank getBankById(Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }
}
