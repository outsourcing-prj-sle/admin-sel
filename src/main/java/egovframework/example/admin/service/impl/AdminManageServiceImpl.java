package egovframework.example.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

import egovframework.example.admin.service.AdminManageService;
import egovframework.example.cmmn.model.LoginVO;

@Service("adminManageService")
public class AdminManageServiceImpl implements AdminManageService {
	
	@Resource(name = "adminManageMapper")
	private AdminManageMapper mapper;
	
	@Resource(name = "userIdGnrService")
	private EgovIdGnrService userIdGnrService;

	@Override
	public LoginVO selectUser(LoginVO loginVO) {
		return mapper.selectUser(loginVO);
	}
	
	@Override
	public String insertUser(LoginVO loginVO) {
		try {
			if(mapper.checkUserById(loginVO.getId())) {
				loginVO.setUniqId(mapper.selectUniqId(loginVO));
				mapper.updateUser(loginVO);
			} else {
				loginVO.setUniqId(userIdGnrService.getNextStringId());
				mapper.insertUser(loginVO);
			}
		} catch (FdlException e) {
			e.printStackTrace();
		}

		return loginVO.getUniqId();
	}
	
	@Override
	public List<LoginVO> selectUserAll(String role){
		return mapper.selectUserAll(role);
	}
	
	@Override
	public String deleteUser(String id){
		if(mapper.checkUserById(id)) {
			mapper.deleteUser(id);
			return id;
		}
		return "없는 유저 입니다";
	}

	@Override
	public boolean checkUserById(String id) {
		return mapper.checkUserById(id);
	}

	@Override
	public boolean authorizationUser(String id) {
		return mapper.authorizationUser(id);
	}
                               
}
