#!/bin/sh

TIMES=30

while ! curl 'http://localhost:8080/events/next' & [ ! $TIMES -eq 0 ]
do
  echo "$(date) - still trying"
  TIMES=`expr $TIMES - 1`
  sleep 1
done
echo "$(date) - connected successfully"
