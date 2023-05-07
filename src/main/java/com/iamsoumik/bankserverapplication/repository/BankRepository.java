package com.iamsoumik.bankserverapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamsoumik.bankserverapplication.entity.Account;

@Repository
public interface BankRepository extends JpaRepository<Account, Integer>{

}
