import java.util.*;
public class simulation {
	public static void main(String[] args){
		DFS dfs = new DFS();
		dfs.search(dfs.vertex);
		dfs.transpose();
	}
}