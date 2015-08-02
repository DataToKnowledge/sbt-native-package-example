# getting-started-scala
Getting Started with Scala / Play on DataToKnowledge

The project execution is supported by the [sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/index.html).
In order to package our java application we need:
1. install the [sbt-native-packager](https://github.com/sbt/sbt-native-packager) as explained in the doc
2. create the java binary 
3. package it with Universal Packager
4. package it with Docker

## 1. Install sbt-native packager
We add ```project/plugins.sbt``` file:

```scala
// for autoplugins
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.3")
```

## 2. Create the java binary
In the build.sbt enable the plugin ```JavaAppPackaging```

```scala
enablePlugins(JavaAppPackaging)
```

after that if run ```sbt stage``` you will find the folder ```target/universal/stage``` 
where there is a compiled version of the project.

## 3. Package it with the UniversalPackager

By default with the universal packager we can use the following packages formats
- Zip: sbt universal:packageBin
- Tar: sbt universal:packageZipTarball
- Xz:  sbt universal:packageXzTarball
- Dmg: sbt universal:packageOsxDmg
To get more informations about additional confs please check the [official doc](http://www.scala-sbt.org/sbt-native-packager/formats/universal.html)


## 4. [package it with Docker](http://www.scala-sbt.org/sbt-native-packager/formats/docker.html)

Add the following to the ```build.sbt```

```scala
enablePlugins(DockerPlugin)
```

After that we need to add the following configurations

```scala
// change the name of the project adding the prefix of the user
packageName in Docker := "dtk/" +  packageName.value
maintainer in Docker := "info@datatotknowledge.it"
//the base docker images
dockerBaseImage := "java:8-jre"
//the exposed port
dockerExposedPorts := Seq(9000)
//exposed volumes
dockerExposedVolumes := Seq("/opt/docker/logs")
```

With the command ```sbt docker:pubblishLocal``` it will publish the image to the local docker repo.

To run the image

```bash
docker run -d -p 9000:9000 --name example dtk/getting-started-scala:1.0
```
and finally to test it

```
curl http://localhost:9000/ip/8.8.8.8

curl -X POST -H 'Content-Type: application/json' http://localhost:9000/ip -d '{"ip1": "8.8.8.8", "ip2": "8.8.4.4"}'
```

# Deal with private config files

All the private configurations are placed in the project privateconfs

```
git clone https://github.com/fabiofumarola/privateconfs
```

copy the file ```private.conf``` in the folder ```src/main/resources``` and
add the file to ```.gitignore``` file.


## references

1. [akka-http-microservice](https://github.com/theiterators/akka-http-microservice) for the example project
2. [sbt-native-packager](http://www.scala-sbt.org/sbt-native-packager/index.html)