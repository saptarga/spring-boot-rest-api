package com.privyid.pretest.privyidpretestbackendenginer.dto.request;

import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDepositMoneyDTO {

    @NotNull
    private Integer amount;

    @NotNull
    private String bankCode;
}
