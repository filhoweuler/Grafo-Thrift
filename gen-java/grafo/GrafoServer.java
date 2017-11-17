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
		int ini = 0;
		int n = 0;

		if ( args.length != 3 ) {
			System.out.println("ERRO : GONES WRONG");
			return;
		} else {
			ini = Integer.parseInt(args[1]);
			porta = Integer.parseInt(args[2]);
			n = Integer.parseInt(args[0]);
		}

		try {
			TServer server = new TThreadPoolServer( new Args(new TServerSocket(porta)).processor(new Grafo.Processor(new GrafoHandler(n, porta - ini, ini))));
			System.out.println("Iniciando o servidor na porta " + porta + " ...");
	 		server.serve();
	  	} catch (Exception x){
			x.printStackTrace();
	 	}
	}
}