FROM tomcat:9

# RUN apt-get update && apt-get install -y sdk
# RUN sdk install java 21.0.4-tem  && sdk use java 21.0.4-tem

RUN apt-get update && apt-get install -y unzip zip
RUN curl -s "https://get.sdkman.io" | bash
RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java 21.0.4-tem  && sdk use java 21.0.4-tem"

RUN apt-get install -y build-essential gcc cmake

CMD ["catalina.sh", "run"]