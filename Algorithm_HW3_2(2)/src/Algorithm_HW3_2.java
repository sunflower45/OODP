import java.util.*;
import java.awt.*;
public class Algorithm_HW3_2{

	public static void main(String[] args){
		Puzzle p = new Puzzle();
		Node answer = new Node();
		answer = p.sliding();
		State s_iter = new State();
		s_iter.iterator(answer.parent);
		
		
		
	}
}
