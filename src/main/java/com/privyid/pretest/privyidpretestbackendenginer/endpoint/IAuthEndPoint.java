package com.privyid.pretest.privyidpretestbackendenginer.endpoint;

import com.privyid.pretest.privyidpretestbackendenginer.dto.request.RequestLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.dto.response.ResponseLoginDTO;
import com.privyid.pretest.privyidpretestbackendenginer.statval.IApplicationConstant;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = IApplicationConstant.ContextPath.V1_AUTH,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public interface IAuthEndPoint {

    @PostMapping("/login")
    ResponseEntity<ResponseLoginDTO> loginUser(@RequestBody RequestLoginDTO requestLoginDto);
}
