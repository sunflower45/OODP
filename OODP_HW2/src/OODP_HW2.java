import java.util.*;
import java.io.FileInputStream;
import java.lang.String;
import java.io.PrintWriter;

public class OODP_HW2 {
	public static void main(String[] args){
		
		FileInputStream inputStream=null;
		PrintWriter pw = null;

		try {
			inputStream = new FileInputStream("C:/Users/user/workspace/score.txt");
			pw = new PrintWriter("C:/Users/user/workspace/output.txt");
			
			byte[] readBuffer = new byte[inputStream.available()];
			while(inputStream.read(readBuffer, 0, readBuffer.length)!=-1){}
			String buffer = new String(readBuffer);
			
			String[] line = buffer.split("\n");
			
			////////// problem (a) ///////////
			float[] total= new float[line.length];
			String[] grade = new String[line.length];
			
			for(int i=0;i<line.length;i++){
				String[] word = line[i].split(":");
				total[i] = Float.parseFloat(word[2])*(float)0.1
						+ Float.parseFloat(word[3])*(float)0.1
						+ Float.parseFloat(word[4])*(float)0.1
						+ Float.parseFloat(word[5])*(float)0.3
						+ Float.parseFloat(word[6])*(float)0.4; 	
				if(total[i]>=90)
				{
					grade[i]="A";
				}
				else if(total[i]<90 && total[i]>=80)
				{
					grade[i]="B";
				}
				else if(total[i]<80 && total[i]>=70)
				{
					grade[i]="C";
				}
				else if(total[i]<70 && total[i]>=60)
				{
					grade[i]="D";
				}
				else
				{
					grade[i]="F";
				}
				
			}
			////////// problem (b) ///////////
			float[] project1 = new float[3];
			float[] project2 = new float[3];
			float[] project3 = new float[3];
			float[] midterm = new float[3];
			float[] finalexam = new float[3];
			float[] totalscore = new float[3];

			
			float max=0;
			float min=10000000;
			float sum=0;
			
			for(int j=2;j<=6;j++){
				max=0;min=1000000;sum=0;
				for(int i=0;i<line.length;i++){
					String[] word = line[i].split(":");
					if(Float.parseFloat(word[j])>max)
					{
						max = Float.parseFloat(word[j]);
					}
					if(Float.parseFloat(word[j])<min)
					{
						min = Float.parseFloat(word[j]);
					}
					sum =sum + Float.parseFloat(word[j]);		
				}
				if (j==2){
					project1[0]=max;
					project1[1]=min;
					project1[2]=sum/line.length;
				}
				else if(j==3){
					project2[0]=max;
					project2[1]=min;
					project2[2]=sum/line.length;
				}
				else if(j==4){
					project3[0]=max;
					project3[1]=min;
					project3[2]=sum/line.length;
				}
				else if(j==5){
					midterm[0]=max;
					midterm[1]=min;
					midterm[2]=sum/line.length;
				}
				else if(j==6){
					finalexam[0]=max;
					finalexam[1]=min;
					finalexam[2]=sum/line.length;
				}
			}
			for(int i=0;i<line.length;i++){
				max=0;min=1000000;sum=0;
				if(total[i]>max)
				{
					max = total[i];
				}
				if(total[i]<min)
				{
					min = total[i];
				}
				sum =sum + total[i];	
			}
			totalscore[0]=max;
			totalscore[1]=min;
			totalscore[2]=sum/line.length;
			
			////////// problem (c) ///////////
			int[] numGun = new int[5];
			for(int i=0;i<line.length;i++){
				if(grade[i].equals("A"))
					numGun[0]++;
				else if(grade[i].equals("B"))
					numGun[1]++;
				else if(grade[i].equals("C"))
					numGun[2]++;
				else if(grade[i].equals("D"))
					numGun[3]++;
				else
					numGun[4]++;
			}
			
			///////////output stremaing//////////
			pw.println("///////////problem (a)/////////////");
			for(int i=0;i<line.length;i++){
				String[] word = line[i].split(":");
				pw.println(word[0]+"\t"+word[1]+"\t"+total[i]+"\t"+grade[i]);
			}
			pw.println("///////////problem (b)/////////////");
			pw.println("\t\t"+"highest"+"\t"+"lowest"+"\t"+"average");
			pw.println("project1"+"\t"+project1[0]+"\t"+project1[1]+"\t"+project1[2]);
			pw.println("project2"+"\t"+project2[0]+"\t"+project2[1]+"\t"+project2[2]);
			pw.println("project3"+"\t"+project3[0]+"\t"+project3[1]+"\t"+project3[2]);
			pw.println("midterm"+"\t\t"+midterm[0]+"\t"+midterm[1]+"\t"+midterm[2]);
			pw.println("final"+"\t\t"+finalexam[0]+"\t"+finalexam[1]+"\t"+finalexam[2]);
			pw.println("total"+"\t\t"+totalscore[0]+"\t"+totalscore[1]+"\t"+totalscore[2]);
		
			pw.println("///////////problem (c)/////////////");
			pw.println("A : "+numGun[0]);
			pw.println("B : "+numGun[1]);
			pw.println("C : "+numGun[2]);
			pw.println("D : "+numGun[3]);
			pw.println("F : "+numGun[4]);
		}
		catch (Exception e)
		{
			System.out.println("File input/output error!"+e);
		}
		finally
		{
			try
			{
				inputStream.close();
				pw.close();
			}
			catch(Exception e)
			{
				System.out.println("File closing error!"+e);
			}
		}
	}
}