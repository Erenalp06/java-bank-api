package com.teksen.bank_api.config;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teksen.bank_api.entity.Account;
import com.teksen.bank_api.entity.Bank;
import com.teksen.bank_api.entity.User;
import com.teksen.bank_api.repository.AccountRepository;
import com.teksen.bank_api.repository.BankRepository;
import com.teksen.bank_api.repository.UserRepository;

@Configuration
public class DataSeeder {

    private final UserRepository userRepository;
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;


    public DataSeeder(UserRepository userRepository, BankRepository bankRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }

    @Bean
    CommandLineRunner initDatabase(){
        return args -> {
            User user1 = new User();
            user1.setName("John Doe");
            user1.setEmail("john.doe@example.com");
            userRepository.save(user1);

            User user2 = new User();
            user2.setName("Jane Roe");
            user2.setEmail("jane.roe@example.com");
            userRepository.save(user2);
            
            Bank bank1 = new Bank();
            bank1.setName("Bank A");
            bank1.setAddress("123 Bank St.");
            bankRepository.save(bank1);

            Bank bank2 = new Bank();
            bank2.setName("Bank B");
            bank2.setAddress("456 Bank St.");
            bankRepository.save(bank2);
            
            Account account1 = new Account();
            account1.setAccountNumber("123456789");
            account1.setBalance(new BigDecimal("1000.00"));
            account1.setUser(user1);
            account1.setBank(bank1);
            accountRepository.save(account1);

            Account account2 = new Account();
            account2.setAccountNumber("987654321");
            account2.setBalance(new BigDecimal("1500.00"));
            account2.setUser(user2);
            account2.setBank(bank2);
            accountRepository.save(account2);
        };
        }
    }


