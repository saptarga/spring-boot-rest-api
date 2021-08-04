package com.privyid.pretest.privyidpretestbackendenginer.endpoint;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.statval.IApplicationConstant;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = IApplicationConstant.ContextPath.V1_AUTH,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface IAuthEndPoint {

    @PostMapping("/login")
    ResponseEntity<ResponseLoginDTO> loginUser(@RequestBody RequestLoginDTO requestLoginDto);

    @PostMapping("/logout")
    ResponseEntity<String> logoutUSer(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication);
}
