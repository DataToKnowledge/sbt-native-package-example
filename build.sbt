name := "getting-started-scala"
organization := "DataToKnowledge"
version := "1.0"
scalaVersion := "2.11.7"


scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV       = "2.3.11"
  val akkaStreamV = "1.0"
  val scalaTestV  = "2.2.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor"                           % akkaV,
    "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-experimental"         % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaStreamV,
    "org.scalatest"     %% "scalatest"                            % scalaTestV % "test"
  )
}

Revolver.settings
enablePlugins(JavaAppPackaging)
//add the configurations for the jvm opts
bashScriptConfigLocation := Some("${app_home}/../conf/jvmopts")

enablePlugins(DockerPlugin)

packageName in Docker := "dtk/" +  packageName.value
maintainer in Docker := "info@datatotknowledge.it"
dockerBaseImage := "java:8-jre"
dockerExposedPorts := Seq(9000)
dockerExposedVolumes := Seq("/opt/docker/logs")