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

    // TODO: Remove username if we have added security, and we can get username from principal
    @NotNull
    private String transferFrom;

    @NotNull
    private String transferTo;

    @NotNull
    private Integer amount;

    @NotNull
    private String bankCodeTo;

    @NotNull
    private String bankCodeFrom;
}
