package com.geek.service.impl;

import com.geek.dao.IAccountDao;
import com.geek.domain.Account;
import com.geek.service.IAccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 根据 id 查询账户信息。
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    /**
     * 转账。
     *
     * @param fromName 转出账户名称。
     * @param toName   转入账户名称。
     * @param money    金额。
     */
    @Override
    public void transfer(String fromName, String toName, Float money) {
        System.out.println("start transfer...");
        // 根据名称查询转出账户。
        Account from = accountDao.findAccountByName(fromName);
        // 根据名称查询转入账户。
        Account to = accountDao.findAccountByName(toName);
        // 转出账户扣钱。
        from.setMoney(from.getMoney() - money);
        // 转入账户加钱。
        to.setMoney(to.getMoney() + money);
        // 更新转出账户。
        accountDao.updateAccount(from);

        int i = 1 / 0;

        // 更新转入账户。
        accountDao.updateAccount(to);
    }
}
