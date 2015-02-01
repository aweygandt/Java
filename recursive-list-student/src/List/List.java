package List;

import structure.ListInterface;
import List.LLNode;

public class List<T> implements ListInterface<T>{
	
	private LLNode<T> lead = null;
	private LLNode<T> tail = lead;
	int size = 0;
	int containscounter = 1;
	
	public List() {
		lead = null;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub                   DONE
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// TODO Auto-generated method stub                   DONE
		if(elem == null){
			throw new NullPointerException("Null element");
		}
		if(lead == null){
			lead = new LLNode<T>(elem);
			tail = lead;
			size++;
		}
		else{
			LLNode<T> node = new LLNode<T>(elem);
			node.setLink(lead);
			lead = node;
			size++;
		}
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		// TODO Auto-generated method stub                 DONE
		if(elem == null){
			throw new NullPointerException("Null element");
		}
		if(lead == null){
			lead = new LLNode<T>(elem);
			tail = lead;
			size++;
		}
		else{
			LLNode<T> node = new LLNode<T>(elem);
			tail.setLink(node);
			tail = node;
			size++;
		}
		return this;
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Null element");
		}
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("Invalid number");
		LLNode<T> node = new LLNode<T>(elem);
		if(this.isEmpty() == true || index == 0){
			insertFirst(elem);
			return this;
		}
		
		else{
			if(index == size){
				insertLast(elem);
				return this;
			}
		LLNode<T> temp = getthere(index-1,lead);
		LLNode<T> temp2 = getthere(index,lead);
		temp.setLink(node);
		node.setLink(temp2);
		size++;
		return this;
		}
	}
	
	public LLNode<T> getthere(int where, LLNode<T> start){
		
		if(where == 0)
			return start;
		else
			where--;
			return getthere(where, start.getLink());
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub              DONE
		if(isEmpty() == true)
			throw new IllegalStateException("Empty list");
		LLNode<T> temp;
		if(lead == tail){
			temp = lead;
			lead = null;
			size--;
			return temp.getInfo();
		}
		temp = lead;
		lead = lead.getLink();
		size--;
		return temp.getInfo();
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub            DONE
		
		//DONT USE TEMPS IN RECURSION
		
		LLNode<T> temp = lead;
		return removeLastHelper(temp);
	}
	
	public T removeLastHelper(LLNode<T> node){
		if(isEmpty() == true)
			throw new IllegalStateException("Empty list");
		LLNode<T> temp;
		if(node == tail){
			temp = lead;
			lead = null;
			size--;
			return temp.getInfo();
		}
		if(node.getLink() == tail){
			temp = node.getLink();
			tail = node;
			node.setLink(null);
			size--;
			return temp.getInfo();
		}
		node = node.getLink();
		return removeLastHelper(node);
	}

	@Override
	public T removeAt(int i) {
		// TODO Auto-generated method stub
		if(i < 0 || i >= size)
			throw new IndexOutOfBoundsException("Invalid number");
		LLNode<T> temp = getthere(i-1,lead);
		//T info = temp.getLink().getInfo();
		if(i == size-1){
			T info = tail.getInfo();
			temp.setLink(null);
			size--;
			return info;
		}
		if(i == 0){
			T yep = lead.getInfo();
			if(size == 1){
				lead = null;
				size--;
				return yep;
				
			}
			else{
				lead = lead.getLink();
				size--;
				return yep;
			}
		}
		else{
			T info = temp.getLink().getInfo();
			temp.setLink(temp.getLink().getLink());
			size--;
			return info;
		}
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub              DONE
		if(isEmpty() == true)
			throw new IllegalStateException("Empty list");
		return lead.getInfo();
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub              DONE
		if(isEmpty() == true)
			throw new IllegalStateException("Empty list");
		return tail.getInfo();
	}

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if(i < 0 || i >= size)
			throw new IndexOutOfBoundsException("Invalid number");
		//LLNode<T> node = lead;
		if(i == 0){
			return lead.getInfo();
		}
		if(i == size-1){
			return tail.getInfo();
		}
		else
			return getthere(i,lead).getInfo();
	}

	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		if(isEmpty() == true)
			throw new IllegalStateException("Empty list");
		if(contains(elem) == -1){
			return false;
		}
		if(lead.getInfo() == elem){
			removeFirst();
			return true;
		}
		LLNode<T> temp = lead;
		if(removehelp(temp, elem) == true){
			return true;
		}
		else
			return false;
	}
	
	public boolean removehelp(LLNode<T> node, T info){
		if(node.getLink().getInfo() == info){
			node.setLink(node.getLink().getLink());
			size--;
			return true;
		}
		if(node.getLink() == null){
			return false;
		}
		return removehelp(node.getLink(),info);
	}
	
	public LLNode<T> nodebefore(LLNode<T> have, LLNode<T> removed){
		if(have.getLink() != removed){
			have = have.getLink();
			nodebefore(have, removed);
		}
		return have;
	}

	@Override
	public int contains(T elem) {
		// TODO Auto-generated method stub
		
		LLNode<T> container = lead;
		if(lead.getInfo() == elem)
			return 0;
		else
			containscounter = 0;
			return containshelp(container,elem);
	}
	
	public int containshelp(LLNode<T> node, T info){
		if(node == null){
			return -1;
		}
		if(node.getInfo() == info){
			return containscounter;
		}
		else{
			containscounter++;
			return containshelp(node.getLink(),info);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub             DONE
		if(lead == null)
			return true;
		else
			return false;
	}
}


