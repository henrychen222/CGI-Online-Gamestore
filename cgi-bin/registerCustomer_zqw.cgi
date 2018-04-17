#!/bin/bash

CLASSPATH=.:/usr/lib/oracle/12.1/client64
CLASSPATH=$CLASSPATH:/usr/lib/oracle/12.1/client64/lib/ojdbc7.jar
CLASSPATH=$CLASSPATH:/usr/lib/oracle/12.1/client64/lib/ottclasses.zip
export CLASSPATH

echo "Content-Type: text/html"

/usr/bin/perl registerCustomer_zqw.pl