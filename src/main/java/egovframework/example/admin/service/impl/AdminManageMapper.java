package egovframework.example.admin.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.example.cmmn.model.LoginVO;

@Mapper("adminManageMapper")
public interface AdminManageMapper {

	void insertUser(LoginVO loginVO);
	
	LoginVO selectUser(LoginVO loginVO);
	
	List<LoginVO> selectUserAll(String role);
	
	void updateUser(LoginVO loginVO);
	
	void deleteUser(String id);
	
	boolean checkUserById(String id);
	
	String selectUniqId(LoginVO loginVO);
	
	boolean authorizationUser(String id);
}
