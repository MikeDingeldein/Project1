package com.revature.controller;

import java.security.InvalidParameterException;

import javax.security.auth.login.FailedLoginException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.DTO.MessageDTO;
import com.revature.exceptions.ReimbursementNotFound;
import com.revature.exceptions.UnautherizedException;

import io.javalin.Javalin;

public class ExceptionMapperCotroller {

	public void mapException(Javalin app) {
		app.exception(UnrecognizedPropertyException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));

		});

		app.exception(FailedLoginException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});

		app.exception(InvalidParameterException.class, (e, ctx) -> {
			ctx.status(400);
			ctx.json(new MessageDTO(e.getMessage()));
		});
		
		app.exception(UnautherizedException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.json(new MessageDTO(e.getMessage()));

		});

		app.exception(ReimbursementNotFound.class, (e, ctx) -> {
			ctx.status(404);
			ctx.json(new MessageDTO(e.getMessage()));

		});
		
	}
}
