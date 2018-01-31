//Name: Surbhi Goel
//USC loginid:surbhigo
//CSCI 455 PA4
//Fall 2016



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenText
{
	static int pre=2;
	static int numWords=10;
	static String inputfile="";
	static String outfile="";
	static int argcount=0;
	static int flagDebugMode=0;
	
	static void  commandArg(int value) throws MyOwnException{
	      if(value < 4)
	         throw new MyOwnException("missing command line argument");
	      
	   }
	static void exceptionHandling(String[] args){

        
        try {
        	commandArg(argcount);
        }
        catch(MyOwnException e)
	    {
	      System.out.println("missing command line argument");
	    } 
		 
		
		
                
        if(argcount==4)
        { 

            inputfile=args[2];
            outfile=args[3];
            
            
                try 
                {
                	 pre=Integer.parseInt(args[0]);
                	 numWords=Integer.parseInt(args[1]);
                } catch (NumberFormatException e) 
                {
                   
                	System.out.println("prefixLength or numWords arguments are not integers");
                    System.out.println(e.getMessage());
                }
            
        }
        if(argcount==5)
		{
        	 try 
             {
             	 pre=Integer.parseInt(args[1]);
             	 numWords=Integer.parseInt(args[2]);
             } catch (NumberFormatException e) 
             {
                
             	System.out.println("prefixLength or numWords arguments are not integers");
                 System.out.println(e.getMessage());
             }
        	 	
            inputfile=args[3];
            outfile=args[4];
            System.out.println("Debug Mode on");
            flagDebugMode=1;
		}
	
        try 
		{
			
			if (numWords < 0)
	         throw new ArithmeticException("numWords < 0");
		}
		catch(ArithmeticException e)
	    {
	      System.out.println("numWords < 0");
	    }
        
        try 
		{
			
			if (pre < 1)
	         throw new ArithmeticException("prefixLength < 1");
		}
		catch(ArithmeticException e)
	    {
	      System.out.println("prefixLength < 1");
	    }
	}
	
	
	public static void main(String[] args) 
	{
		try
		{
		
		//MyOwnException m;
            for (String arg : args) {
                argcount++;
                }
            
         exceptionHandling(args);
		File in = new File(inputfile);
		FileReader fileread = new FileReader(in);
		BufferedReader input = new BufferedReader(fileread);
		
		File out = new File(outfile);
		FileWriter filewrite = new FileWriter(out);
		BufferedWriter output = new BufferedWriter(filewrite);		

		
		RandomTextGenerator RandomText = new RandomTextGenerator(pre,flagDebugMode);
		
		
		RandomText.build_Tokens(input);
		input.close();
		
		RandomText.generate_Random(numWords, output);
		output.close();
		
		}
		  catch(FileNotFoundException e)
	    {
	      System.out.println("input file does not exist");
	    } 
		catch (IOException e) 
		{
			e.printStackTrace();
		}  
				
		
	}
} 