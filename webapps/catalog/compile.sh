#!/bin/bash

javac -Xlint:unchecked -cp /usr/local/tomcat/lib/servlet-api.jar -d WEB-INF/classes/ WEB-INF/src/*.java