/*
 * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
 */
package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Resource5 extends Controller {

  val form = Form(
    tuple(
      "foo" -> text,
      "bar" -> text
    )
  )

  def index = Action {
    Ok(views.html.resource5.index(Seq(1, 2, 3)))
  }

  def get(id: Long) = Action {
    Ok(views.html.resource5.item(id, ("foo", "bar")))
  }

  def create = Action {
    Ok(views.html.resource5.edit(None, form))
  }

  def save = Action { implicit req =>
    form.bindFromRequest().fold(
      error => Ok(views.html.resource5.edit(None, error)),
      data => Redirect(routes.Resource5.get(100))
    )
  }

  def edit(id: Long) = Action {
    Ok(views.html.resource5.edit(Some(id), form))
  }

  def update(id: Long) = Action { implicit req =>
    form.bindFromRequest().fold(
      error => Ok(views.html.resource5.edit(Some(id), error)),
      data => Redirect(routes.Resource5.get(id))
    )
  }

  def delete(id: Long) = Action {
    Redirect(routes.Resource5.index())
  }

}
