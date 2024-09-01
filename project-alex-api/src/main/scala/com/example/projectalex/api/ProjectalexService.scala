package com.example.projectalex.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.JsValue

trait ProjectalexService extends Service {

  override final def descriptor: Descriptor = {
    import Service._
//     @formatter:off
    named("project-alex")
      .withCalls(
        restCall(Method.GET,"/api/health", health),
        restCall(Method.GET,"/api/hello", hello),
      )
      .withAutoAcl(true)
    // @formatter:on
  }

  def hello(): ServiceCall[NotUsed, JsValue]

  def health(): ServiceCall[NotUsed, JsValue]
}

