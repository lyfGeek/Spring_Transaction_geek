package com.geek.service;

import com.geek.domain.Account;

/**
 * 账户业务接口。
 */
public interface IAccountService {

    /**
     * 根据 id 查询账户信息。
     *
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账。
     *
     * @param fromName 转出账户名称。
     * @param toName   转入账户名称。
     * @param money    金额。
     */
    void transfer(String fromName, String toName, Float money);
}
