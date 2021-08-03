package com.privyid.pretest.privyidpretestbackendenginer.statval;

public interface IApplicationConstant {

    interface ContextPath {
        String V1_USER_BALANCE = "/v1/user-balance";
    }

    interface Path {

        interface UserBalance {
            String DEPOSIT_MONEY = "deposit-money";
            String TRANSFER_MONEY = "transfer-money";
        }
    }
}
