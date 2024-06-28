package egovframework.example.admin.service;

import java.util.List;

import egovframework.example.cmmn.model.LoginVO;

public interface AdminManageService {
	
	String insertUser(LoginVO loginVO);
	
	LoginVO selectUser(LoginVO loginVO);
	
	List<LoginVO> selectUserAll(String role);
	
	String deleteUser(String id);
	
	boolean checkUserById(String id);
	
	boolean authorizationUser(String id);
}
