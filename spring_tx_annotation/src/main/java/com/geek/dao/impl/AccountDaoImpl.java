package com.geek.dao.impl;

import com.geek.dao.IAccountDao;
import com.geek.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accountList = jdbcTemplate.query("select * from account where id = ?", new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accountList.isEmpty() ? null : accountList.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accountList = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<>(Account.class), accountName);
        if (accountList.isEmpty()) {
            return null;
        }
        if (accountList.size() > 1) {
            throw new RuntimeException("结果集不唯一。");
        }
        return accountList.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        int update = jdbcTemplate.update("update account set name = ?, money = ? where id = ?", account.getName(), account.getMoney(), account.getId());
    }
}
