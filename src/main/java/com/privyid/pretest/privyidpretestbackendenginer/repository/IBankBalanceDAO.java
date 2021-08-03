package com.privyid.pretest.privyidpretestbackendenginer.repository;

import com.privyid.pretest.privyidpretestbackendenginer.entity.BankBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankBalanceDAO extends JpaRepository<BankBalance, Long> {

    BankBalance findByCode(String code);
}
