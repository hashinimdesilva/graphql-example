import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val AkkaVersion = "2.8.0"
val AkkaHttpVersion = "10.5.2"


lazy val root = (project in file("."))
  .settings(
    name := "GraphQLTest",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "org.postgresql" % "postgresql" % "42.6.0",
      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "org.sangria-graphql" %% "sangria" % "3.5.3",
      "org.sangria-graphql" %% "sangria-spray-json" % "1.0.3",
    )
  )