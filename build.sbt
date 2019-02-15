
name := "ScalaJs101"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.2"

enablePlugins(ScalaJSPlugin)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "org.querki" %%% "jquery-facade" % "1.2"
)

scalaJSUseMainModuleInitializer := true
