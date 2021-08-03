package com.privyid.pretest.privyidpretestbackendenginer.repository;

import com.privyid.pretest.privyidpretestbackendenginer.entity.BankBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankBalanceHistoryDAO extends JpaRepository<BankBalanceHistory, Long> {
}
