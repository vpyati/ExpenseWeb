package com.vikram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vikram.openconnect.login.google.GoogleOpenconnectLogin;

@Controller
@RequestMapping("/googlelogin")
public class GoogleLogin extends GoogleOpenconnectLogin {
	

}
