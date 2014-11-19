import java.io.*;
import java.util.*;

public class implement {
	
	Scanner scn;
	node head;
	Scanner s;
	int data1;
	double time;
	String string1;
	String string2;
	int source;
	int destination;
	int numOfNodes;
	String onGoingEvent;
	ArrayList<ArrayList<String>> allLocations = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<Integer>> links = new ArrayList<ArrayList<Integer>>();
	linkedList Ob = new linkedList();

	public void get_data() {
		
		try{
			scn = new Scanner(new File("/media/DriveE/NLP/simple_map"));
		}catch(Exception e){
			System.out.println("Error in opening the map file");
		}
		numOfNodes = scn.nextInt();
		scn.nextLine();
		ArrayList<String> locationValue = new ArrayList<String>();
		//s = new Scanner(System.in);
		int count = 0;
		
		for(int i=0;i<numOfNodes;i++){
		//do {
			locationValue = new ArrayList<String>();
			//System.out.println("Enter the values of node " + count);
			data1 = count;
			scn.nextLine();
			string1 = scn.nextLine();
			string2 = scn.nextLine();
			System.out.println(count+"-->"+string1);
			Ob.insert(data1, string1, string2);
			//numOfNodes++;
			locationValue.add(String.format("%d", count));
			locationValue.add(string1);
			allLocations.add(locationValue);
			//System.out.println("Do you want to enter more values (Y/n)");
			//String str = s.nextLine();
			count++;
			//if (str.equalsIgnoreCase("Y") == false)
				//flag = true;

	//	} while (flag == false);
		//Ob.printList();
			
	}
		scn.nextLine();
	}

	public void prepare_links_map() {
		
		
		node current = Ob.start;
		s = new Scanner(System.in);
		ArrayList<Integer> row = new ArrayList<Integer>();
		int counter = 0;
		while (current != null) {
			row = new ArrayList<Integer>();
			row.add(counter);
			//System.out.println("enter the number of neighbour of node: "
				//	+ current.location);
			int numOfNeighbours = scn.nextInt();
			//System.out
				//.println("enter the neighbours of node (indexes) with their distances: "
					//	+ current.location);
			for (int i = 0; i < numOfNeighbours; i++) {
				row.add(scn.nextInt());
				row.add(scn.nextInt());
			}
			links.add(row);
			current = current.nextNode;
			counter++;
		}
	}

	public void display_mapping() {

		for (int i = 0; i < links.size(); i++) {
			System.out.println();
			for (int j = 0; j < links.get(i).size(); j++) {
				System.out.print(links.get(i).get(j) + " ");
			}
		}
	}

	public void get_source() {
		Scanner s = new Scanner(System.in);
		System.out.println("\nenter the index of source location");
		source = s.nextInt();
	}

	public void get_destination() {
		Scanner s = new Scanner(System.in);
		System.out.println("enter the index of destination location");
		destination = s.nextInt();
		node current = Ob.start;
		while(current.ID!=destination)
			//System.out.println("djfwjlnv "+current.ID);
			current = current.nextNode;
		onGoingEvent = current.event;
	}
	
	public void get_time(){
		Scanner s = new Scanner(System.in);
		System.out.println("enter the current time");
		time = s.nextDouble();
	}

	public void get_directions() {
		
		get_minimum_distance Object = new get_minimum_distance();
		Object.dijkstra(links,source,numOfNodes);
		Object.printPath(links, source, destination,allLocations);
		
		System.out.println("\n"+onGoingEvent);
		int visit[] = new int[numOfNodes];
		for(int i=0;i<numOfNodes;i++)
			visit[i]=-1;
		
		int d = 0;
		String path="";
		int current = source;
		while (current != destination) {
			int min = links.get(current).get(2);
			int index = links.get(current).get(1);
			for (int i = 2; i < links.get(current).size(); i = i + 2) {
				if (links.get(current).get(i) < min && visit[links.get(current).get(i-1)]==-1) {
					min = links.get(current).get(i);
					index = links.get(current).get(i - 1);
				}
			}
			d = d+min;
			path = String.format("%s %d",path,index);
			//System.out.println(path);
			visit[current]=0;
			current = index;
		}
		//System.out.println(path);
		//System.out.println(d);
	}
	
	public void get_event_details(){
		
		
		try{
			scn = new Scanner(new File("/media/DriveE/NLP/timing_data"));
			
		}catch(Exception e){
			System.out.println("error in opening the timings file");
		}
		
		while(scn.hasNext()){
			String line = scn.nextLine();
			if(line.contains("node")==true && line.contains(String.format("%d", destination))){
				boolean flag = false;
				while(flag==false){
				double t1 = scn.nextDouble();double t2 = scn.nextDouble();
				if(time>=t1 && time<t2){
					System.out.println(scn.nextLine());
					System.out.println("current event: "+scn.nextLine());
					flag = true;
					return;
				}
				else{
					scn.nextLine();
					scn.nextLine();
					continue;
				}
				}
			}
		}
	}

}
