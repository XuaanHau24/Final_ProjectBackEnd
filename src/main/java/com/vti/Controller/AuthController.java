package com.vti.Controller;

import com.vti.DTO.LogInfoDTO;
import com.vti.Entity.Account;
import com.vti.Service.EmailSenderService;
import com.vti.Service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/login")
    public LogInfoDTO login(Principal principal){
        String username = principal.getName();
        Account account = accountService.getAccountByUsername(username);
        return modelMapper.map(account, LogInfoDTO.class);

    }

}
