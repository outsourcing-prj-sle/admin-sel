package egovframework.example.admin.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.admin.login.service.AdminLoginService;
import egovframework.example.cmmn.model.AccountDTO;
import egovframework.example.cmmn.model.LoginVO;

@Service("adminLoginService")
public class AdminLoginServiceImpl implements AdminLoginService {
	
	@Resource(name = "adminLoginMapper")
	private AdminLoginMapper mapper;
	
	@Override
	public LoginVO selectUserById(AccountDTO accountDTO) {
		return mapper.selectUserById(accountDTO);
	}
}
