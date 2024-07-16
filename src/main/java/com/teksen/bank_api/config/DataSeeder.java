package com.teksen.bank_api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    CommandLineRunner initDatabase() {
        return args -> {
            List<User> users = new ArrayList<>();
            List<Bank> banks = new ArrayList<>();

            String[] names = {"Alice Johnson", "Bob Smith", "Cathy Brown", "David Wilson", "Emma Moore",
                          "Frank White", "Grace Lee", "Henry Garcia", "Isabella Martinez", "James Taylor"};
            for (int i = 0; i < names.length; i++) {
            User user = new User();
            user.setName(names[i]);                            
            String email = names[i].toLowerCase().replace(" ", "-") + "@gmail.com";
            user.setEmail(email);
            users.add(userRepository.save(user));
        }

            String[] bankNames = {"Union Bank", "City Bank", "Trust Bank", "Heritage Bank", "Metro Bank"};
            for (int i = 0; i < bankNames.length; i++) {
                Bank bank = new Bank();
                bank.setName(bankNames[i]);
                bank.setAddress(bankNames[i] + " Financial Plaza");
                banks.add(bankRepository.save(bank));
            }

            Random random = new Random();
            for (User user : users) {
                int numAccounts = random.nextInt(4);
                for (int j = 0; j < numAccounts; j++) {
                    Account account = new Account();
                    account.setAccountNumber(String.valueOf(random.nextInt(1000000000) + 1000000000));
                    account.setBalance(BigDecimal.valueOf(random.nextInt(10000) + 1000));
                    account.setUser(user);
                    account.setBank(banks.get(random.nextInt(banks.size())));
                    accountRepository.save(account);
                }
            }
        };
    }
    }


