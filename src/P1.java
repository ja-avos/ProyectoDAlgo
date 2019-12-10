import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
  
public class P1 
{ 
	/*
	 * Clase adaptada de GeeksForGeeks - https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
	 */
    static class Reader
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        }
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret;
        }
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    }
    
    public static class Comparators {

        public static Comparator<int[]> COMPARE = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
            	for(int i = 0; i < o1.length && i< o2.length; i++)
            	{
            		if(o1[i] > o2[i])
            			return 1;
            		else if(o1[i]<o2[i])
            			return -1;
            	}
            	return o1.length - o2.length;
            }
        };
    }
    
    public static int countEquals(int[] array1, int[] array2)
    {
    	int c = 0;
    	for(int i = 0; i < array1.length && i < array2.length; i++)
    	{
    		if(array1[i] == array2[i])
    			c++;
    		else
    			break;
    	}
    	return c;
    }
      
    public static void main(String[] args) throws IOException 
    { 
        Reader s=new Reader();
        int n = s.nextInt();
        while(n != 0)
        {
        	//Crea variables necesarias
        	int maxSR = 0;
        	ArrayList<int[]> subarrays = new ArrayList<>();
        	int[] array = new int[n];
        	
        	//Agrega valores al arreglo principal e inicializa subarreglos.
        	while(n-- > 0)
        	{
        		int tmp = s.nextInt();
        		array[array.length-(n+1)] = tmp;
        		subarrays.add(new int[n+1]);
        	}
        	
        	//Ingresa la informacion de los subarreglos
        	for(int j =0; j < subarrays.size(); j++)
        	{
        		int[] sub = subarrays.get(j);
        		for(int i = 0; i < sub.length; i++)
        			sub[i] = array[j+i];
        	}
        	
        	//Organiza los subarreglos de menor a mayor lexicograficamente
        	Collections.sort(subarrays, Comparators.COMPARE);
        	
        	//Compara cual es el subarreglo mas grande
        	for(int i = 0; i < subarrays.size() - 1; i++)
        	{
        		int[] sba = subarrays.get(i);
        		int[] nxt = subarrays.get(i+1);
        		int c = countEquals(sba, nxt);
        		maxSR = c > maxSR ? c : maxSR;
        	}
        	
        	//Imprime la respuesta
        	System.out.println(maxSR);
        	
        	//Lee siguiente valor
        	n = s.nextInt();
        }
    }
}