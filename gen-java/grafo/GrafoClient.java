package grafo;

import java.util.Scanner;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class GrafoClient {
	public static void main(String [] args) {
		int porta = 0;
		String servidor = "";

		if ( args.length != 2) {
			System.out.println("ERRO : GONES WRONG");
			return;
		} else {
			porta = Integer.parseInt(args[1]);
			servidor = args[0];
		}

		try {
			TTransport transport = new TSocket(servidor, porta);
			transport.open();
 			TProtocol protocol = new TBinaryProtocol(transport);
			Grafo.Client client = new Grafo.Client(protocol);

			Vertice v;
  			
  			v = new Vertice(2, 25, "O vertice do amor", 5.5);
  			client.adiciona_vertice(v);

  			Vertice v2 = client.le_vertice(v.nome);

  			assert(v2 == null);

			transport.close();

		} catch (TException x) {
			x.printStackTrace();
		}
	}
}