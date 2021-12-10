package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.DTO.LoginDTO;
import com.revature.DTO.MessageDTO;
import com.revature.model.User;
import com.revature.service.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LoginController {

	private LoginService loginService;

	public LoginController() {
		this.loginService = new LoginService();
	}

	private Handler login = (ctx) -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		User user = this.loginService.getUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		HttpServletRequest req = ctx.req;
		HttpSession session = req.getSession();
		session.setAttribute("currentuser", user); //set the user as logged in
		ctx.json(user);
	};

	private Handler logout = (ctx) -> {
		HttpServletRequest req = ctx.req;

		req.getSession().invalidate();
	};

	private Handler checkIfLoggedIn = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if(!(session.getAttribute("currentuser") == null)) { //Are you actually logged in.
			ctx.json(session.getAttribute("currentuser"));
			ctx.status(200);
		} else {
			ctx.json(new MessageDTO("User is not logged in"));
			ctx.status(401);
		}
	};
	
	public void registerEndPoints(Javalin app) {
		// TODO Auto-generated method stub
		app.post("/login", login);
		app.post("/logout", logout);
		app.get("/checkloginstatus", checkIfLoggedIn);

	}

}
