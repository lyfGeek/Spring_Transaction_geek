package com.geek.dao.impl;

import com.geek.dao.IAccountDao;
import com.geek.domain.Account;
import com.geek.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * 账户的持久层实现类。
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner queryRunner;

    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 根据名称查询账户。
     *
     * @param name
     * @return 如果有唯一结果就返回。如果没有结果返回 null。
     * 如果结果超过一个，就抛异常。
     */
    @Override
    public Account findByName(String name) {
        try {
//            List<Account> accountList = queryRunner.query("select * from account where name = ?", new BeanListHandler<>(Account.class), name);
            List<Account> accountList = queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where name = ?", new BeanListHandler<>(Account.class), name);
            if (accountList == null || accountList.size() == 0) {
                return null;
            }
            return accountList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> FindAllAccount() {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return queryRunner.query(connectionUtils.getThreadConnection(), "select * from account where id = ?", new BeanHandler<Account>(Account.class), accountId);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "insert into account(name, money) values (?, ?)", account.getName(), account.getMoney());
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "update account set name = ?, money= ? where id = ?", account.getName(), account.getMoney(), account.getId());
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), "delete from account where id = ?", accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
