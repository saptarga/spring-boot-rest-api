package com.privyid.pretest.privyidpretestbackendenginer.repository;

import com.privyid.pretest.privyidpretestbackendenginer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Long> {
}
