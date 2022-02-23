val ScalatraVersion = "2.8.2"

// ThisBuild / scalaVersion := "2.13.4"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / organization := "com.github.zhuodongpei"

lazy val hello = (project in file("."))
  .settings(
    name := "myserver",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      // "com.typesafe.play" %% "play-json" % "2.6.2",
      // "net.liftweb" %% "json" % "3.5.0",
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "9.4.35.v20201120" % "container",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
      "com.typesafe.play" %% "play-json" % "2.6.2"
    ),
  )

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.2"

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
