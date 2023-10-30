package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class ShortestPath<T> {
    private static final int EDGE_WEIGHT = 1;

    private final Graph<T> g;
	private final Set<T> nodes;
    private final int edgeWeight;

	private HashMap<T, Integer> dist; 
	private HashMap<T, T> prev;

    public ShortestPath(Graph<T> g, int edgeWeight) {
        this.g = g;
        this.nodes = g.nodeSet();
        this.edgeWeight = edgeWeight;
    }

    public ShortestPath(Graph<T> g) {
        this(g, EDGE_WEIGHT);
    }

    private void init(T source) {
        this.dist = new HashMap<>();
        this.prev = new HashMap<>();
        //foreach node set default values
        for (final var node : this.nodes) {
            this.dist.put(node, Integer.MAX_VALUE);
            this.prev.put(node, null);
        }
        this.dist.put(source, 0);
    }

    //dijkstra's relax step
	private void relax(final T u, final T v) {
        final int distance = this.dist.get(u) + this.edgeWeight;
        if(distance < this.dist.get(v)) {
            this.dist.put(v, distance);
            this.prev.put(v, u);
        }
    }

    //recompute priority of element
    private void recomputeQueue(final Queue<T> queue, final T el) {
        queue.remove(el);
        queue.add(el);
    }

    //determinates the path from source to node
	private List<T> getPathFromPrev(final T source, T node) {
		final List<T> path = new ArrayList<>();
		if(prev.get(node) != null || node.equals(source)) {
			while(node != null) {
				path.add(node);
				node = prev.get(node);
			}
		}
		//reverse the list
		Collections.reverse(path);
		return path;
	}

    public List<T> getPath(T source, T target) {
        //create structures and init values
        this.init(source);
		//create queue and init
		final Queue<T> queue = new PriorityQueue<>(this.nodes.size(), 
            new PriorityComprator<>(this.dist));
        queue.addAll(this.nodes);
        //while queue is not empty
        while(!queue.isEmpty()) {
            final var node = queue.poll();
            //if we have reached the target node
            if(node.equals(target)) {
                return this.getPathFromPrev(source, target);
            }
            //foreach neighbor of current node
            final var neighbors = this.g.linkedNodes(node);
            for (final var neighbor : neighbors) {
                if(queue.contains(neighbor)) {
                    this.relax(node, neighbor);
                    this.recomputeQueue(queue, neighbor);
                }
            }
        }
        // no path found, return empty list
        return new ArrayList<>();
    }
}