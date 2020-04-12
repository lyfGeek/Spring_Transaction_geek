package com.geek.service.impl;

import com.geek.dao.IAccountDao;
import com.geek.domain.Account;
import com.geek.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)// 只读型事务。
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void transfer(String fromName, String toName, Float money) {

        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
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

                return null;
            }
        });

    }
}
