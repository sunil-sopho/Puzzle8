import java.util.*;

public class Pqueue //extends AbstractCollection
{
	private ArrayList q;
	private int size;	
	
	public Pqueue()
	{
		q = new ArrayList(64);
		q.add(null);
		size = 0;
	}
	public boolean add(Object o)
	{
		q.add(o);
		size++;
		int loc = size;
		addjustment(loc,o);
		return true;
	}
	private void addjustment(int loc,Object o)
	{
		while(loc >1 && comp.compare(q.get(loc/2),o) > 0)
		{
			q.set(loc,q.get(loc/2));
			loc /=2;
		}
		q.set(loc,o);
	}
	public int size()
	{
		return size;
	}
	
	private static class Compar implements Comparator
	{
		public int compare(Object obj1,Object obj2)
		{
			return ((Comparable)obj2).compareTo(obj1);
		}

	}
	
	public Object remove(){
		if(!(size()==0))
		{
			Object handle = q.get(1);
			q.set(1,q.get(size));
			q.remove(size);
			size--;
			if(size > 1)
			{
				heapify(1);
			}
			return handle;
		}
		return null;
	}
	private Comparator comp = new Compar();
	private void heapify(int start)
	{
		Object obj = q.get(start);
		addjust(start,obj);
	}
	private void addjust(int k,Object obj)
	{
		int j;
		while(2*k <= size)
		{
			j = 2*k;
			if(j < size && comp.compare(q.get(j),q.get(j+1)) > 0)
				j++;
			if(comp.compare(obj,q.get(j)) <=0)
			{
				break;
			}
			else
			{
				q.set(k,q.get(j));
				k = j;
			}
		}
		q.set(k,obj);
	}		
}
