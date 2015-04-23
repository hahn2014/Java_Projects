public class Edge {
	Circle first;
	Circle second;
	String label;
	public Edge(Circle newfirst, Circle newsecond, String newlabel) {
		first = newfirst;
		second = newsecond;
		label = newlabel;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Circle getFirst() {
		return first;
	}
	public void setFirst(Circle first) {
		this.first = first;
	}
	public Circle getSecond() {
		return second;
	}
	public void setSecond(Circle second) {
		this.second = second;
	}
}