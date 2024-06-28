package egovframework.example.admin.login.service;

import egovframework.example.cmmn.model.AccountDTO;
import egovframework.example.cmmn.model.LoginVO;

public interface AdminLoginService {
	LoginVO selectUserById(AccountDTO accountDTO);
}
