package grafo;

import java.util.*;
import java.util.concurrent.Semaphore;

public class GrafoHandler implements Grafo.Iface {

	HashMap<Integer, Vertice> vertices = new HashMap<>();
    HashMap<Integer, List<Aresta> > AdjList = new HashMap<>();
    HashMap<Integer, Semaphore> mutex_vertices = new HashMap<>();
    Semaphore graph_mutex = new Semaphore(1);

	@Override
	public void adiciona_vertice(Vertice v) throws org.apache.thrift.TException {
		if(!vertices.containsKey(v.nome)) {
			System.out.println("Vertice inserido : " + v.nome);
			vertices.put(v.nome, v);
            AdjList.put(v.nome, new ArrayList());
		} else {
			System.out.println("Vertice nao inserido! Ja existe no grafo!");
		}
	}

	@Override
    public Vertice le_vertice(int nome) throws org.apache.thrift.TException {
    	if (vertices.containsKey(nome)) {
    		return vertices.get(nome);
    	} else {
    		System.out.println("Vertice " + nome + " nao existe no Grafo! ");
    	}

    	return null;
    }

    @Override
    public void atualiza_vertice(int nome, Vertice v) throws org.apache.thrift.TException {
        if(vertices.containsKey(nome)) {
            vertices.put(nome, v);
        } else {
            System.out.println("Vertice " + nome + " nao existe no Grafo! ");
        }
    }

    @Override
    public void deleta_vertice(int nome) throws org.apache.thrift.TException {
        vertices.remove(nome);

        for(Aresta a : AdjList.get(nome)) {
            if(!a.direcionada) {
                for (int i = 0; i < AdjList.get(a.v2).size(); i++) {
                    if (AdjList.get(a.v2).get(i).v2 == nome) {
                        AdjList.get(a.v2).remove(i);
                    }
                }
            }
        }

        AdjList.remove(nome);
    }

    @Override
    public void adiciona_aresta(Aresta a) throws org.apache.thrift.TException {
        int u = a.v1;
        int v = a.v2;

        Aresta a2 = new Aresta(a);
        int aux = a2.v1;
        a2.v1 = a2.v2;
        a2.v2 = aux;
        if (vertices.containsKey(u) && vertices.containsKey(v)) { 
            
            if(!AdjList.get(u).contains(a)) {
                System.out.println("Adicionada a aresta " + a + "no vertice " + u);
                AdjList.get(u).add(a);

            }

            if(!a.direcionada && !AdjList.get(v).contains(a2)) {
                AdjList.get(v).add(a2);
            }

        } else {

            System.out.println("Algum dos vertices informados na aresta não existem!");

        }
    }

    @Override
    public Aresta le_aresta(int v1, int v2) throws org.apache.thrift.TException {
        if (vertices.containsKey(v1)) {
            for(Aresta a : AdjList.get(v1)) {
                if (a.v1 == v1 && a.v2 == v2) {
                    return a;
                }
            }
        }

        return null;
    }

    @Override
    public void atualiza_aresta(int v1, int v2, Aresta a) throws org.apache.thrift.TException {
        if (vertices.containsKey(v1) && vertices.containsKey(v2)) {
            for(int i = 0; i < AdjList.get(v1).size(); i++) {
                if(AdjList.get(v1).get(i).v1 == v1 && AdjList.get(v1).get(i).v2 == v2) {                    
                    AdjList.get(v1).set(i, a);
                    System.out.println("Aresta alterada! " + AdjList.get(v1).get(i));
                }
            }

            if (!a.direcionada) {
                for(int i = 0; i < AdjList.get(v2).size(); i++) {
                    if(AdjList.get(v2).get(i).v1 == v2 && AdjList.get(v2).get(i).v2 == v1) {
                        Aresta a2 = new Aresta(a);
                        int ax = a2.v1;
                        a2.v1 = a2.v2;
                        a2.v2 = ax;
                        AdjList.get(v2).set(i, a2);
                        // System.out.println("Aresta alterada! " + AdjList.get(v2).get(i));
                    }
                }   
            }
        }
    }
    
    @Override
    public void deleta_aresta(int v1, int v2) throws org.apache.thrift.TException {
        if (vertices.containsKey(v1) && vertices.containsKey(v2)) {
            for(int i = 0; i < AdjList.get(v1).size(); i++) {
                if (AdjList.get(v1).get(i).v1 == v1 && AdjList.get(v1).get(i).v2 == v2) {
                    AdjList.get(v1).remove(i);
                }
            }

            for(int i = 0; i < AdjList.get(v2).size(); i++) {
                if (AdjList.get(v2).get(i).v1 == v1 && AdjList.get(v2).get(i).v2 == v2) {
                    AdjList.get(v2).remove(i);
                }
            }
        }
    }
    
    @Override
    public List<Vertice> listar_vertices() throws org.apache.thrift.TException {
        List<Vertice> aux = new ArrayList();

        System.out.println("\nListando vertices no Grafo: ");

        for(Vertice v : vertices.values()) {
            System.out.println(v);
            aux.add(v);
        }

        System.out.println("");

        return aux;
    }

    @Override
    public List<Aresta> listar_arestas() throws org.apache.thrift.TException {
        List<Aresta> aux = new ArrayList();

        System.out.println("\nListando arestas no Grafo: ");

        for(List<Aresta> l : AdjList.values()) {
            for (Aresta x : l) {
                System.out.println(x);
                aux.add(x);
            }
        }

        System.out.println("");

        return aux;
    }

    @Override
    public List<Aresta> listar_arestas_vertice(int nome) throws org.apache.thrift.TException {
        List<Aresta> aux = new ArrayList();

        System.out.println("O vertice " + nome + " contem as seguintes arestas:");
        
        if (AdjList.containsKey(nome)) {
            aux = AdjList.get(nome);
            for(Aresta a : aux) {
                System.out.println(a);
            }
        }

        System.out.println("");

        return aux;
    }

    @Override
    public List<Vertice> listar_vizinhos_vertice(int nome) throws org.apache.thrift.TException {
        List<Vertice> aux = new ArrayList();

        System.out.println("O vertice " + nome + " é vizinho dos seguintes vertices:");

        for(Aresta a : AdjList.get(nome)) {
            Vertice v = vertices.get(a.v2);
            System.out.println(v);
            aux.add( v );
        }

        System.out.println("");

        return aux;
     }

    public void graph_mutex_acquire() throws org.apache.thrift.TException  {
        try {
            graph_mutex.acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
            mutex.release();
        }
    }

    public void graph_mutex_release() throws org.apache.thrift.TException {
        graph_mutex.release();
    }
}