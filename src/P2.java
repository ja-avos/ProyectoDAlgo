import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class P2 {
	
	 static class Ordenador implements Comparable<Ordenador>
		{
			int indice;
			int a;
			int b;
			@Override
			public int compareTo(Ordenador obj1) {
				// TODO Auto-generated method stub
				if ( a > obj1.a)
					return 1;
				else if (a < obj1.a)
					return -1;
				else if (a == obj1.a)
					return b > obj1.b ? -1: b< obj1.b ? 1: indice > obj1.indice?1:-1;
				return 0;
			}
			 Ordenador(int indice, int a, int b)
			{
				this.indice=indice;
				this.a=a;
				this.b=b;
			}
			
		}
	 static int techo(int A[], int l, int r, int key) 
	    { 
	        while (r - l > 1) { 
	            int m = l + (r - l) / 2; 
	            if (A[m] < key) 
	                r = m; 
	            else
	                l = m; 
	        } 
	  
	        return r; 
	    } 
	  
	    static void LongestIncreasingSubsequenceLength(ArrayList<Ordenador> ord) 
	    { 
	        // Add boundary case, when array size is one 
	  
	        int[] tailTable = new int[ord.size()]; 
	        int len; // always points empty slot 
	        
	        int[] arre = new int[ord.size()];
	  
	        tailTable[0] = ord.get(0).b; 
	        arre[0]=ord.get(0).indice;
	        len = 1; 
	        for (int i = 1; i < ord.size(); i++) { 
	            if (ord.get(i).b > tailTable[0]) 
	            {
	                tailTable[0] = ord.get(i).b; 
	               arre[0]=ord.get(i).indice;

	            }
	  
	            else if (ord.get(i).b < tailTable[len - 1]) 
	            {
	                tailTable[len++] = ord.get(i).b; 
		               arre[len-1]=ord.get(i).indice;

	            }
	  
	            else
	            {
	            	int techo =techo(tailTable, -1, len - 1, ord.get(i).b);
	            	if(arre[techo]<ord.get(i).indice)
	            		continue;
	                tailTable[techo] = ord.get(i).b; 
		            arre[techo]=ord.get(i).indice;


	            }
	        } 
	        if(len==1)
	        {
	        	System.out.println(0 + "\n");
	        }else
	        {
	        	System.out.println(len);
	        	String str = "";
	        	for(int j=0; j<len-1; j++)
	        	{
	        		str+= (arre[j] + " ");
	        	}
	        	str+=(arre[len-1] + "");
	        	System.out.println(str);
	        }
	    } 
	
	public static void main(String[] args) {
		
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			int n = Integer.parseInt(br.readLine());
			while(n!=0)
			{
				ArrayList<Ordenador> ord = new ArrayList<Ordenador>();
				//Mientras  que el número leído correspondiente al número de elementos en el array no sea 0...
				for(int i =0; i<n; ++i)
				{
					String[] data = br.readLine().split(" ");
					ord.add(new Ordenador(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
				}
				Collections.sort(ord);
	
				LongestIncreasingSubsequenceLength(ord);
				n = Integer.parseInt(br.readLine());
				if(n==0)
					break;
			}
		}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}

}
