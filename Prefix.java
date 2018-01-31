
//Name: Surbhi Goel
//USC loginid:surbhigo
//CSCI 455 PA4
//Fall 2016
import java.util.ArrayList;

public class Prefix 
{
	public ArrayList<String> al;
	static final int MULT= 31;

	Prefix(Prefix p)
	{
		al=(ArrayList) p.al.clone();
	}

	Prefix(int n, String str)
	{
		al = new ArrayList();
		for (int i = 0; i < n; i++)
		{
			al.add(str);
		}
	}
	public int hashCode()
	{
		int h = 0;
		for (int i = 0; i < al.size(); i++)
		{
			h = MULT * h + al.get(i).hashCode();
		}
			return h;
	}

	public boolean equals(Object obj)
	{
		Prefix pref = (Prefix) obj;
		for (int i = 0; i < al.size(); i++)
		{
			if (!al.get(i).equals(pref.al.get(i)))

				return false;
		}
		return true;
	}
} 
