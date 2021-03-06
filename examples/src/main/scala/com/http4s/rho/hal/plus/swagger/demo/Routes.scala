package com.http4s.rho.hal.plus.swagger.demo

import cats.effect.IO
import org.http4s.HttpService
import org.http4s.rho.RhoMiddleware
import org.http4s.rho.swagger.syntax.io._

class Routes(businessLayer: BusinessLayer) {

  val middleware: RhoMiddleware[IO] =
    createRhoMiddleware()

  val dynamicContent: HttpService[IO] =
    new RestService[IO](businessLayer).toService(middleware)

  /**
   * Routes for getting static resources. These might be served more efficiently by apache2 or nginx,
   * but its nice to keep it self contained
   */
  val staticContent: HttpService[IO] =
    new StaticContentService[IO](org.http4s.dsl.io) {}.routes

}
