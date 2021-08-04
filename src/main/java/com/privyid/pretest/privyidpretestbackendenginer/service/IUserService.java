package com.privyid.pretest.privyidpretestbackendenginer.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    void updateStatusLoggedOut(String username);

    UserDetails loadUserByUsernameAndStatusLoginTrue(String username);

    void updateStatusLogged(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
