package it.polito.tdp.yelp.model;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	YelpDao dao;
	Graph<User, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new YelpDao();
	}
	
	public void creaGrafo(int n, int anno) {
		
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.getUsers(n));
		
		for (Arco a : dao.getArchi(anno)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getU1(), a.getU2(), a.getPeso());
		}
		
	}
	
	public Set<User> setCombo(){
		return this.grafo.vertexSet();
	}
	
	public Graph<User, DefaultWeightedEdge> getGrafo() {
		return this.grafo;
	}
	
	public Set<User> getSimile(User u){
		
		double max = 0;
		User a = null;
		HashSet<User> result = new HashSet<>();
		
		if(this.grafo.edgesOf(u).size()>0) {
		for(DefaultWeightedEdge e : this.grafo.edgesOf(u)) {
			if(this.grafo.getEdgeWeight(e)>max) {
				max = this.grafo.getEdgeWeight(e);
				a = Graphs.getOppositeVertex(this.grafo, e, u);
			}
		}
		
		result.add(a);
		
		for(DefaultWeightedEdge e : this.grafo.edgesOf(u)) {
			if(this.grafo.getEdgeWeight(e)==max) {
				result.add(Graphs.getOppositeVertex(this.grafo, e, u));
			}
		}}
		
		return result;
	}
	
	public void simula(int I, int G) {
		Simulator sim = new Simulator(I, G, this);
		sim.init();
		sim.run();
		System.out.println(sim.getTime());
	}
	
}
