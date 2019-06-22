package com.codehub6

import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.*
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle


/**
 * @author shenzhou-6
 * @data 2019-06-22
 */
class HttpServerVerticle : CoroutineVerticle() {
  private val log = LoggerFactory.getLogger("httpserver")

  override suspend fun start() {
    val router = Router.router(vertx)
    router.route().handler(BodyHandler.create(""))
    router.route().handler(ResponseContentTypeHandler.create())
    router.route().handler(LoggerHandler.create(LoggerFormat.DEFAULT))
    router.route().handler(TimeoutHandler.create(5000))
    vertx.createHttpServer()
      .requestHandler(router)
      .listenAwait(8080)

    log.info("http server verticle deploy sucess!")
  }
}
