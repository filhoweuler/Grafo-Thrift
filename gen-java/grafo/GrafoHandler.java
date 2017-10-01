package grafo;

import java.util.*;

public class GrafoHandler implements Grafo.Iface {

	HashMap<Integer, Vertice> vertices = new HashMap<>();

	@Override
	public void adiciona_vertice(Vertice v) throws org.apache.thrift.TException {
		if(!vertices.containsKey(v.nome)) {
			System.out.println("Vertice inserido : " + v.nome);
			vertices.put(v.nome, v);
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
    public void atualiza_vertice(int nome, Vertice v) throws org.apache.thrift.TException;

    @Override
    public void deleta_vertice(int nome) throws org.apache.thrift.TException;

    @Override
    public void adiciona_aresta(Aresta a) throws org.apache.thrift.TException;

    @Override
    public Aresta le_aresta(int v1, int v2) throws org.apache.thrift.TException;

    @Override
    public void atualiza_aresta(int v1, int v2, Aresta a) throws org.apache.thrift.TException;
    
    @Override
    public void deleta_aresta(int v1, int v2) throws org.apache.thrift.TException;
    
    @Override
    public List<Vertice> listar_vertices() throws org.apache.thrift.TException;

    @Override
    public List<Aresta> listar_arestas() throws org.apache.thrift.TException;

    @Override
    public List<Aresta> listar_arestas_vertice(int nome) throws org.apache.thrift.TException;

    @Override
    public List<Vertice> listas_vizinhos_vertice(int nome) throws org.apache.thrift.TException;
}