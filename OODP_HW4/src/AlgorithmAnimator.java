import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;

// Templete Class
public abstract class AlgorithmAnimator extends Applet implements Runnable {
	Thread mainThread = null;
	int delay = 50;

	// cassy가 준거 그대로임
    public int[] arrayX = new int[50]; //50개 점에 대한 각각의 x좌표
    public int[] arrayY = new int[50]; //50개 점에 대한 각각의 y좌표
    public double[][] arrayDis = new double[50][50]; // 50개 점들의 모든 사이
    
    // 좌표 찍혀있는 파일
    String fileToRead = "test.txt";
    StringBuffer strBuff;
    
    // readText해야 file이 read되고 점을 찍기 시작할 수 있기 때문에 init()에 넣었음
	public void init(){

		readText();
	}
	
    // cassy가 준거에서 arrayDis를 2차원 배열로 바꿨음
    public void calDist() {
        double temp; // 두 점 사이의 거리를 계산하고 임의로 저장하고자 한다
        int count = 0; // arrayDis의 index를 계산하기 위함

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                //if (i >= j) // i가 j보다 작을 때만 거리를 측정한다
                  //  continue;
                //else {
                    temp = getDistance(arrayX[i], arrayY[i], arrayX[j], arrayY[j]);
                    arrayDis[i][j]=temp;
                //}
            }
        }
        
      //  for(int i=0;i<50;i++){
        	//for(int j=0;j<50;j++){
        		//if(i>=j)
        		//	continue;
        		//else
        			//System.out.println("["+i+"]"+"["+j+"]"+arrayDis[i][j]);
        	//}
       // }
   }

    // cassy가 준거 그대로
    public double getDistance(int ax, int ay, int bx, int by) {
        // 두 점 사이의 거리를 구하는 공식
        int deltaX = bx - ax;
        int deltaY = by - ay;

        double result = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return result;
    }

    final public double[][] getArrayDis(){
        return arrayDis;
    }
    // test.txt 받아들이는 method, 교수님readFileApplet 코드보고 수정했음
    public void readText() {
    	int index = 0;
    	String line;
    	try {
    		URL url = new URL(getCodeBase(), fileToRead);
    		InputStream in = url.openStream();
    		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
    		strBuff = new StringBuffer();
    		//이부분은 캐시가 짯다
            while ((line = bf.readLine()) != null) {
                //파일을 한 줄씩 읽어 각각의 좌표를 array에 넣는다
                String[] temp = line.split(", ");

                arrayX[index] = Integer.parseInt(temp[0]);
                arrayY[index] = Integer.parseInt(temp[1]);

                index++;
                //bf.close();
            }
    	} catch(IOException err) {
    		System.out.println("IOError");
    	}
        //for (int i = 0; i < 50; i++)
            //System.out.println(arrayX[i] + " " + arrayY[i]);
    }
    
    // Hook Method PPT랑 다른점은 algorithm에 parameter로 Graphics g 를 넣었다.. 안넣으니까 애플릿 안그려져서 넣었다ㅠ
	abstract protected void algorithm(Graphics g);
	
	//일반적인 start함수
	public void start() {
		if(mainThread == null) {
			mainThread = new Thread(this);
			mainThread.start();
		}
	}
	
	//일반적인 stop함수
	public void stop(){
		mainThread = null;
	}
	
	//일반적인 run함수
	public void run() {
		if(Thread.currentThread() == mainThread) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {}
			repaint();
		}
	}
	
	//pause에도 graphics를 넣었음 아니면 애플릿이 안그려지더라구ㅠㅠ 그리고 paint를 계속 호출하면서 갱신
	final protected void pause(Graphics g) {
			paint(g);
	}
	
	// 알고리즘을 call하는 함수. ppt에는 run()함수에 구현돼있는데 graphics가 들어가야돼서 걍 paint에 구현했음
	public void paint(Graphics g){
		readText(); //여기서 파일 읽어서 점찍어야돼기 때문에 
		calDist(); //여기서 arrayX, arrayY를 써야되기 떄문에
		
		// 점이 찍혀 나오는 for문, 어느 알고리즘이나 공통적일 것임, for을 row, col로 나눠서 쓰는 이유는 점을 두껍게 하기 위해서임
		for(int i=0;i<50;i++){
			for(int row=0;row<2;row++){
				for(int col=0;col<2;col++){
					//점의 색깔
					g.setColor(Color.DARK_GRAY);
					//drawLine에서 x,y 같으면 선이 아니라 점이 찍힌다. 
					g.drawLine(arrayX[i]+col, arrayY[i]+row, arrayX[i]+col, arrayY[i]+row);
				}
			}	
		}
		
		//이거 없으면 선이 한번에 그려진다. 하나하나 순서대로 그려지는게 아니고
		try{
			Thread.sleep(delay);
		}catch(Exception e){}
		
		//알고리즘을 호출한다
		algorithm(g);
	}
}
