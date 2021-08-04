package com.privyid.pretest.privyidpretestbackendenginer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransferMoneyDTO {

    @NotNull
    private String transferTo;

    @NotNull
    private Integer amount;

    @NotNull
    private String bankCodeTo;

    @NotNull
    private String bankCodeFrom;
}
