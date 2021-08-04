package com.privyid.pretest.privyidpretestbackendenginer.endpoint.impl;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestTransferMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseDepositMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseTransferMoneyDTO;
import com.privyid.pretest.privyidpretestbackendenginer.endpoint.IUserBalanceEndPoint;
import com.privyid.pretest.privyidpretestbackendenginer.entity.User;
import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalance;
import com.privyid.pretest.privyidpretestbackendenginer.service.IUserBalanceService;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class UserBalanceEndPointImpl implements IUserBalanceEndPoint {

    private final IUserBalanceService userBalanceService;

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseDepositMoneyDTO> depositMoney(@Valid RequestDepositMoneyDTO requestDepositMoneyDTO, Authentication authentication, HttpServletRequest request) throws Exception {
        User userDetails = (User) authentication.getPrincipal();
        UserBalance result = userBalanceService.depositMoney(requestDepositMoneyDTO, userDetails.getUsername(), request);
        return ResponseEntity.ok(ResponseDepositMoneyDTO.builder()
                .username(userDetails.getUsername())
                .balance(result.getBalanceAchieve())
                .depositMoney(requestDepositMoneyDTO.getAmount())
                .activity(EActivity.DEPOSIT_MONEY).build());
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<ResponseTransferMoneyDTO> transferMoney(@Valid RequestTransferMoneyDTO requestTransferMoneyDTO, Authentication authentication, HttpServletRequest request) throws Exception {
        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userBalanceService.transferMoney(requestTransferMoneyDTO, userDetails.getUsername(), request));
    }
}
