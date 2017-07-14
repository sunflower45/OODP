import java.util.*;
public class NodeIterator implements Iterator{
	public Vector<Node> item;
	public int count = 0;
	
	public NodeIterator(Vector<Node> item){
		this.item = item;
	}
	public Object next(){
		Node n = new Node();
		n = item.get(count);
		count++;
		return n;
	}
	public boolean hasNext(){
		if(count >= item.size() || item.isEmpty()==true){
			return false;
		} else {
			return true;
		}
	}
}
