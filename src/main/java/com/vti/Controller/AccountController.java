package com.vti.Controller;

import com.vti.DTO.AccountDTO;
import com.vti.Entity.Account;
import com.vti.Form.CreatingAccountForm;
import com.vti.Form.UpdatingAccountForm;
import com.vti.Service.EmailSenderService;
import com.vti.Service.IAccountService;
import com.vti.filter.AccountFilterForm;
import com.vti.validate.AccountIdExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
//@Validated
@CrossOrigin("*")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailSenderService emailSenderService;

    //get all account
    @GetMapping
    public ResponseEntity<Page<AccountDTO>> getAllAccounts(Pageable pageable, AccountFilterForm form){
       Page<Account> page = accountService.getAllAccounts(pageable, form);
       List<AccountDTO> accountDTOS = modelMapper.map(page.getContent(), new TypeToken<List<AccountDTO>>(){
       }.getType());
        return new ResponseEntity<>(new PageImpl<>(accountDTOS, pageable, page.getTotalElements()), HttpStatus.OK);
    }

    //get account by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable(name = "id") @AccountIdExists int id) {
        return new ResponseEntity<>(modelMapper.map(accountService.getAccountById(id), AccountDTO.class), HttpStatus.OK);

    }

    //create account
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody @Valid CreatingAccountForm form) {
        accountService.createAccount(form);
        return new ResponseEntity<>("Create account successfully",HttpStatus.OK);
    }

    //update account
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "id") @AccountIdExists int id, @RequestBody @Valid UpdatingAccountForm form){
        form.setId(id);
        accountService.updateAccount(form);
        return new ResponseEntity<>("Update account successfully",HttpStatus.OK);
    }

    //delete account
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") @AccountIdExists int id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("delete account successfully",HttpStatus.OK);
    }

    //delete list account
    @DeleteMapping
    public ResponseEntity<?> deleteAccounts(@RequestParam(name = "ids") List<Integer> ids){
        accountService.deleteAccounts(ids);
        return new ResponseEntity<>("delete list account successfully", HttpStatus.OK);
    }


    //send mail
    @PostMapping(value = "/sendEmail")
    public ResponseEntity<?> sendEmail(@RequestParam String email) {
        emailSenderService.sendLinkChangePassword(email);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String newPassword, @RequestParam String token) throws Exception {
        emailSenderService.changePassword(newPassword, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
