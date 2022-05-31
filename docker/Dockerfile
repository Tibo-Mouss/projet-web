FROM quay.io/wildfly/wildfly

# Enviorment variables
ENV WILDFLY_USER admin
ENV WILDFLY_PASS pass

ENV DB_NAME sample
ENV DB_USER mysql
ENV DB_PASS mysql
ENV DB_URI localhost:3306

RUN echo "=> Downloading MariaDB driver"
RUN curl https://downloads.mariadb.com/Connectors/java/connector-java-3.0.4/mariadb-java-client-3.0.4.jar > /opt/jboss/mariadb.driver.jar

ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/

# Expose http and admin ports and debug port
EXPOSE 8080 9990

RUN mkdir -p ${JBOSS_HOME}/customization/init.d
ADD docker-entrypoint.sh /opt/jboss/wildfly/customization/
CMD ["/opt/jboss/wildfly/customization/docker-entrypoint.sh"]
