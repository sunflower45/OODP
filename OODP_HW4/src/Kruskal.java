import java.awt.*;
import java.applet.*;
import java.util.*;

public class Kruskal extends AlgorithmAnimator{
	//order 링크리스트에는 가야할 점이 각각 순서대로 들어가있다. 가장 짧은 선 순서대로 13->43이 가장 짧은 선이라면 13, 43..이 순서대로 링크리스트로 들어가있다.
	static LinkedList<Integer> order = new LinkedList<Integer>();
	
	//nodeNum 이 static이어야 applet에서 작동된다. 전역변수여야 paint를 다시 호출할때마다 그림이 갱신된다. prim에서 nodeNum이랑 의미는 약간 다르지만 비슷한 일을 한다.
	static int nodeNum = 0;
	
	protected void algorithm(Graphics g){
		
		//크루스칼은 가장 짧은 거리를 계산하는 일종의 sorting알골이 필요하기 때매 그걸 priority queue로 구현해줌. arrayDis대신 distance를 우선순위 큐로 구현 해서 작은거부터 꺼낼 수 있게함.
	    PriorityQueue<Double> distance = new PriorityQueue<Double>();
	    
	    //queue는 poll하면 객체가 꺼내지고 없어지기 때문에 임시로 저장할 connect리스트가 있어야함..ㅠㅠ
	    LinkedList<Double> distance_connect = new LinkedList<Double>();
	    
	    Queue<Integer> connect = new LinkedList<Integer>();		
	    
		readText();
		double x; // 두 점 사이의 거리를 계산하고 임의로 저장하고자 한다
	    int[] label = new int[50];		
	    
	    //distance를 우선순위 queue로 구현해서 작은거부터 뺴줘야 되는 알골이 kruskal에는 필요하기 때문에 임의로 캐시가 구현한 discal의 arrayDis를 distance linkedlist로 바꿔줬다.
	    for (int i=0; i<50;i++){
	    	label[i]=i;
	    }
	    
	    for (int i = 0; i < 50; i++) {
	        for (int j = 0; j < 50; j++) {
	            if (i >= j) // i가 j보다 작을 때만 거리를 측정한다
	                 continue;
	             else {
	                 x = getDistance(arrayX[i], arrayY[i], arrayX[j], arrayY[j]);
	                 distance.add(x); //거리 넣는다.
	             }
	         }
	     }
	    	
	    //distance를 쭉 돌면서 distance_connect에 순서대로 넣어준다. 다 돌고 난 다음에 distance_connect.get(0) 즉, 첫번째 리스트 요소에는 제일 작은 거리가 들어가있다. 즉 이건 정렬알고리즘인 것이다.
	    while(!distance.isEmpty()){
	        double temporary = distance.poll();
	        if(!distance_connect.contains(temporary)){
	        	distance_connect.add(temporary);	 //이제 정렬된 distance_connect만 쓴다.
	        }
	    }
	    
	    
	    for(int k=0;k<distance_connect.size();k++){//distance_connect size만큼 처음부터 돌면서 array[i][j]중에 가작 작은 길이에 해당되는 점 순서대로 connect에 element를 집어넣는다.
		    for(int i=0;i<50;i++){
		      	for(int j=i+1;j<50;j++){
		       		if(arrayDis[i][j] == distance_connect.get(k)){ //예를들어 k=0일땐 제일 작은 거리니까 connect에는 제일 작인 거리를 연결하는 두 점 주개가 i, j로 들어간다. k=0일때는 10번째로 작은
		       													// 선에 해당되는 두 점 i, j가 connect에 들어간다.
		       			connect.offer(i);
		       			connect.offer(j);
		       		}
		       	}
		    }
	    }
	    
	    // 98인 이유가 order에는 출력되야 할 2개이점이 계속 들어가는데, 총 들어가야 할 '선'의 수는 49개이다. 정점이 50개면 간선이 49개니까.. 간선이 49개면 들어가야 할 점의 수는 중복 상관없이 98개이다.
	    //처음엔 order는 0으로 시작했다가, while이 끝나고 나면 98개가 들어가있다.
	    while(order.size()<98){
	    	int temp1, temp2, temp3=0;
	    	if(connect.isEmpty()){
	    		break;
	    	}
	    	
	    	//poll하면 데이터 없어지기 떄문에 tmp1, 2에 잠시 저장해두고 비교한다.
	    	temp1 = connect.poll();
	    	temp2 = connect.poll();
	    	   
	    	// 이건 각 정점에 cycle이 있는지 확인하는 부분이다 . cycle이 있으면 order에 안넣고 없으면 넣는다. label을 달아놓고 각 vertex 번호로 초기화시킨다. 
	    	// 그래놓고 연결되는 vertex마다 대표 vertex number를 정해서 연결된 vertex마다 같은 number로 label에 저장되게 한다.
	    	// 그래서 label[i] = label[j] 란 뜻은 i, j번째 점이 연결돼있단 뜻이다.
	    	if(label[temp1] != label[temp2]){ //연결 안되있을 때 order에 넣어야 되기 때문에 !=이다.
	    		temp3 = label[temp2]; // cycle없는 두 점을 연결시켜줘야 되기 때문에 나중 loop를 위해서 두 점이 연결됐다는 흔적을 label에 남긴다. 두번째 vertex label을 tmpe3에 저장한다.
	    								// 둘중 어느 vertex의 lable을 선택해도 상관 없긴 하다. 나는 기준을 두번째 vertex로 정했다.
	    		for(int i=0;i<50;i++){
	    			if(temp3 == label[i]){//첫번째 vertex에 이전에 연결된 다른 vertex들과 비교한다. 다른 vertex과 비교하면서 첫번째 vertex와 연결된 다른 vertex들에도 2번쨰가 연결됐다는 표시를 남긴다.
	    				label[i]=label[temp1];
	    			}
	    		}
		    	order.add(temp1); //order에 연결될 두 vertex를 추가
		    	order.add(temp2);
	    	}
	    	   
	    }

	    //마찬가지로 order에 들어가 있는 node 수가 중복 포함 98개라서 98번 while하면서 선을 찍는다.
		while(nodeNum < 98){
			g.setColor(Color.PINK);
			g.drawLine(arrayX[order.get(nodeNum)], arrayY[order.get(nodeNum)], arrayX[order.get(nodeNum+1)], arrayY[order.get(nodeNum+1)]);
			System.out.println(nodeNum + " : " + order.get(nodeNum) +  ", " +order.get(nodeNum+1) + ", "+arrayDis[order.get(nodeNum)][order.get(nodeNum+1)]);
			
			//order에 13, 34, 31, 39.. 이런식으로 드가있는데 13,34선찍고, 31,39 선찍고 이렇게 두칸씩 뛰어야하기 때문에 +2하는 것이다. 
			nodeNum=nodeNum+2;
			pause(g);
			
		}

			
	}
}
