import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MST extends Applet{

    public int[] arrayX = new int[50]; //50개 점에 대한 각각의 x좌표
    public int[] arrayY = new int[50]; //50개 점에 대한 각각의 y좌표
    public double[][] arrayDis = new double[50][50]; // 50개 점들의 모든 사이
    
    String fileToRead = "test.txt";
    StringBuffer strBuff;
    
	public void init(){
		readText();
	}
	
    //arrayDis modified
    public void calDist() {
        double temp; // 두 점 사이의 거리를 계산하고 임의로 저장하고자 한다
        int count = 0; // arrayDis의 index를 계산하기 위함

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
               // if (i >= j) // i가 j보다 작을 때만 거리를 측정한다
                 //   continue;
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

    public double getDistance(int ax, int ay, int bx, int by) {
        // 두 점 사이의 거리를 구하는 공식
        int deltaX = bx - ax;
        int deltaY = by - ay;

        double result = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return result;
    }

    public void readText() {
    	int index = 0;
    	String line;
    	try {
    		URL url = new URL(getCodeBase(), fileToRead);
    		InputStream in = url.openStream();
    		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
    		strBuff = new StringBuffer();
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
}
