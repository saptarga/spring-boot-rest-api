package com.privyid.pretest.privyidpretestbackendenginer.endpoint.impl;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.endpoint.IAuthEndPoint;
import com.privyid.pretest.privyidpretestbackendenginer.entity.User;
import com.privyid.pretest.privyidpretestbackendenginer.security.jwt.JwtUtils;
import com.privyid.pretest.privyidpretestbackendenginer.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class AuthEndPointImpl implements IAuthEndPoint {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final IUserService iUserService;

    @Override
    public ResponseEntity<ResponseLoginDTO> loginUser(RequestLoginDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        iUserService.updateStatusLogged(request.getUsername());
        return ResponseEntity.ok(ResponseLoginDTO.builder()
                .token(jwt)
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .username(userDetails.getUsername())
                .roles(roles)
                .build());
    }
}
