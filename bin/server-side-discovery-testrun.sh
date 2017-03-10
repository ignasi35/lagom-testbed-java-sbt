#!/usr/bin/env bash


## parse single optional argument
if [ "conductr" == "$1" ]
then
  HOST="192.168.10.1"
else
  HOST="localhost"
fi



##
## Uses $TEST_NAME from a global scope
## $1 == expected
## $2 == actual
## Uses $COMMAND from a global scope when test failed.
##

assertion () {
  if [ $1 == $2 ]
  then
     echo "[pass] - $TEST_NAME"
  else
     echo "[fail] - $TEST_NAME"
     echo "   [$1] was not equal to [$2]"
     echo "   Use [$COMMAND] to reproduce."
  fi
}




TEST_NAME="001-Monoservice: the primary service is available (server-side discovery)"
COMMAND="curl -s http://$HOST:9000/api/hello001/bob"
RESPONSE="$( $COMMAND )"
assertion "bob" $RESPONSE



TEST_NAME="002-Multiservice: the primary service is available (server-side discovery)"
COMMAND="curl -s http://$HOST:9000/api/hello002/bob"
RESPONSE="$( $COMMAND )"
assertion "bob" $RESPONSE

TEST_NAME="002-Multiservice: the secondary service is available (server-side discovery)"
COMMAND="curl -s http://$HOST:9000/api/goodbye002/bob"
RESPONSE="$( $COMMAND )"
assertion "bob" $RESPONSE

