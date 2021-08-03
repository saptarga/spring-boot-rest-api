package com.privyid.pretest.privyidpretestbackendenginer.service;


import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalance;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface IUserBalanceService {

    @Transactional
    UserBalance depositMoney(RequestDepositMoneyDTO requestDepositMoneyDTO, String username, HttpServletRequest request) throws Exception;
}
