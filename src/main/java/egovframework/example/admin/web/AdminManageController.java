package egovframework.example.admin.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.example.admin.service.AdminManageService;
import egovframework.example.cmmn.model.LoginVO;
import egovframework.example.cmmn.model.ResultVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminManageController {
	
	@Resource(name = "adminManageService")
	private AdminManageService adminManageService;
	
	@GetMapping("/users")
	ResponseEntity<?> selectUser(@RequestHeader HttpHeaders header){
		LoginVO loginVO = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		if(adminManageService.authorizationUser(loginVO.getUniqId())) {
			LoginVO user = adminManageService.selectUser(loginVO);
			return ResponseEntity.ok(user);
		}
		log.error("요청 내용을 찾을 수 없음");
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"요청 내용을 찾을 수 없음"));

	}
	/**
	 * 권한에 따른 회원 리스트 조회
	 * OgzAdmin, EduAdmin, SuperAdmin, SchoolAdmin, Student, Teacher, NormalUser
	 * @return
	 */
	@GetMapping("/users/{role}")
	ResponseEntity<?> selectAdminManagement(@RequestHeader HttpHeaders header, @PathVariable String role, @RequestParam(required=false) Map<String,String> rule){
		LoginVO loginVO = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		if(adminManageService.authorizationUser(loginVO.getUniqId())) {
			List<LoginVO> users = adminManageService.selectUserAll(role);
			return ResponseEntity.ok(users);
		}
		log.error("유효한 유저가 아님");
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"유효한 유저가 아님"));
	}
	
	@PutMapping("/users/register")
	ResponseEntity<?> insertUser(@RequestHeader HttpHeaders header,@RequestBody LoginVO loginVO){
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		if(adminManageService.authorizationUser(auth.getUniqId())) {
			loginVO.setUniqId(adminManageService.insertUser(loginVO));
			LoginVO res = adminManageService.selectUser(loginVO);
			return ResponseEntity.ok(res);
		}
		log.error("유효한 유저가 아님");
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"유효한 유저가 아님"));
	}
	
	@DeleteMapping("/users/delete")
	ResponseEntity<?> deleteUser(@RequestHeader HttpHeaders header, @RequestBody String deleteId){
		LoginVO auth = LoginVO.builder()
				.uniqId(header.get("authorization").get(0))
				.build();
		if(adminManageService.authorizationUser(auth.getUniqId())) {
			String msg = adminManageService.deleteUser(deleteId);
			return ResponseEntity.ok(msg);
		}
		log.error("유효한 유저가 아님");
		return ResponseEntity.ok(ResultVO.res(HttpStatus.OK,"유효한 유저가 아님"));
	}
	
}
