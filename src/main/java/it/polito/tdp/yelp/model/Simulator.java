package it.polito.tdp.yelp.model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import org.jgrapht.*;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.yelp.model.Event.tipo;


public class Simulator {
	
	int I; //intervistati
	int G; //giornalisti
	
	int time=0;
	TreeMap<Integer, Integer> conta;
	Queue<Event> queue;
	Graph<User, DefaultWeightedEdge> grafo;
	Model model;
	LinkedList<User> insieme;
	Set<User> intervistati;
	
	
	public Simulator(int I, int G, Model model) {
		this.I = I;
		this.G = G;
		this.model = model;
		grafo = this.model.getGrafo();
		queue = new PriorityQueue<>();
		conta = new TreeMap<Integer, Integer>();
		intervistati = new HashSet<>();
		insieme = new LinkedList<>(this.grafo.vertexSet());
	}
	
	public void init() {
		for(int g = 1;g<=G;g++) {
			conta.put(g, 0);
			int index = (int) (Math.random() * insieme.size());
			queue.add(new Event(tipo.FINE, g, insieme.get(index), 1));
			insieme.remove(index);
		}
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			process(queue.poll());
		}
	}
	
	public void process(Event e) {
		
		time = e.getTime();
		
		switch(e.getEvento()) {
		
		case I:
			if(model.getSimile(e.getUser()).size()>0) {
				for(User u : model.getSimile(e.getUser())) {
					if(insieme.contains(u)) {
						queue.add(new Event(tipo.FINE, e.getGiornalista(), u, e.getTime()+1));
						insieme.remove(u);
						break;
					}
				}
			}
			if(insieme.size()>0) {
			User u = insieme.get((int) (Math.random() * insieme.size()));
			queue.add(new Event(tipo.FINE, e.getGiornalista(), u, e.getTime()+1));
			insieme.remove(u);
			}
			break;			
			
		case III:
			queue.add(new Event(tipo.FINE, e.getGiornalista(), e.getUser(), e.getTime()+1));
			conta.put(e.getGiornalista(), conta.get(e.getGiornalista())-1);
			
			break;
			
		case FINE:
			conta.put(e.getGiornalista(), conta.get(e.getGiornalista())+1);
			
			double p = Math.random();
			if(p<0.8 && insieme.isEmpty()) {
				break;
			}
			if(p<0.6) {	
				
				queue.add(new Event(tipo.I, e.getGiornalista(), e.getUser(), e.getTime()));
				
			}else if(p>=0.6 && p<0.8) {
				
				queue.add(new Event(tipo.I, e.getGiornalista(), e.getUser(), e.getTime()+1));
				
			}else{
				
				queue.add(new Event(tipo.III, e.getGiornalista(), e.getUser(), e.getTime()));
				
			}
			break;
		}
	}

	public int getTime() {
		return time;
	}

	public TreeMap<Integer, Integer> getConta() {
		return conta;
	}

	
	
	

}
