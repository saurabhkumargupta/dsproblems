package com.code.saurabh.datastructure.graph.elements;

public class Vertex {
	Integer x = null;
	Integer y = null;
	Object value;
	
	public Vertex (int x, int y, Object v) {
		this(x,y);
		this.value = v;
	}

	public Vertex (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vertex (Object v) {
		this.value = v;
	}
	
	public Object getValue () {
		return value;
	}
	
	public int getX () {
		return x.intValue();
	}

	public int getY () {
		return y.intValue();
	}

	public boolean equal (int i, int j) {
		if (this.x.intValue() == i && this.y.intValue() == j)
			return true;
		return false;
	}

	public boolean equal (int i, int j, Object v) {
		if (this.x.intValue() == i && this.y.intValue() == j
				&& this.value.equals(v))
			return true;
		return false;
	}
	
	public boolean equal (Object v) {
		if (this.value.equals(v))
			return true;
		return false;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		if (x != null && y != null) {
			sb.append(x);
			sb.append(",");
			sb.append(y);
			sb.append(":");
		}
		if (value != null)
			sb.append(value);
		return sb.toString();
	}
}
