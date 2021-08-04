package com.privyid.pretest.privyidpretestbackendenginer.dto.request;

import com.privyid.pretest.privyidpretestbackendenginer.entity.BankBalance;
import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalance;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import com.privyid.pretest.privyidpretestbackendenginer.statval.EType;
import lombok.*;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestProsesTransactionDTO {

    private UserBalance userBalance;
    private BankBalance bankBalance;
    private Integer amount;
    private EType type;
    private EActivity activity;
    private String author;
    private HttpServletRequest httpServletRequest;

}
