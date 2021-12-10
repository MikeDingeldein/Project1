package com.revature.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.tika.Tika;

import com.revature.DTO.AddOrUpdateReimbursementDTO;
import com.revature.DTO.MessageDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AutherizationService;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

public class ReimbursementController {

	private ReimbursementService reimbursementService;

	private AutherizationService autherizationService;

	
	public ReimbursementController() {
		this.reimbursementService = new ReimbursementService();
		this.autherizationService = new AutherizationService();
	}

	public Handler getReimbursements = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizationEmployeeAndManager(currentlyLoggedInUser);
		
		List<Reimbursement> reimbursements = this.reimbursementService.getReimbursements(currentlyLoggedInUser);

		ctx.json(reimbursements);
	};
	
	public Handler getAllReimbursements = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizationEmployeeAndManager(currentlyLoggedInUser);
		
		List<Reimbursement> reimbursements = this.reimbursementService.getAllReimbursements();

		ctx.json(reimbursements);
	};

	public Handler addNewReimbursement = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizeEmployee(currentlyLoggedInUser);
		
//		AddOrUpdateReimbursementDTO dto = ctx.bodyAsClass(AddOrUpdateReimbursementDTO.class);
		String reimAmount = ctx.formParam("reimb_amount");
		String reimType = ctx.formParam("reimb_type");
		String reimDescription = ctx.formParam("reimb_description");
		UploadedFile file = ctx.uploadedFile("reimb_receipt");
		
		if (file == null) { // Could let people try without proof of the expense
			ctx.status(400);
			ctx.json(new MessageDTO("Must upload copy of receipt."));
			return;
		}
		
		InputStream content = file.getContent();
		Tika tika = new Tika();
		String mimeType = tika.detect(content);
		
		
		Reimbursement r = this.reimbursementService.addNewReimbursement(currentlyLoggedInUser , reimAmount, reimType, reimDescription, content, mimeType);
		ctx.status(201);
		ctx.json(r);
	};

	public Handler getReimbursementByEmployee = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizeEmployee(currentlyLoggedInUser);
		
//		String id = ctx.pathParam("user_id");
		List<Reimbursement> r = this.reimbursementService.getReimbursements(currentlyLoggedInUser);
		ctx.json(r);
	};

//	public Handler getReimbursementByStatus = (ctx) -> {
//		
//		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
//		this.autherizationService.autherizationEmployeeAndManager(currentlyLoggedInUser);
//		
//		String status = ctx.pathParam("reimb_status");
//		List<Reimbursement> r = this.reimbursementService.getReimbursementByStatus(status);
//		ctx.json(r);
//	};
	
	public Handler updateReimbursementById = (ctx) -> {
		
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizeManager(currentlyLoggedInUser);
		
		String id = ctx.pathParam("reimb_id");
		AddOrUpdateReimbursementDTO dto = ctx.bodyAsClass(AddOrUpdateReimbursementDTO.class);
		Reimbursement r = this.reimbursementService.editReimbursementById(id, dto.getReimStatus(), currentlyLoggedInUser);
		ctx.json(r);
	};
	
	private Handler getImageFromAssignmentById = (ctx) -> {
		User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("currentuser");
		this.autherizationService.autherizationEmployeeAndManager(currentlyLoggedInUser);
		
		String reimbursementId = ctx.pathParam("reimb_id");
		
		InputStream image = this.reimbursementService.getImageFromReimbursementById(currentlyLoggedInUser, reimbursementId);
		Tika tika = new Tika();
		String mimeType = tika.detect(image);
		ctx.contentType(mimeType);
		ctx.result(image);
		
	};
	
	public void registerEndPoints(Javalin app) {
		app.get("/ers_reimbursement", getReimbursements);
//		app.get("/ers_reimbursement", getAllReimbursements);
		app.post("/ers_reimbursement", addNewReimbursement);
//		app.get("/ers_reimbursement/{user_id}", getReimbursementByEmployee);
//		app.get("/ers_reimbursement/{reimb_status}", getReimbursementByStatus);
		app.patch("/ers_reimbursement/{reimb_id}", updateReimbursementById);
		app.get("/ers_reimbursement/{reimb_id}/image", getImageFromAssignmentById);
	}

}
