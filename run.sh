#!/bin/bash
# run.sh 
#

jre_path="$(which java)"
java -jar ./artifacts/SPAT-linux.jar 5 ./Benchmarks/9133/Original ./Benchmarks/9133/transformed/_5 /usr/lib/jvm/java-8-openjdk/jre/lib
