# Getting Started

## Run Kafka
helm install my-release bitnami/kafka

## Docker Specific
- Build Image:       docker build -t mtanvir96/collector
- Run Container:     docker run -dp 9000:9000 mtanvir96/collector
- Push to dockerhub: docker login; docker push mtanvir96/collector
- Log: Docker logs -f <containerId>

## Github Specific:
- Create a repository in github.com
- git remote add origin https://github.com/mtanvir9666/collector.git

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

