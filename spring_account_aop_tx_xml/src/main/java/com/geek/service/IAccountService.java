package com.geek.service;

import com.geek.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口。
 */
public interface IAccountService {

    /**
     * 查询所有。
     *
     * @return
     */
    List<Account> FindAllAccount();

    /**
     * 查询一个。
     *
     * @param id
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存账户。
     *
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 修改账户。
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除账户。
     *
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 转账操作。
     *
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transfer(String sourceName, String targetName, Float money);
}
