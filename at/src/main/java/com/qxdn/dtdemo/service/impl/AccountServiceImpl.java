package com.qxdn.dtdemo.service.impl;

import com.qxdn.dtdemo.dal.model.Account;
import com.qxdn.dtdemo.dal.repository.AccountRepository;
import com.qxdn.dtdemo.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void debit(Long userId, Long money) {
        log.info("start account service xid:{}", RootContext.getXID());
        Account account = accountRepository.findAccountByUserId(userId);
        if (account.getMoney() < money) {
            throw new RuntimeException("余额不足");
        }
        account.setMoney(account.getMoney() - money);
        accountRepository.save(account);
    }
}
