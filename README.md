
<h1>Repositorio para o trabalho de Sistemas distribuidos</h1>
<h2> Autores: </h2>
<ul>
	<li> Weuler Borges Santos Filho</li>
	<li> Ariane Santos Borges </li>
</ul>
<h2> Funcionamento </h2>
<p>		As classes implementadas (GrafoClient.java, GrafoServer.java, GrafoHandler.java) estao em gen-java/grafo junto das classes geradas pelo Thrift.</p>
<p>		Para executar o projeto, utilizamos os seguintes comandos:</p>
<ul>
	<li>  javac -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ gen-java/grafo/*.java (Os .class ficam salvos dentro de gen-java/grafo)</li>
	<li>  java -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ grafo.GrafoServer 9090 9090 (Start no servidor)</li>
	<li>  java -classpath jars/libthrift-0.9.3.jar:jars/slf4j-api-1.7.21.jar:gen-java/ grafo.GrafoClient localhost 9090 (Start em um cliente)</li>
</ul>

<h2> Referencias uteis para Instalação </h2>

http://thrift-tutorial.readthedocs.io/en/latest/installation.html
