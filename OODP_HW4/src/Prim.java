import java.awt.*;
import java.applet.*;
import java.util.*;

public class Prim extends AlgorithmAnimator{
	

	static int nodeNum = 0; //전체 노드만큼 돌리도록
	
	//진짜 중요한 핵심 리스트. 이 리스트 안에 visit되는 노드가 순서대로 들어가있어서 그 순서대로 애플릿에 그려진다.
	static LinkedList<Integer> order = new LinkedList<Integer>();
	
	//만약 5번쨰 vertex가 방문되었으면 visited[5] =  true, 안하면 false
	static boolean[] visited = new boolean[50];

	protected void algorithm(Graphics g) {
		readText(); // 점을 읽어들이기 위해 파일 읽어들어옴
		calDist(); // dist 계산
		visited[0] = true;
		while (nodeNum != 49) {
			double min = 10000000;
			int x = 0;
			int y = 0;
			for(int i = 0; i < visited.length; i++) {
				if(visited[i] == true) {
					for(int j = 0; j < 50; j++) {
						if(i == j)
							continue;
						if(visited[j] == true)
							continue;
						if(arrayDis[i][j] < min) {
							min = arrayDis[i][j];
							x = i;
							y = j;
						}
					}
				}
			}
			visited[x] = true;
			if(!order.contains(x)){
				order.add(x);
			}
			
			visited[y] = true;
			if(!order.contains(y)){
				order.add(y);
			}
			//선의 색깔
			g.setColor(Color.green);
			// 점에서 점으로 선을 그린다.
			g.drawLine(arrayX[x], arrayY[x], arrayX[y], arrayY[y]);
			//pause가 while안에 있다. 노드 하나하나 pause로 paint를 다시 호출해서 선을 그리기 때문
			pause(g);
			
			nodeNum++;
			

		}
			

		for(int i=0;i<order.size();i++){
			System.out.println("order["+i+"] : " + order.get(i));
		}
		System.out.println("finish while");
	}

}