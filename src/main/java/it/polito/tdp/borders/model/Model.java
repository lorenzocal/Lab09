package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.borders.db.BordersDAO;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

public class Model {
	
	private BordersDAO dao;
	private Map<Integer, Country> countriesIdMap;
	private Map<Country, List<Country>> bordersMap;
	private Graph<Country, DefaultEdge> graph;
	
	
	public Model() {
		this.bordersMap = new HashMap<Country, List<Country>>();
		this.dao = new BordersDAO();
		this.countriesIdMap = new HashMap<Integer, Country>();
	}
	
	public void popolateCountriesIdMap() {
		
		for (Country country : this.dao.loadAllCountries()) {
			this.countriesIdMap.put(country.getcCode(), country);
		}
	}
	
	public void popolateBordersMap(Integer anno) {
		
		for (Border border : this.dao.getCountryPairs(anno)) {
			
			if (border.getContType() == 1) {
				
				Country firstCountry = this.countriesIdMap.get(border.getState1No());
				
				Country secondCountry = this.countriesIdMap.get(border.getState2No());
				
				if (this.bordersMap.containsKey(firstCountry)) {
					this.bordersMap.get(firstCountry).add(secondCountry);
				}
				else {
					ArrayList<Country> neighboring = new ArrayList<Country>();
					neighboring.add(secondCountry);
					this.bordersMap.put(firstCountry, neighboring);
				}
			}
		}
	}
	
	public BordersDAO getDao() {
		return dao;
	}

	public Map<Integer, Country> getCountriesIdMap() {
		return countriesIdMap;
	}

	public Map<Country, List<Country>> getBordersMap() {
		return bordersMap;
	}
	
	public void creaGrafo(Integer anno) {
		
		this.popolateCountriesIdMap();
		this.popolateBordersMap(anno);
		
		this.graph = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(this.graph, this.bordersMap.keySet());
		
		for (Country country : this.bordersMap.keySet()) {
			for (Country neighbor : this.bordersMap.get(country)) {
				if (!this.graph.containsEdge(country, neighbor)) {
					this.graph.addEdge(country, neighbor);
				}
			}
		}
		
		System.out.println("Numero di vertici: " + this.graph.vertexSet().size());
		System.out.println("Numero di archi: " + this.graph.edgeSet().size());
	}

	public Graph<Country, DefaultEdge> getGraph() {
		return graph;
	}
	
	public Integer getNumberOfConnectedSets() {
		ConnectivityInspector<Country, DefaultEdge> connectInspect = new ConnectivityInspector<Country, DefaultEdge>(this.graph);
		return connectInspect.connectedSets().size();
	}
	
	public Set<Country> connectedSetOf(Country country){
		
		BreadthFirstIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<Country, DefaultEdge>(this.graph, country);
		
		Set<Country> connectedSet = new HashSet<Country>();
		
		while (visita.hasNext()) {
			Country c = visita.next();
			connectedSet.add(c);
		}
		
		connectedSet.remove(country);
		return connectedSet;
	}
}
