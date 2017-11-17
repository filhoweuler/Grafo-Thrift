#!/bin/sh

#$1 = porta inicial
#$2 = numero de servidores

ini=$1

#java -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ grafo.GrafoServer 9090

for i in $(seq $ini $((ini + $2 - 1)))
do
	echo "java -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ grafo.GrafoServer $ini $i &"
	java -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ grafo.GrafoServer $2 $ini $i &
done