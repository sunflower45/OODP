import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class readFileApplet extends Applet {
   String fileToRead = "test1.txt";
   StringBuffer strBuff;
   TextArea txtArea;
   Graphics g;
   
   public void init() {
      txtArea = new TextArea(100, 100);
      txtArea.setEditable(false);
      add(txtArea, "left");
      readFile();
   }
   public void readFile(){
      String line;
      try {
         URL url = new URL(getCodeBase(), fileToRead);
         InputStream in = url.openStream();
         BufferedReader bf = new BufferedReader(new InputStreamReader(in));
         strBuff = new StringBuffer();
         while((line = bf.readLine()) != null) {
            strBuff.append(line + "\n");
         }
         txtArea.append("File Name : " + fileToRead + "\n");
         txtArea.append(strBuff.toString());
         //bf.close();
      } catch(IOException err) {
         txtArea.append("Error");
      }
   }
}