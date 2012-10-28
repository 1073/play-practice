package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import views._

object Application extends Controller
{
	val loginForm = Form(
		tuple(
		  "login_id" -> nonEmptyText,
		  "password" -> nonEmptyText
		)
	)
	var init = Action{
	  Ok(html.index(loginForm))
	}
	var login = Action{implicit request =>
	    loginForm.bindFromRequest.fold(
	      formWithErrors => BadRequest(html.index(formWithErrors)),
	      {case (login_id, password) => Ok(html.page("Comming Soon(?)"))}
	    )
	}
}