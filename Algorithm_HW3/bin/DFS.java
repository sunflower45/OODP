import java.util.*;

public class DFS {
	// variable declaration //
	protected static int size = 8; // size of vertices
	protected static int time = 0; // timestamp
	// initial state of vertices
	protected static LinkedList<Integer>[] vertex = new LinkedList[size]; 
	// transposition vertices
	protected static LinkedList<Integer>[] vertex_t = new LinkedList[size]; 
	protected static LinkedList<Integer>[] temp = new LinkedList[size]; 
	protected static int[] d = new int[size]; // timestap for discovered node
	protected static int[] f = new int [size]; //timestap for finished node
	protected static int[] order = new int[size]; 
	protected static int[] parent = new int[size]; // parent for each node
	protected static String[] color = new String[size]; // distingush if discovered or not
	
	
	//constructor//
	public DFS(){
		//initializing
		for(int i=0;i<size;i++){
			vertex[i] = new LinkedList<Integer>();
			vertex_t[i] = new LinkedList<Integer>();
			temp[i]=new LinkedList<Integer>();
			color[i] = "white";
			parent[i] = -1;
			d[i] = 0;
			f[i] = 0;
		}
		
		// vertices information
		vertex[0].add(1);
		vertex[1].add(4);	vertex[1].add(5);	vertex[1].add(2);
		vertex[2].add(6);	vertex[2].add(3);
		vertex[3].add(2);	vertex[3].add(7);
		vertex[4].add(0);	vertex[4].add(5);
		vertex[5].add(6);
		vertex[6].add(5);	vertex[6].add(7);
		vertex[7].add(7);
		
	}
	
	public void search(LinkedList<Integer>[] vertex){
		time = 0; // start searching
		for(int i=0;i<size;i++){ // for each node
			if(color[i].equals("white")){ // if v in vertices is undiscovered
				visit(vertex, i); // call visit with v
				System.out.println("=====Strongly Connected Component======");
			}
		}
	}
	
	public void visit(LinkedList<Integer>[] vertex, int u){
		color[u]="gray"; // visited but not finished
		time = time + 1; // time stamp incrasing
		d[u] = time; // gray means that node is discovered
		System.out.println("d["+u+"] : "+d[u]);
		
		// for each adjacent node from u
		for(int i = 0; i < vertex[u].size(); i++){
			
			// if adjacent node is not discovered
			if(color[vertex[u].get(i)].equals("white")){
				// save parent information, here, parent is u
				parent[vertex[u].get(i)] = u;
				// recursively visit again from adjacent node of u
				visit(vertex, vertex[u].get(i)); 
			}
		}
		
		// after visit with depth, u become black
		// searching from u is done
		color[u] = "black";
		time = time + 1; //timestamp increasing
		f[u] = time; // u is finished
		System.out.println("f["+u+"] : "+f[u]);
		
	}
	
	// calculate transposition from graph G //
	public void transpose(){
		//initializing
		for(int i=0;i<size;i++){
			vertex_t[i] = new LinkedList<Integer>();
			temp[i]=new LinkedList<Integer>();
			color[i] = "white";
			parent[i] = -1;
		}
		
		//initializing with decreasing order//
		for(int i=size-1;i>=0;i--){
			for(int j=0;j<vertex[i].size();j++){
				vertex_t[vertex[i].get(j)].add(i);
			}
		}		
		
		// calculate with f[u] decreasing order //
		int k=0;
		for(int i=16;i>=1;i--){
			for(int j=0;j<size;j++){
				if(f[j]==i){
					order[k]=j;
					k++;
				}
			}
			
		}
		for(int i=0;i<size;i++){
			for(int j=0;j<vertex_t[order[i]].size();j++){
				for(int m=0;m<order.length;m++){
					if(order[m]==vertex_t[order[i]].get(j)){
						temp[i].add(m);
					}
				}
			}
		}
		
		// initialize again //
		for(int i=0;i<size;i++){
			d[i]=0;
			f[i]=0;
		}
		
		time =0;
		// search DFS again with finish time decreasing order
		search(temp);
		
	}
}
