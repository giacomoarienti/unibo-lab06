package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<T> implements Graph<T> {
    private final Map<T, Set<T>> map = new HashMap<>();

	@Override()
	public void addNode(T node) {
		this.map.put(node, new HashSet<T>());
	}

	@Override()
	public void addEdge(T source, T target) {
	   	this.map.get(source).add(target);
	}

	@Override()
	public Set<T> nodeSet() {
		return this.map.keySet();
	}

	@Override()
	public Set<T> linkedNodes(T node) {
		return this.map.get(node);
	}

	@Override()
	public List<T> getPath(final T source, final T target) {
		return (new ShortestPath<T>(this)).getPath(source, target);
	}
}