package com.code.saurabh.misc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TaskScheduling {
	List<Integer> servers = new LinkedList<Integer> ();
	List<Integer> tasks = new LinkedList<Integer> ();
	
	TaskScheduling () {
		
	}
	void sort (List<Integer> input) {
		Collections.sort(input, Collections.reverseOrder());
	}
	
	int sum (List<Integer> input) {
		int total = 0;
		for (int i: input) {
			total += i;
		}
		return total;
	}
	private boolean initialCheck () {
		if ( sum(servers) < sum(tasks) ) {
			return false;
		}
		if (servers.get(0) < tasks.get(0)) {
			return false;
		}
		return true;
	}

	private void trim() {
		List<Integer> toberemovedservers = new LinkedList<Integer> ();
		List<Integer> toberemovedtasks = new LinkedList<Integer> ();	
		for (int server : servers) {
			if (tasks.contains(server)) {
				toberemovedservers.add (server);
				toberemovedtasks.add (server);
			}
		}
		tasks.removeAll(toberemovedtasks);
		servers.removeAll(toberemovedservers);
	}
	public boolean isSchedulingPossible () {
		trim ();
		sort (servers);
		sort (tasks);
		
		while (true) {
			if (servers.size() == 0 || tasks.size() == 0) {
				break;
			}
			int current_server = servers.get(0);
			if (!initialCheck()) {
				return false;
			}
			int new_cap = getTasks(current_server);
			servers.remove(0);
			if (new_cap > 0) {
				servers.add(new_cap);
				sort(servers);
			}
		}
		if (tasks.size() == 0)
			return true;
		return false;	
	}

	private int getTasks(int current_server) {
		int total = 0;
		List<Integer> toberemovedtasks = new LinkedList<Integer> ();
		for (int t: tasks) {
			if ((total + t) == current_server) {
				toberemovedtasks.add(t);
				total = total + t;
				break;
			}
			else if ((total + t) < current_server) {
				toberemovedtasks.add(t);
				total = total + t;
			}
		}
		tasks.removeAll(toberemovedtasks);
		return current_server - total;
	}
	
	void setServer (Integer...input) {
		for (int i : input) {
			this.servers.add (i);
		}
	}
	void setTasks (Integer...input) {
		for (int i : input) {
			this.tasks.add (i);
		}
	}

	public static void main (String[] args) {
		TaskScheduling ts = new TaskScheduling ();
		ts.setServer(32,16,8,8);
		ts.setTasks(18,8,8,8,6,6,4,4);

		printResult (ts.isSchedulingPossible());
		
		TaskScheduling ts2 = new TaskScheduling ();
		ts2.setServer(32,8,2);
		ts2.setTasks(28,9);

		printResult (ts2.isSchedulingPossible());
		
		TaskScheduling ts3 = new TaskScheduling ();
		ts3.setServer(14,5);
		ts3.setTasks(8,5,3,3);

		printResult (ts3.isSchedulingPossible());

		TaskScheduling ts4 = new TaskScheduling ();
		ts4.setServer(1,3);
		ts4.setTasks(4);

		printResult (ts4.isSchedulingPossible());

		
	}
	private static void printResult(boolean schedulingPossible) {
		if (schedulingPossible) {
			System.out.println ("Scheduling Possible");
		}
		else {
			System.out.println ("Scheduling Not Possible");
		}		
	}
}
