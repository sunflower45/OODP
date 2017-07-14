import java.awt.*;

public class Dijkstra extends AlgorithmAnimator{
    private static int nodeNum=50;         // 노드 수
    double shortDistance[] = new double[nodeNum + 1];   // 최단 거리를 저장할 변수
    boolean[] check = new boolean[nodeNum + 1];    // 해당 노드 방문 여부
    int arbitStart = 37;                             // 시작 노드, 50이하 아무 값이나 넣어서 돌린다.
    static int l = 0;
    public void checkFifty(){
        for(int i=0;i<nodeNum;i++){
            for(int j=0;j<nodeNum;j++) {
                if (arrayDis[i][j] >= 50)
                    arrayDis[i][j] = 0;
            }
        }
    }

    @Override
    protected void algorithm(Graphics g) {
        readText();
        calDist();
        checkFifty();                       // 50 이상인 애들 걸러내기

        //distance값 초기화.
        for(int i=1;i<nodeNum+1;i++){
            shortDistance[i] = Double.MAX_VALUE;
        }


        shortDistance[arbitStart]=0;
        check[arbitStart]=true;
        String path="";


        //연결노드 distance갱신
        for(int i=0;i<nodeNum;i++){
            if(!check[i] && arrayDis[arbitStart][i]!=0){
                shortDistance[i] = arrayDis[arbitStart][i];
            }
        }

        for(int j=0;j<nodeNum-1;j++){
            double min=Double.MAX_VALUE;
            int index=0;
            for(int k=1;k<nodeNum+1;k++){
                if(check[k]==false && shortDistance[k]!=Double.MAX_VALUE){
                    if(shortDistance[k]<min) {
                        min = shortDistance[k];
                        index = k;
                    }
                }
            }

            check[index]=true;
            path+=index+" ";
            g.setColor(Color.red);
            g.drawLine(arrayX[arbitStart],arrayY[arbitStart],arrayX[index],arrayY[index]);
            pause(g);

            int nodeMemory=-1;
            for(l=0;l<nodeNum;l++){
                if(check[l]==false && arrayDis[index][l]!=0){
                    if(shortDistance[l]>shortDistance[index]+arrayDis[index][l]){
                        shortDistance[l] = shortDistance[index]+arrayDis[index][l];
                        if(nodeMemory==-1) {
                            System.out.println(index+ " to " + l);
                            g.setColor(Color.red);
                            g.drawLine(arrayX[index],arrayY[index],arrayX[l],arrayY[l]);
                            pause(g);
                        }else{
                            System.out.println(index+ " to " + l);
                            g.setColor(Color.red);
                            g.drawLine(arrayX[nodeMemory],arrayY[nodeMemory],arrayX[l],arrayY[l]);
                            pause(g);
                        }
                        nodeMemory=l;
                    }
                }
            }
        }
    }
}