/* OVERVIEW NODE CLASS
 * 5 2 3
 * 6 1 0
 * 8 7 4
 * this is Node
 * Node contains information to calculate A*
 *  G : weight from initial state to current state
 *  H : heuristic
 *  	counting the number of number that is misplaced comparing goal state
 *  F = H + G
 *  
 */
import java.util.*;

// Node class //
public class Node{	
	public State state; //current state with 2D array
	// save just before states(=close node) 
	// without this parent close node, state back to past state and make cycle
	public Vector<State> parent = new Vector<State>();
	
	public int f; // F(n)
	public int g; // G(n)
	public int h; // H(n)
	
	// default constructor //
	public Node(){ 
		f = 0;
		g = 0;
		h = 0;
	}
	// constructor // 
	public Node(State s, State parent){
		f = 0;
		g = 0;
		h = 0;
		this.state = s;
		this.parent.add(parent);
	}
	
	// overriding equals method //
	public boolean equals(Node n){
		if(this.state.equals(n.state)){
			return true;
		} else {
			return false;
		}
	}

	
	// estimate H(heuristic)
	// count different number comparing goal state
	public int heuristic(State goal){
		
		int misplace = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.state.value[i][j]!=goal.value[i][j]){
					misplace++;
				}
			}
		}
		return misplace;

	}
	
	public void iterator(Vector<Node> items){
		NodeIterator NodeIter = new NodeIterator(items);
		System.out.println("=============");
		while(NodeIter.hasNext()){
			Node n_item = (Node)NodeIter.next();
			for(int i=0;i<3;i++){
				System.out.println(n_item.state.value[i][0]+" "+n_item.state.value[i][1]+" "+n_item.state.value[i][2]);
			}
			System.out.println("=============");
		}

	}

	
	// return adjacent node method //
	public Vector<Node> adjacent(){
		// set of adjacent node
		// used vector data structure
		Vector<Node> adj = new Vector<Node>(); 
		
		
		/*
		 * for example, when we call
		 * 2 8 3
		 * 1 6 4
		 * 7 0 5 .adjacent()
		 * 
		 * vector adj contains three nodes
		 * 2 8 4	2 8 3	2 8 3
		 * 1 6 4	1 0 4	1 6 4
		 * 0 7 5	7 6 5	7 5 0
		 * and return
		 */
		
		// where is '0' in state
		int x = -1;
		int y = -1;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.state.value[i][j]==0){
					x = i;
					y = j;
				}
			}
		}
		if(x-1>=0){ // up
					/* based on 0 if 0 can go up save into adj
					 * for example
					 * 2 0 3
					 * 1 8 4
					 * 7 6 5 '0' cannot go up
					 * adj do not contain up node with this state
					 */
			
			int[][] temp_value = new int[3][3];
			
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					temp_value[i][j] = this.state.value[i][j];
				}
			}
			temp_value[x][y] = this.state.value[x-1][y];
			temp_value[x-1][y] = 0;
			State temp_state = new State(temp_value);
			
			
			// this.parent contain just before state info
			/*
			 * for example
			 * (1)---------> (2)
			 * 2 8 3		2 8 3
			 * 1 6 4		1 0 4
			 * 7 0 5  		7 6 5 
			 * if node move from (1) to (2)
			 * parent contain (1)
			 * without this part, cycle occurs
			 * 
			 */
			Node child_node = new Node();
			child_node.state = temp_state;
			for(int i=0;i<this.parent.size();i++){
				child_node.parent.add(this.parent.get(i));
			}
			
			// copy every passed parent path into child
			// additionally, add current state into child
			// when we print final node's parent vector 
			// we can see path that passed by
			child_node.parent.add(this.state);
			if(!(temp_state.equals(this.parent.get(this.parent.size()-1)))){
				adj.add(child_node);
			}
		}
		
		if(x+1<3){ //down
			int[][] temp_value = new int[3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					temp_value[i][j] = this.state.value[i][j];
				}
			}
			
			temp_value[x][y] = this.state.value[x+1][y];
			temp_value[x+1][y] = 0;
			State temp_state = new State(temp_value);
			Node child_node = new Node();
			child_node.state = temp_state;
			for(int i=0;i<this.parent.size();i++){
				child_node.parent.add(this.parent.get(i));
			}
			child_node.parent.add(this.state);
			if(!(temp_state.equals(this.parent.get(this.parent.size()-1)))){
				adj.add(child_node);
			}
		}
		if(y+1<3){ //right
			int[][] temp_value = new int[3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					temp_value[i][j] = this.state.value[i][j];
				}
			}
			temp_value[x][y] = this.state.value[x][y+1];
			temp_value[x][y+1] = 0;
			State temp_state = new State(temp_value);
			Node child_node = new Node();
			child_node.state = temp_state;
			for(int i=0;i<this.parent.size();i++){
				child_node.parent.add(this.parent.get(i));
			}
			child_node.parent.add(this.state);
			if(!(temp_state.equals(this.parent.get(this.parent.size()-1)))){
				adj.add(child_node);
			}
		}
		if(y-1>=0){ //left
			int[][] temp_value = new int[3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					temp_value[i][j] = this.state.value[i][j];
				}
			}
			temp_value[x][y] = this.state.value[x][y-1];
			temp_value[x][y-1] = 0;
			State temp_state = new State(temp_value);
			Node child_node = new Node();
			child_node.state = temp_state;
			for(int i=0;i<this.parent.size();i++){
				child_node.parent.add(this.parent.get(i));
			}
			child_node.parent.add(this.state);
			if(!(temp_state.equals(this.parent.get(this.parent.size()-1)))){
				adj.add(child_node);
			}
		}
		return adj; //finally return adj that is adjacent of this
	}
}
