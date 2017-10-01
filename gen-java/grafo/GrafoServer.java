package grafo;

import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import java.util.HashMap;

public class GrafoServer {
	public static void main(String [] args) {
		int porta = 0;

		if ( args.length != 1 ) {
			System.out.println("ERRO : GONES WRONG");
			return;
		} else {
			porta = Integer.parseInt(args[0]);
		}

		try {
			TServer server = new TThreadPoolServer( new Args(new TServerSocket(porta)).processor(new Grafo.Processor(new GrafoHandler())));
			System.out.println("Iniciando o servidor ...");
	 		server.serve();
	  	} catch (Exception x){
			x.printStackTrace();
	 	}
	}
}