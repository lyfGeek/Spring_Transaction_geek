package com.geek.dao;

import com.geek.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 根据名称查询账户。
     *
     * @param name
     * @return
     */
    Account findByName(String name);

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

}
