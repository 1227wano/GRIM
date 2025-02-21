package com.grim.auth.util;

import com.grim.exception.InvalidUserAddressException;
import com.grim.exception.InvalidUserEmailException;
import com.grim.exception.InvalidUserIdException;
import com.grim.exception.InvalidUserNameException;
import com.grim.exception.InvalidUserPwdException;

public class MemberValidator {

	   public static void validateUserId(String userId) {
	        if (userId == null || userId.isEmpty()) {
	            throw new InvalidUserIdException("❌ 아이디는 비어있을 수 없습니다.");
	        }
	        if (userId.length() < 6 || userId.length() > 12) {
	            throw new InvalidUserIdException("❌ 아이디는 6글자 이상 12글자 이하만 입력할 수 있습니다.");
	        }
	        if (!userId.matches("^[a-zA-Z0-9]*$")) {
	            throw new InvalidUserIdException("❌ 아이디는 영어/숫자만 입력할 수 있습니다.");
	        }
	    }

	    public static void validateUserPwd(String userPwd) {
	        if (userPwd == null || userPwd.isEmpty()) {
	            throw new InvalidUserPwdException("❌ 비밀번호는 비어있을 수 없습니다.");
	        }
	        if (userPwd.length() < 8 || userPwd.length() > 16) {
	            throw new InvalidUserPwdException("❌ 비밀번호는 8글자 이상 16글자 이하만 입력할 수 있습니다.");
	        }
	        if (!userPwd.matches("^[a-zA-Z0-9!@#$%^&*?]*$")) {
	            throw new InvalidUserPwdException("❌ 비밀번호는 영어/숫자(!, @, #, $, %, ^, &, *, ?)만 입력할 수 있습니다.");
	        }
	    }

	    public static void validateUserAddress(String userAddress) {
	        if (userAddress == null || userAddress.isEmpty()) {
	            throw new InvalidUserAddressException("❌ 주소는 비어있을 수 없습니다.");
	        }
	    }

	    public static void validateUserName(String userName) {
	        if (userName == null || userName.isEmpty()) {
	            throw new InvalidUserNameException("❌ 별명은 비어있을 수 없습니다.");
	        }
	        if (userName.length() < 2 || userName.length() > 5) {
	            throw new InvalidUserNameException("❌ 별명은 2글자 이상 5글자 이하만 입력할 수 있습니다.");
	        }
	        if (!userName.matches("^[a-zA-Z가-힣]*$")) {
	            throw new InvalidUserNameException("❌ 별명은 한글/영어만 입력할 수 있습니다.");
	        }
	    }

	    public static void validateUserEmail(String userEmail) {
	        if (userEmail == null || userEmail.isEmpty()) {
	            throw new InvalidUserEmailException("❌ 이메일은 비어있을 수 없습니다.");
	        }
	        if (!userEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	            throw new InvalidUserEmailException("❌ 올바른 형식의 이메일 주소여야 합니다.");
	        }
	    }

	}
