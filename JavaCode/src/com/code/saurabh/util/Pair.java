package com.code.saurabh.util;

public class Pair <T1, T2> {
	T1 first;
	T2 second;

	public Pair (T1 f, T2 s) {
		this.first = f;
		this.second = s;
	}

	public Pair() {
		// TODO Auto-generated constructor stub
	}

	public void setFirst (T1 f) {
		this.first = f;
	}

	public T1 getFirst () {
		return first;
	}

	public void setSecond (T2 s) {
		this.second = s;
	}
	
	public T2 getSecond () {
		return second;
	}
	
	public Pair<T1, T2> getPair () {
		return this;
	}

	public void assign(Pair<T1, T2> p) {
		this.first = p.first;
		this.second = p.second;
	}

	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append(first);
		sb.append(",");
		sb.append(second);
		return sb.toString();
	}

	public void print () {
		System.out.println (this.toString());
	}
	/*
	public boolean equals (Pair<Integer, Integer> value){
		if (value.getFirst() == first && value.getSecond() == second){
			return true;
		}
		return false;
		
	}
	*/
}
