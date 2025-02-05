package com.grim.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grim.auth.service.AuthentlcationServiceImpl;
import com.grim.member.model.service.MemberService;
import com.grim.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final AuthentlcationServiceImpl authService;
	private final TokenService tokenService;
}
