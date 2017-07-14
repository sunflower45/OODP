/*
 * 8-sliding puzzle soling class
 * A*, BFS algorithm used
 * using the principle of BFS and optimal sub-structure
 * find shortest move to goal state sliding '0'
 */

import java.util.*;
public class Puzzle {

	public Node init_node; // initial state node
	public Node goal_node; // goal state node
	/* goal node can be
	 * 1 2 3	1 2 3
	 * 8 0 4	4 5 6
	 * 7 6 5 or	7 9 0
	 */

	public static Node close_node; // same as parent node. it prevent cycle
	public Vector<Node> adj_nodes; // after .adjacent()

	public static int distance; //count distance from init_state to current state
	/*
	 * 2 8 3	2 8 3	2 0 3
	 * 1 6 4	1 0 4	1 8 4
	 * 7 0 5	7 6 5	7 6 5
	 * If '0' move this order, distance is 2
	 * In A*, based on F=G+H calculate optimal substructure.
	 * G is distance part
	 * even if 2 node has same misplace number, the smaller moving distance, the better
	 * G can show this
	 */

	public Puzzle(){
		int[][] init = new int[3][3];
		int[][] goal = new int[3][3];
		init[0][0]=5;
		init[0][1]=2;
		init[0][2]=3;
		init[1][0]=6;
		init[1][1]=1;
		init[1][2]=0;
		init[2][0]=8;
		init[2][1]=7;
		init[2][2]=4;

		goal[0][0]=1;
		goal[0][1]=2;
		goal[0][2]=3;
		goal[1][0]=8;
		goal[1][1]=0;
		goal[1][2]=4;
		goal[2][0]=7;
		goal[2][1]=6;
		goal[2][2]=5;
		distance = 0;
		State init_state = new State(init);
		State goal_state = new State(goal);
		init_node = new Node(init_state, init_state);
		goal_node = new Node(goal_state, goal_state);
		adj_nodes = new Vector<Node>();

	}

	// main algorithm //
	public Node sliding() {
		// current node and adjacent nodes are pushed in total_ndoes priory queue
		// every grey nodes goes in total_nodes
		// in total_nodes priory queue, minimal value node pop out
		// and the minimal node become black vertex
		// from the minimal node, search process keep going
		Vector<Node> total_nodes = new Vector<Node>();

		Node min_node = new Node(); // node for minimal value

		int min = 99999999;
		int min_index = -1;

		State parent_temp = new State(init_node.state.value);

		// main while loop //
		// init_node is current (white -> gray) vertex
		// if init_node become same as state of goal_node
		// we find answer
		while(!init_node.equals(goal_node)){
			adj_nodes.clear();

			adj_nodes = init_node.adjacent(); //adj_nodes has current node's adjacent nodes

			// initialize H, G, F value in adjacent nodes
			// and insert into total_node priory queue
			for(int i=0;i<adj_nodes.size();i++){
				adj_nodes.get(i).h = adj_nodes.get(i).heuristic(goal_node.state);
				adj_nodes.get(i).g = (init_node.g)+1;
				adj_nodes.get(i).f = adj_nodes.get(i).g + adj_nodes.get(i).h;
				total_nodes.add(adj_nodes.get(i));
			}

			// In total_nodes, pop minimal node based on F value.
			min = 99999999;
			min_index = -1;

			for(int i=0;i<total_nodes.size();i++){
				if(min > total_nodes.get(i).f){
					min = total_nodes.get(i).f;
					min_node = total_nodes.get(i);
					min_index = i;
				}
			}

			// Now, current node (=init_node) become minimal F node in total_nodes
			parent_temp = init_node.parent.get(init_node.parent.size()-1);
			// if this min_node is same as goal state,
			// init_node also become goal state
			// and then we break out loop
			init_node = min_node;

			// Remove minimal node from total_node
			// because we need search another minimal node
			if(min_index!=-1){
				total_nodes.remove(min_index);
			}
			// increase distance
			distance++;
		}

		// loop out means current node (=init_node) found goal node
		System.out.println("distance : "+distance);
		System.out.println("you found answer!");

		return init_node;
	}


	public boolean contain(Node n, Vector v){
		for(int i=0;i<v.size();i++){
			if(n.equals(v.elementAt(i))){
				return true;
			}
		}
		return false;
	}



}
