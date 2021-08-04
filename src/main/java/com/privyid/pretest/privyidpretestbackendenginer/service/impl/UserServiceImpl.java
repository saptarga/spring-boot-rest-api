package com.privyid.pretest.privyidpretestbackendenginer.service.impl;

import com.privyid.pretest.privyidpretestbackendenginer.entity.User;
import com.privyid.pretest.privyidpretestbackendenginer.repository.IUserDAO;
import com.privyid.pretest.privyidpretestbackendenginer.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    private final IUserDAO iUserDAO;

    @Override
    public void updateStatusLoggedOut(String username) {

    }

    @Override
    public UserDetails loadUserByUsernameAndStatusLoginTrue(String username) {
        return null;
    }

    @Override
    public void updateStatusLogged(String username) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iUserDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
