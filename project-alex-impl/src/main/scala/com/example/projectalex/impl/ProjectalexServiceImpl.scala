package com.example.projectalex.impl

import akka.NotUsed
import com.example.projectalex.api.ProjectalexService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.{ExecutionContext, Future}


class ProjectalexServiceImpl()(implicit ec: ExecutionContext) extends ProjectalexService {

  override def hello(): ServiceCall[NotUsed, JsValue] = { (request) =>
    print(request.toString)
    Future.successful(Json.toJson("Hi"))
  }

  override def health(): ServiceCall[NotUsed, JsValue] = { (request) =>
    Future.successful(Json.toJson("Service up and running..."))
  }
}
