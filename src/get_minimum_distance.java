import java.util.*;
import java.util.*;

public class get_minimum_distance {

	int[] dist;
	int[] pred;
	boolean[] visited;
	linkedList Obj = new linkedList();
	
	public int[] dijkstra(ArrayList<ArrayList<Integer>> links, int s, int numOfNodes){
		
		dist = new int[numOfNodes];
		pred = new int[numOfNodes];
		visited = new boolean[numOfNodes];
		
		for(int i=0;i<dist.length;i++){
			dist[i] = Integer.MAX_VALUE;
			int next = minVertex(dist,visited);
			visited[next] = true;
			
			ArrayList<Integer> n = new ArrayList<Integer>();
			ArrayList<Integer> n_distance = new ArrayList<Integer>();
			
			for(int j=1;j<links.get(next).size();j=j+2){
				n.add(links.get(next).get(j));
				n_distance.add(links.get(next).get(j+1));
			}
			
			for(int j=0;j<n.size();j++){
				int v = n.get(j);
				int v_dist = n_distance.get(j);
				int d = dist[next]+v_dist;
				if(dist[v]>d){
					dist[v]= d;
					pred[v]= next;
				}
			}
		}
		
		return pred;
		
	}
	
	public int minVertex(int[] dist,boolean[] v){
		
		int x = Integer.MAX_VALUE;
		int y = -1;
		
		for(int i=0;i<dist.length;i++){
			if(!v[i] && dist[i]<x){
				y=i;
				x=dist[i];
			}
		}
		return y;
	}
	
	public void printPath(ArrayList<ArrayList<Integer>>links,int source,int destination,ArrayList<ArrayList<String>>allLocations){
		
		ArrayList<String> completePath = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		int x= source;
		int n1 = source;
		int n2 = pred[source];
		while(x!= destination){
			path.add(links.get(x).get(0));
			completePath.add(allLocations.get(x).get(1));
			distances.add(get_distances_between_nodes(links,n1,n2));
			x = pred[x];
			n1 = n2;
			n2 = pred[n2];
		}
		path.add(links.get(destination).get(0));
		for(int i=0;i<completePath.size();i++){
			System.out.print(completePath.get(i)+": walk a distance of "+distances.get(i)+"m -->");
		}
		completePath.add(allLocations.get(destination).get(1));
		System.out.print(completePath.get(completePath.size()-1));
		System.out.println("\n"+path);
		}
	
	public int get_distances_between_nodes(ArrayList<ArrayList<Integer>> links, int n1, int n2){
		
		for(int i=1;i<links.get(n1).size();i=i+2){
			if(links.get(n1).get(i)==n2)
				return links.get(n1).get(i+1);
		}
		return 0;
	}
}
