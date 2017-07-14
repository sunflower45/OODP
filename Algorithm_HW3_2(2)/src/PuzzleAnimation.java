import java.awt.*;
import java.util.*;

public abstract class PuzzleAnimation extends AnimationApplet{
	
	public static int i=0;
	String numStr;
	public PuzzleAnimation(){
		setDelay(1000);
	}
	
	final protected void pause(){
		if(Thread.currentThread()==animationThread){
			try{
				Thread.sleep(delay);
			}catch(InterruptedException e){}
			repaint();
		}
	}
	
	public void paint(Graphics g){//templete
		drawPuzzle(g);
		PuzzleFunction(g); //이부분이 hook
		//지금 puzzle알고리즘은 A*알고리즘이라는 특수한 알고리즘 갖고 한거다
		//나중에 이 알고리즘을 BFS, DFS, 다익스트라_PuzzleFunction등으로 바꿀 수 있따
	}
	
	//이거는 drawCoordinates(g)역할을 하는 메소드
	protected void drawPuzzle(Graphics g){
		g.drawRect(0, 0, 40, 40);
		g.drawRect(0, 40, 40, 40);
		g.drawRect(0, 80, 40, 40);
		g.drawRect(40, 0, 40, 40);
		g.drawRect(40, 40, 40, 40);
		g.drawRect(40, 80, 40, 40);
		g.drawRect(80, 0, 40, 40);
		g.drawRect(80, 40, 40, 40);
		g.drawRect(80, 80, 40, 40);		
	}
	public abstract Node func(Puzzle p); //여기 hook
	protected void PuzzleFunction(Graphics g){
		Puzzle p = new Puzzle();
		Node answer = new Node();
		answer = func(p);//여기도 hook 여러방법으로 answer받을수있음
		
		if(i==answer.parent.size()-2){
			
			stop();
		}
		System.out.println("i : "+i);
		numStr = String.valueOf(answer.parent.get(i).value[0][0]);
		g.drawString(numStr, 20, 20);
		numStr = String.valueOf(answer.parent.get(i).value[0][1]);
		g.drawString(numStr, 60, 20);
		numStr = String.valueOf(answer.parent.get(i).value[0][2]);
		g.drawString(numStr, 100, 20);
		numStr = String.valueOf(answer.parent.get(i).value[1][0]);
		g.drawString(numStr, 20, 60);
		numStr = String.valueOf(answer.parent.get(i).value[1][1]);
		g.drawString(numStr, 60, 60);
		numStr = String.valueOf(answer.parent.get(i).value[1][2]);
		g.drawString(numStr, 100, 60);
		numStr = String.valueOf(answer.parent.get(i).value[2][0]);
		g.drawString(numStr, 20, 100);
		numStr = String.valueOf(answer.parent.get(i).value[2][1]);
		g.drawString(numStr, 60, 100);
		numStr = String.valueOf(answer.parent.get(i).value[2][2]);
		g.drawString(numStr, 100, 100);
		i++;
		pause();
	}
}
