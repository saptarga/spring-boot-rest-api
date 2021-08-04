package com.privyid.pretest.privyidpretestbackendenginer.endpoint;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestTransferMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseTransferMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.statval.IApplicationConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(IApplicationConstant.ContextPath.V1_USER_BALANCE)
public interface IUserBalanceEndPoint {

    @PostMapping(IApplicationConstant.Path.UserBalance.DEPOSIT_MONEY)
    ResponseEntity<ResponseDepositMoneyDTO> depositMoney(@Valid @RequestBody RequestDepositMoneyDTO requestDepositMoneyDTO,
                                                         Authentication authentication,
                                                         HttpServletRequest request) throws Exception;

    @PostMapping(IApplicationConstant.Path.UserBalance.TRANSFER_MONEY)
    ResponseEntity<ResponseTransferMoneyDTO> transferMoney(@Valid @RequestBody RequestTransferMoneyDTO requestDepositMoneyDTO,
                                                           Authentication authentication,
                                                           HttpServletRequest request) throws Exception;

}
