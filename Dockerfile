
# Pull base image
FROM java

ENV SCALA_VERSION 2.12.3
ENV SBT_VERSION 0.13.15

EXPOSE 9000

# Copy already built jar file
COPY target/scala-2.12/bravetalent.jar /bravetalent/bravetalent.jar

# Command executed when a container is built off this image
ENTRYPOINT ["java", "-jar", "\/bravetalent\/bravetalent.jar"]
