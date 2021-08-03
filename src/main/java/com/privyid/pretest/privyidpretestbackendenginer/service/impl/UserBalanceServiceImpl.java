package com.privyid.pretest.privyidpretestbackendenginer.service.impl;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestTransferMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseTransferMoneyDTO;
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
import lombok.Synchronized;
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

    @Override
    public ResponseTransferMoneyDTO transferMoney(RequestTransferMoneyDTO requestTransferMoneyDTO, HttpServletRequest request) throws Exception {
        UserBalance userBalanceFrom = iUserBalanceDAO.findByUser_Username(requestTransferMoneyDTO.getTransferFrom());
        if (Objects.isNull(userBalanceFrom)) throw new ServiceException("User balance from not found");

        UserBalance userBalanceTo = iUserBalanceDAO.findByUser_Username(requestTransferMoneyDTO.getTransferTo());
        if (Objects.isNull(userBalanceTo)) throw new ServiceException("User balance to not found");

        BankBalance bankBalanceFrom = iBankBalanceDAO.findByCode(requestTransferMoneyDTO.getBankCodeFrom());
        if (Objects.isNull(bankBalanceFrom)) throw new ServiceException("Bank balance from not found");

        BankBalance bankBalanceTo = iBankBalanceDAO.findByCode(requestTransferMoneyDTO.getBankCodeTo());
        if (Objects.isNull(bankBalanceTo)) throw new ServiceException("Bank balance to not found");

        if (requestTransferMoneyDTO.getAmount() > userBalanceFrom.getBalanceAchieve()) throw new ServiceException("Bank balance is not enough");

        transferMoney(request, requestTransferMoneyDTO, userBalanceFrom, userBalanceTo, bankBalanceFrom, bankBalanceTo);

        return ResponseTransferMoneyDTO.builder()
                .transferFrom(userBalanceFrom.getUser().getUsername())
                .transferTo(userBalanceTo.getUser().getUsername())
                .amount(requestTransferMoneyDTO.getAmount())
                .build();
    }

    private synchronized void transferMoney(HttpServletRequest request, RequestTransferMoneyDTO requestTransferMoneyDTO, UserBalance userBalanceFrom, UserBalance userBalanceTo, BankBalance bankBalanceFrom, BankBalance bankBalanceTo){
        int amountUserBalanceFrom = userBalanceFrom.getBalanceAchieve() - requestTransferMoneyDTO.getAmount();
        int amountUserBalanceTo = userBalanceTo.getBalanceAchieve() + requestTransferMoneyDTO.getAmount();

        UserBalanceHistory userBalanceHistoryFrom = UserBalanceHistory.builder()
                .userBalance(userBalanceFrom)
                .balanceBefore(userBalanceFrom.getBalanceAchieve())
                .balanceAfter(amountUserBalanceFrom)
                .activity(EActivity.TRANSFER_MONEY)
                .type(EType.CREDIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(userBalanceFrom.getUser().getUsername())
                .build();
        iUserBalanceHistoryDAO.save(userBalanceHistoryFrom);

        userBalanceFrom.setBalanceAchieve(amountUserBalanceFrom);
        userBalanceFrom.setBalance(String.valueOf(amountUserBalanceFrom));
        iUserBalanceDAO.save(userBalanceFrom);

        UserBalanceHistory userBalanceHistoryTo = UserBalanceHistory.builder()
                .userBalance(userBalanceTo)
                .balanceBefore(userBalanceTo.getBalanceAchieve())
                .balanceAfter(amountUserBalanceTo)
                .activity(EActivity.TRANSFER_MONEY)
                .type(EType.DEBIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(userBalanceTo.getUser().getUsername())
                .build();
        iUserBalanceHistoryDAO.save(userBalanceHistoryTo);

        userBalanceTo.setBalanceAchieve(amountUserBalanceTo);
        userBalanceTo.setBalance(String.valueOf(amountUserBalanceTo));
        iUserBalanceDAO.save(userBalanceTo);

        int amountBankBalanceFrom = bankBalanceFrom.getBalanceAchieve() - requestTransferMoneyDTO.getAmount();
        int amountBankBalanceTo = bankBalanceTo.getBalanceAchieve() + requestTransferMoneyDTO.getAmount();

        BankBalanceHistory bankBalanceHistoryFrom = BankBalanceHistory.builder()
                .bankBalance(bankBalanceFrom)
                .balanceBefore(userBalanceFrom.getBalanceAchieve())
                .balanceAfter(amountBankBalanceFrom)
                .activity(EActivity.TRANSFER_MONEY)
                .type(EType.CREDIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(userBalanceFrom.getUser().getUsername())
                .build();
        iBankBalanceHistoryDAO.save(bankBalanceHistoryFrom);

        bankBalanceFrom.setBalanceAchieve(amountBankBalanceFrom);
        bankBalanceFrom.setBalance(amountBankBalanceFrom);
        iBankBalanceDAO.save(bankBalanceFrom);

        BankBalanceHistory bankBalanceHistoryTo = BankBalanceHistory.builder()
                .bankBalance(bankBalanceTo)
                .balanceBefore(userBalanceTo.getBalanceAchieve())
                .balanceAfter(amountBankBalanceTo)
                .activity(EActivity.TRANSFER_MONEY)
                .type(EType.DEBIT)
                .ip(request.getLocalAddr())
                .userAgent(request.getHeader("user-agent"))
                .author(userBalanceTo.getUser().getUsername())
                .build();
        iBankBalanceHistoryDAO.save(bankBalanceHistoryTo);

        bankBalanceTo.setBalanceAchieve(amountBankBalanceTo);
        bankBalanceTo.setBalance(amountBankBalanceTo);
        iBankBalanceDAO.save(bankBalanceTo);
    }

    private synchronized UserBalance depositMoney(RequestDepositMoneyDTO requestDepositMoneyDTO, String username, UserBalance userBalance, BankBalance bankBalance, HttpServletRequest request){
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
