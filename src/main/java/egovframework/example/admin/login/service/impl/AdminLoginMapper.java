package egovframework.example.admin.login.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.model.AccountDTO;
import egovframework.example.cmmn.model.LoginVO;

@Mapper("adminLoginMapper")
public interface AdminLoginMapper {
	LoginVO selectUserById(AccountDTO accountDTO);
}
