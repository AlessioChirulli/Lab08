package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	private Graph<Airport,DefaultWeightedEdge>grafo;
	private ExtFlightDelaysDAO dao;
	private Map<Integer,Airport>idMap;
	private String voliScelti;
	
	public Model() {
		dao=new ExtFlightDelaysDAO();
		idMap=new HashMap<Integer,Airport>();
	}
	
	public void creaGrafo(int distanza) {
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.loadAllAirports(idMap);
		Graphs.addAllVertices(this.grafo,idMap.values());
		List<Connessione> connessioni=dao.loadFlights(idMap);
		voliScelti=new String();
		double media=0;
		boolean bool=true; 
		for(Connessione c:connessioni) {
			for(Connessione cc:connessioni) {
				if(c.getA1().getId()==cc.getA2().getId() && c.getA2().getId()==cc.getA1().getId()) {
					media=(c.getDistanza()+cc.getDistanza())/(c.getTot()+cc.getTot());
					if(media>distanza && !grafo.containsEdge(cc.getA1(),cc.getA2()) ) {
					Graphs.addEdge(this.grafo, cc.getA1(), cc.getA2(), media);
					voliScelti+=c.getA1().getAirportName() + " - " + c.getA2().getAirportName() + ": " + media + "\n";
					bool=false;
					}
				}
			}
			if(bool) {
				media=c.getDistanza()/c.getTot();
				if(media>distanza && !grafo.containsEdge(c.getA1(),c.getA2()) ) {
					Graphs.addEdge(this.grafo, c.getA1(), c.getA2(), media);
					voliScelti+=c.getA1().getAirportName() + " - " + c.getA2().getAirportName() + ": " + media + "\n";
				}
			}
			bool=true;
		}
		
	}
	
	public int getNArchi() {
		return grafo.edgeSet().size();
	}
	
	public int getNVertici() {
		return grafo.vertexSet().size();
	}
	public String getVoliScelti() {
		return voliScelti;
	}
}
