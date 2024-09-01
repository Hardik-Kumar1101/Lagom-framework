package com.example.projectalex.impl

import com.example.projectalex.api.ProjectalexService
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

abstract class ProjectalexApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {
  override lazy val lagomServer: LagomServer = serverFor[ProjectalexService](wire[ProjectalexServiceImpl])
}

class ProjectalexLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new ProjectalexApplication(context) with LagomDevModeComponents

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new ProjectalexApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[ProjectalexService])
}
