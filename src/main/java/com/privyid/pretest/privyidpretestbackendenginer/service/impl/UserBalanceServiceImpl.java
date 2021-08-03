package com.privyid.pretest.privyidpretestbackendenginer.service.impl;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.entity.BankBalance;
import com.privyid.pretest.privyidpretestbackendenginer.entity.BankBalanceHistory;
import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalance;
import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalanceHistory;
import com.privyid.pretest.privyidpretestbackendenginer.exception.ServiceException;
import com.privyid.pretest.privyidpretestbackendenginer.repository.IBankBalanceDAO;
import com.privyid.pretest.privyidpretestbackendenginer.repository.IBankBalanceHistoryDAO;
import com.privyid.pretest.privyidpretestbackendenginer.repository.IUserBalanceDAO;
import com.privyid.pretest.privyidpretestbackendenginer.repository.IUserBalanceHistoryDAO;
import com.privyid.pretest.privyidpretestbackendenginer.service.IUserBalanceService;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserBalanceServiceImpl implements IUserBalanceService {

    private final IUserBalanceDAO iUserBalanceDAO;
    private final IUserBalanceHistoryDAO iUserBalanceHistoryDAO;
    private final IBankBalanceDAO iBankBalanceDAO;
    private final IBankBalanceHistoryDAO iBankBalanceHistoryDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBalance depositMoney(RequestDepositMoneyDTO requestDepositMoneyDTO, String username, HttpServletRequest request) throws Exception {
        UserBalance userBalance = iUserBalanceDAO.findByUser_Username(username);
        BankBalance bankBalance = iBankBalanceDAO.findByCode(requestDepositMoneyDTO.getBankCode());

        if (Objects.isNull(bankBalance)) {
            throw new ServiceException("Bank balance not found");
        }

        if (Objects.isNull(userBalance)) {
            throw new ServiceException("User balance not found");
        }

        return depositMoney(requestDepositMoneyDTO, username, userBalance, bankBalance, request);
    }

    private UserBalance depositMoney(RequestDepositMoneyDTO requestDepositMoneyDTO, String username, UserBalance userBalance, BankBalance bankBalance, HttpServletRequest request){
        int amountUserBalance = userBalance.getBalanceAchieve() + requestDepositMoneyDTO.getAmount();
        UserBalanceHistory userBalanceHistory = UserBalanceHistory.builder()
                .userBalance(userBalance)
                .balanceBefore(userBalance.getBalanceAchieve())
                .balanceAfter(amountUserBalance)
                .activity(EActivity.DEPOSIT_MONEY)
                .type(EType.DEBIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(username)
                .build();
        iUserBalanceHistoryDAO.save(userBalanceHistory);

        userBalance.setBalanceAchieve(amountUserBalance);
        userBalance.setBalance(String.valueOf(amountUserBalance));
        iUserBalanceDAO.save(userBalance);

        int amountBankBalance = bankBalance.getBalanceAchieve() + requestDepositMoneyDTO.getAmount();
        BankBalanceHistory bankBalanceHistory = BankBalanceHistory.builder()
                .bankBalance(bankBalance)
                .balanceBefore(userBalance.getBalanceAchieve())
                .balanceAfter(amountBankBalance)
                .activity(EActivity.DEPOSIT_MONEY)
                .type(EType.DEBIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(username)
                .build();
        iBankBalanceHistoryDAO.save(bankBalanceHistory);

        bankBalance.setBalanceAchieve(amountBankBalance);
        bankBalance.setBalance(amountBankBalance);
        iBankBalanceDAO.save(bankBalance);

        return userBalance;
    }
}
