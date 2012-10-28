package controllers

import java.util.{Date, Locale}
import java.lang.String
import java.text.SimpleDateFormat
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import views._

import play.cache.Cache

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
	      {case (login_id, password) =>
	        Cache.set("login_id",login_id)
	        Ok(html.home("Comming Soon(?)")
	            (SystemUser(login_id,password,login_id))
	            (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") format new Date))}
	    )
	}
	var logined = Action{
		var login_id_obj = Cache.get("login_id")
		var login_id = "Undefined"
		if(login_id != null)
		{
		  login_id = login_id_obj.toString()
		}

	    Ok(html.home("Comming Soon(?)")
	            (SystemUser(login_id,"",login_id))
	            (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") format new Date))
	}
	var resources = Action{
		var login_id_obj = Cache.get("login_id")
		var login_id = "Undefined"
		if(login_id != null)
		{
		  login_id = login_id_obj.toString()
		}
	    Ok(html.resources("Comming Soon(?)")
	            (SystemUser(login_id,"",login_id))
	            (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") format new Date))
	}
}