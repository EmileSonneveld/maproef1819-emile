package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getProjects: Array[String] = {
    Array("projectA", "projectB")
  }

  def getCommits: Array[String] = {
    Array("86465456", "78978978654")
  }
  def getTabs: Array[String] = {
    Array("Overview Pyramid", "Class diagram", "Design Smells", "CFG per Method")
  }

  var name = "Emile Sonneveld"

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    var controlToView = new ControlToView()
    controlToView.project_list = getProjects
    controlToView.commit_list = getCommits
    controlToView.tab_list = getTabs
    Ok(views.html.index(controlToView))
  }

  def tutorial() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tutorial())
  }

}
