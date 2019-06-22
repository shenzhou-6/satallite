package com.codehub6

import io.vertx.config.ConfigRetriever
import io.vertx.kotlin.config.configRetrieverOptionsOf
import io.vertx.kotlin.config.configStoreOptionsOf
import io.vertx.kotlin.config.getConfigAwait
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.CoroutineVerticle

/**
 * @author shenzhou-6
 * @data 2019-06-22
 *
 */
class DeployVerticle:CoroutineVerticle(){
  override suspend fun start() {
    //read config.json in resources
    val fileStore = configStoreOptionsOf(type = "file",config = jsonObjectOf("path" to "config.json"))
    val options = configRetrieverOptionsOf(stores = listOf(fileStore))
    val retriever = ConfigRetriever.create(vertx, options)
    val config = retriever.getConfigAwait()
    //TODO deploy other verticle
  }
}
