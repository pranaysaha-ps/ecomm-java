package com.ecomm.demo.service;

import com.ecomm.demo.model.UserAccount;
import com.ecomm.demo.model.constants.Constant;
import com.ecomm.demo.repository.UserAccountRepository;
import com.ecomm.demo.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public Map findAll(){
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        return ResponseUtil.getSuccessResponseJsonWithData(userAccountList, Constant.SUCCESS);
    }
}
