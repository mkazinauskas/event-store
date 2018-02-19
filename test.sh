#!/bin/sh

TIMES=40

while [ ! $TIMES -eq 0 ]
do
  if curl 'http://localhost:8080/events/next' ; then
    printf "\n"
    echo "$(date) - connected successfully"	
    break
  else 
    echo "$(date) - still trying, times left: $TIMES"
    printf "\n"
    TIMES=`expr $TIMES - 1`
    sleep 1  
  fi
done
printf "\n"


