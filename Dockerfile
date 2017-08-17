
# Pull base image
FROM java

ENV SCALA_VERSION 2.12.3
ENV SBT_VERSION 0.13.15

EXPOSE 9000

# Scala expects this file
RUN touch /usr/lib/jvm/java-8-openjdk-amd64/release

# Install Scala
## Piping curl directly in tar
RUN \
  curl -fsL http://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo 'export PATH=~/scala-$SCALA_VERSION/bin:$PATH' >> /root/.bashrc

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

# Copy source code from VCS
COPY project /bravetalent/project
COPY src /bravetalent/src
COPY build.sbt /bravetalent

# Compile project and retrieve dependencies
RUN cd /bravetalent && ls && sbt compile

# Create fat jar [and optionally run tests according to build.sbt]
RUN cd /bravetalent && mkdir app && sbt assembly

# Command executed when a container is built off this image
ENTRYPOINT ["java", "-jar", "\/bravetalent\/target\/scala-2.12\/bravetalent.jar"]
