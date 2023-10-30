package it.unibo.generics.graph.impl;

import java.util.Comparator;
import java.util.HashMap;

public class PriorityComprator<T> implements Comparator<T> {
	private final HashMap<T, Integer> priority;

	public PriorityComprator(HashMap<T, Integer> priority) {
		this.priority = priority;
	}

	@Override
	public int compare(T o1, T o2) {
		return Integer.compare(this.priority.get(o1), this.priority.get(o2));
	}
}
