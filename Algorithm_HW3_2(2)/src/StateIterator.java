import java.util.Vector;
public class StateIterator implements Iterator{
	public Vector<State> item;
	public int count = 0;
	
	public StateIterator(Vector<State> item){
		this.item = item;
	}
	public Object next(){
		State n = new State();
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
