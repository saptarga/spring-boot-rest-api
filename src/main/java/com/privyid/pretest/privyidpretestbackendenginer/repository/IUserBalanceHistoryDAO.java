package com.privyid.pretest.privyidpretestbackendenginer.repository;

import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserBalanceHistoryDAO extends JpaRepository<UserBalanceHistory, Long> {
}
