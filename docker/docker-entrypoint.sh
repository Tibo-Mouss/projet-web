#!/bin/bash
set -e

if [[ ! -f $JBOSS_HOME/.setup ]]; then

    # run init script
    if (ls ${JBOSS_HOME}/customization/init.d/* 1>/dev/null 2>&1); then
        for f in ${JBOSS_HOME}/customization/init.d/*; do
            echo "=> Run custom init script '$f'"
            bash "$f"
        done
    fi

    # Set environment variables
    DATASOURCE=java:/jdbc/datasources/${DB_NAME}DS

    # Setup WildFly admin user
    echo "=> Add WildFly administrator"
    $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent

    # Configure datasource
    echo "=> Create datasource: '${DATASOURCE}'"
    $JBOSS_CLI <<EOF
# Start WildFly in standalone mode to install mariadb module
embed-server --server-config=standalone.xml

# Batch the following operations
batch

# Add MariaDB module
module add \
--name=org.mariadb \
--dependencies=javax.api,javax.transaction.api \
--resources=/opt/jboss/mariadb.driver.jar \

# Configure driver
/subsystem=datasources/jdbc-driver=mariadb:add(driver-name="mariadb", driver-module-name="org.mariadb", driver-class-name="org.mariadb.jdbc.Driver")

# Add new datasource
data-source add \
--name=${DB_NAME}Pool \
--jndi-name=${DATASOURCE} \
--driver-name=mariadb \
--connection-url=jdbc:mariadb://${DB_URI}/${DB_NAME} \
--user-name=${DB_USER} \
--password=${DB_PASS} \
--check-valid-connection-sql="/* ping */ SELECT 1" \
--min-pool-size=10 \
--enabled=true

# Execute the batch
run-batch

# Exit from standalone mode
stop-embedded-server
EOF

    echo "=> Clean up"
    rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/* \
        $JBOSS_HOME/standalone/log/* \
        /tmp/*.jar
    unset WILDFLY_USER WILDFLY_PASS DB_NAME DB_USER DB_PASS DATASOURCE

    touch $JBOSS_HOME/.setup
    echo "=> Setup finished !!"
fi

echo "=> Start WildFly"
# Boot WildFly in standalone mode and bind it to all interfaces (enable admin console and debug)
$JBOSS_HOME/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
