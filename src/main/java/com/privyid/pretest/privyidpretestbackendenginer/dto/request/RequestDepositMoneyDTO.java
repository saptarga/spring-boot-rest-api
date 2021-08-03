package com.privyid.pretest.privyidpretestbackendenginer.dto.request;

import com.privyid.pretest.privyidpretestbackendenginer.statval.EActivity;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDepositMoneyDTO {

    // TODO: Remove username if we have added security, and we can get username from principal
    @NotNull
    private String username;

    @NotNull
    private Integer amount;

    @NotNull
    private String bankCode;
}
