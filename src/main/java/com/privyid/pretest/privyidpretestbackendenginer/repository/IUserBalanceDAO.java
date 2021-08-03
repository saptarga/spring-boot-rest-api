package com.privyid.pretest.privyidpretestbackendenginer.repository;

import com.privyid.pretest.privyidpretestbackendenginer.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserBalanceDAO extends JpaRepository<UserBalance, Long> {

    UserBalance findByUser_Username(String username);
}
