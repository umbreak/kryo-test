val scalaCompilerVersion            = "2.13.4"
val akkaKryoVersion                 = "2.1.0"
val akkaVersion                     = "2.6.12"
val catsVersion                     = "2.4.2"
val kryoVersion                     = "5.0.1"
val logbackVersion                  = "1.2.3"
val scalaTestVersion                = "3.2.4"

lazy val akkaKryo                 = "io.altoo"                   %% "akka-kryo-serialization"         % akkaKryoVersion
lazy val akkaSlf4j                = "com.typesafe.akka"          %% "akka-slf4j"                      % akkaVersion
lazy val akkaTestKit              = "com.typesafe.akka"          %% "akka-testkit"                    % akkaVersion
lazy val catsCore                 = "org.typelevel"              %% "cats-core"                       % catsVersion
lazy val kryo                     = "com.esotericsoftware"        % "kryo"                            % kryoVersion
lazy val logback                  = "ch.qos.logback"              % "logback-classic"                 % logbackVersion
lazy val scalaTest                = "org.scalatest"              %% "scalatest"                       % scalaTestVersion

lazy val root = project
  .in(file("."))
  .settings(
		scalaVersion := scalaCompilerVersion,
		name := "kryo-test",
		moduleName := "kryo-test",
    libraryDependencies     ++= Seq(
      akkaKryo,
      catsCore,
      akkaTestKit % Test,
      akkaSlf4j   % Test,
      scalaTest   % Test
    )
)
