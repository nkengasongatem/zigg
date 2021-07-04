# zigg project

This project uses Quarkus, the Supersonic Subatomic Java Framework to display planned blackouts in different cities in Cameroon.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/zigg-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Related guides

- WebSockets ([guide](https://quarkus.io/guides/websockets)): WebSocket communication channel support

## Generate Zigg banner

- http://patorjk.com/software/taag/#p=display&h=0&v=0&f=Graffiti&t=Zigg

## Continuous integration

`Build`, `test`, and `deploy` the code right from GitHub using `GitHub Actions`

On every push we,
1- Build the app to produce a native executable.
2- Embed the produced executable into an ultra lightweight distroless Docker image (41.7MB).
3- Push this image to a container registry (TODO).
4- Deploy this image to the Cloud (TODO).
