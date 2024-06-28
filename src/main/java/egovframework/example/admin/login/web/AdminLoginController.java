package egovframework.example.admin.login.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.login.service.AdminLoginService;
import egovframework.example.cmmn.model.AccountDTO;
import egovframework.example.cmmn.model.LoginVO;
import egovframework.example.cmmn.model.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {
	
	@Resource(name = "adminLoginService")
	private AdminLoginService adminLoginService;

	/**
	 * 기본 로그인
	 * @param AccountDTO
	 */
	@PostMapping("/login")
	public ResponseEntity<?> selLogin(@RequestBody AccountDTO account){
		LoginVO user = new LoginVO();
		log.info("로그인 시도- ID : {} , PW : {} ",account.getId(),account.getPassword());
		try {
			user = adminLoginService.selectUserById(account);

			if(!user.getPassword().equals(account.getPassword())) {
				log.error("로그인 에러-패스워드 오류");
				return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Login Failed(Wrong Password)"));
			}
		//	if(!user.getUserRole())

		}catch(Exception e) {
			log.error("로그인 에러");
			return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"Login Failed(User Not Found)"));
		}
		log.info("로그인 User : ",account.getId());
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("token", user.getUniqId());
		result.put("id", account.getId());
		result.put("role", user.getUserRole());
		result.put("position", user.getUserSpaceOrgInfo());
		
		return ResponseEntity.ok(result);
	}
}
