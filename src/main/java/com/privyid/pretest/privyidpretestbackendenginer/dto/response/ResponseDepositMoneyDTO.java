package com.privyid.pretest.privyidpretestbackendenginer.dto.response;

import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepositMoneyDTO {

    private String username;
    private EActivity activity;
    private Integer balance;
    private Integer depositMoney;
}
