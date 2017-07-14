import java.util.*;
public class A_Star extends PuzzleAnimation{
	public A_Star(){}
	public Node func(Puzzle p){
		return p.sliding();
	}
}
//여기다대고 애플릿 실행해야됨~
//좀 야매같지만 이름 바꾸는게 무서워서 p.sliding그대로 했음 A*알고리즘이 sliding임
