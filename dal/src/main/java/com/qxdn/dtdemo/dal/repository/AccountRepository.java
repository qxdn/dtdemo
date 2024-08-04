package com.qxdn.dtdemo.dal.repository;

import com.qxdn.dtdemo.dal.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByUserId(Long userId);
}
