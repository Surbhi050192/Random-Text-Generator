//Name: Surbhi Goel
//USC loginid:surbhigo
//CSCI 455 PA4
//Fall 2016
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class RandomTextGenerator 
{
	int count,count_with_space,flag_first_word;
	static int PREFIX_LEN ; 
	static final String SPACE = " ";
	HashMap hm ;
	Prefix prefix;
	Random rand; 
	int flagdebug;


	public RandomTextGenerator(int pre,int flagdebug) 
	{
		PREFIX_LEN=pre;
		hm = new HashMap();
		prefix=new Prefix(PREFIX_LEN, SPACE);
		this.flagdebug=flagdebug;
		if(flagdebug==1)
			rand= new Random(1);
		else
			rand= new Random();

	}
	void build_Tokens(BufferedReader in) throws IOException
	{
		String s;
		StringTokenizer st;
		while((s = in.readLine())!=null)
		{
				st = new StringTokenizer(s, " ");
				while (st.hasMoreTokens())
				addWord(st.nextToken());
		}
			addWord(SPACE);
	} 

	void addWord(String word)
	{

		ArrayList<String> suf = (ArrayList) hm.get(prefix);
		if (suf == null) {
			suf = new ArrayList<String>();
			hm.put(new Prefix(prefix), suf);
		}


		suf.add(word);

		prefix.al.remove(0);

		prefix.al.add(word);

	}

	void generate_Random(int numWords, BufferedWriter out) throws IOException
	{	
		prefix = new Prefix(PREFIX_LEN, SPACE);


		//start collecting value from beginning
		for (int i = 0; i < numWords; i++) 
		{
			ArrayList<String> suf = (ArrayList) hm.get(prefix);
			int r = Math.abs(rand.nextInt()) % suf.size();

			String s = (String) suf.get(r);

			if (s.equals(SPACE))
			{
				if (flagdebug==1)
				{
					System.out.println("DEBUG: prefix: " + prefix.al.get(0) +" " + prefix.al.get(1));
					System.out.println("DEBUG: successors: <END OF FILE>"); 

				}
				break;
			}


			{
				if(count==0)
					flag_first_word=1;

				for(int j = 0;j<s.length();j++)
				{
					count++;

				}
				count++;//extra addition to count for space/
				//will not count the final space after the last word since break in above if condition

				if(count>81)//+1 than original count since we had count extra for space above

				{
					out.newLine();
					count = s.length()+1;
					out.write(s);

				}
				else
				{
					if(flag_first_word==1)
					{
						out.write(s);
						flag_first_word=0;
					}
					else
						out.write(" "+s);
				}
				if((i==2) && (flagdebug==1))
				{	
					System.out.println("DEBUG: chose a new initial prefix: " + prefix.al.get(0) +" " + prefix.al.get(1));
				}
				if((i>=2)  && (flagdebug==1))
				{
					System.out.println("DEBUG: prefix: " + prefix.al.get(0) +" " + prefix.al.get(1));
					System.out.print("DEBUG: successors: ");
					for(int j=0;j<suf.size();j++)
						System.out.print(suf.get(j) + " ");
					System.out.println();
					System.out.println("DEBUG: word generated: " +  s);
				}

			}//end of for

			//System.out.print(s+ " ");
			//System.out.println(count);

			prefix.al.remove(0);

			prefix.al.add(s);
		}

	}
} 