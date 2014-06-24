import java.io.*;
import java.util.*;

public class Info
{
    private int max, min, size;

    private LinkedHashMap<Integer, Integer> month;

    public Info()
    {
	max = min = size = 0;
	month = new LinkedHashMap<Integer, Integer>();
    }

    public void init(int m, int v)
    {
	this.size++;
	this.max = this.min = v;
	this.month.put(m, v);
    }
    
    public void search(int m, int v)
    {
	//System.out.println("AAA");
	if(!this.month.containsKey(m)) this.size++;
	this.month.put(m, v);
	if(this.max < v) this.max = v;
	if(this.min > v) this.min = v;
    }

    public void setMax(int max)
    {
	this.max = max;
    }

    public void setMin(int min)
    {
	this.min = min;
    }

    public void setSize(int size)
    {
	this.size = size;
    }

    public int getMax()
    {
	return max;
    }

    public int getMin()
    {
	return min;
    }

    public int getSize()
    {
	return size;
    }

}
