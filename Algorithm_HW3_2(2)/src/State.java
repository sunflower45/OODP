/* OVERVIEW STATE CLASS
 * this is basic atomic class
 * state is information for node class
 * simply save 2D array for node
 */

import java.util.*;
public class State {
	// 2D array
	public int[][] value = new int[3][3];
	// constructor //
	public State(){	}
	public State(int[][] v){
		assign(v);
	}
	
	// equals overriding //
	public boolean equals(State state){
		boolean flag = true;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.value[i][j]!=state.value[i][j])
					flag = false;
			}
		}
		return flag;
	}
	
	public void iterator(Vector<State> items){
		StateIterator StateIter = new StateIterator(items);
		System.out.println("=============");
		while(StateIter.hasNext()){
			State n_item = (State)StateIter.next();
			for(int i=0;i<3;i++){
				System.out.println(n_item.value[i][0]+" "+n_item.value[i][1]+" "+n_item.value[i][2]);
			}
			System.out.println("=============");
		}

	}
	// assign //
	public void assign(State s){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				this.value[i][j]=s.value[i][j];
			}
		}
	}
	
	public void assign(int[][] a){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				this.value[i][j]=a[i][j];
			}
		}
	}
	

}
