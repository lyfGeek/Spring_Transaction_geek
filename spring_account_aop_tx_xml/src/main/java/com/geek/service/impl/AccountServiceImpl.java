package com.geek.service.impl;

import com.geek.dao.IAccountDao;
import com.geek.domain.Account;
import com.geek.service.IAccountService;
import com.geek.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类。
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 转账操作。
     *
     * @param sourceName
     * @param targetName
     * @param money
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {

        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。

            // 根据名称查询转出账户。
            Account source = accountDao.findByName(sourceName);
            // 根据名称查询转入账户。
            Account target = accountDao.findByName(targetName);
            // 转出账户减钱。
            source.setMoney(source.getMoney() - money);
            // 转入账户加钱。
            target.setMoney(target.getMoney() + money);
            // 更新转出账户。
            accountDao.updateAccount(source);

            int a = 1 / 0;

            // 更新转入账户。
            accountDao.updateAccount(target);

            // 提交事务。
            transactionManager.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            // 回滚操作。
            transactionManager.rollbackTransaction();
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }

    /**
     * 查找全部。
     *
     * @return
     */
    @Override
    public List<Account> FindAllAccount() {
        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。
            List<Account> accountList = accountDao.FindAllAccount();
            // 提交事务。
            transactionManager.commitTransaction();
            // 返回结果。
            return accountList;
        } catch (Exception e) {
            // 回滚操作。
            transactionManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }

    /**
     * 根据 id 查找一个。
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。
            Account account = accountDao.findAccountById(accountId);
            // 提交事务。
            transactionManager.commitTransaction();
            // 返回结果。
            return account;
        } catch (Exception e) {
            // 回滚操作。
            transactionManager.rollbackTransaction();
            throw new RuntimeException(e);
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }

    /**
     * 保存。
     *
     * @param account
     */
    @Override
    public void saveAccount(Account account) {
        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。
            accountDao.saveAccount(account);
            // 提交事务。
            transactionManager.commitTransaction();
        } catch (Exception e) {
            // 回滚操作。
            transactionManager.rollbackTransaction();
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }

    /**
     * 修改。
     *
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。
            accountDao.updateAccount(account);
            // 提交事务。
            transactionManager.commitTransaction();
        } catch (Exception e) {
            // 回滚操作。
            transactionManager.rollbackTransaction();
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }

    /**
     * 删除。
     *
     * @param accountId
     */
    @Override
    public void deleteAccount(Integer accountId) {
        try {
            // 开启事务。
            transactionManager.beginTransaction();
            // 执行操作。
            accountDao.deleteAccount(accountId);
            // 提交事务。
            transactionManager.commitTransaction();
        } catch (Exception e) {
            // 回滚操作。
            transactionManager.rollbackTransaction();
        } finally {
            // 释放连接。
            transactionManager.release();
        }
    }
}
