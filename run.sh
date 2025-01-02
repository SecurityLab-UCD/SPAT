#!/bin/bash
# run.sh 
#

test_id=0
#java -jar ./artifacts/SPAT-linux.jar "$test_id" ./Benchmarks/9133/Original "./Benchmarks/9133/transformed/_$test_id" /usr/lib/jvm/java-8-openjdk/jre/lib
java -jar ./artifacts/SPAT-linux.jar "$test_id" ./Benchmarks/9133/Original "./Benchmarks/9133/transformed/_$test_id" /usr/lib/jvm/java-21-openjdk/lib
