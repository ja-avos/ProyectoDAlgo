import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
  
public class P1 
{ 
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
//        	String l = "";
//        	for(int i = 0; i < 10000; i++)
//        		l += 1 + " ";
//        	return l;
        } 
  
        public int nextInt() throws IOException 
        { 
//            int ret = 0; 
//            byte c = read(); 
//            while (c <= ' ') 
//                c = read(); 
//            boolean neg = (c == '-'); 
//            if (neg) 
//                c = read(); 
//            do
//            { 
//                ret = ret * 10 + c - '0'; 
//            }  while ((c = read()) >= '0' && c <= '9'); 
//  
//            if (neg) 
//                return -ret; 
//            return ret;
        	return 1;
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
    
    public static int compararArray(int[] array1, int[] array2)
    {
    	for(int i = 0; i < array1.length && i< array2.length; i++)
    	{
    		if(array1[i] > array2[i])
    			return 1;
    		else if(array1[i]<array2[i])
    			return -1;
    	}
    	return array1.length - array2.length;
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
    
    public static int countEquals(String array1, String array2)
    {
    	int c = 0;
    	for(int i = 0; i < array1.length() && i < array2.length(); i++)
    	{
    		if(array1.charAt(i) == array2.charAt(i))
    			c++;
    		else
    			break;
    	}
    	return c;
    }
    
    public static String[] mergeSort(String[] list) {
        String [] sorted = new String[list.length];
        if (list.length == 1) {
            sorted = list;
        } else {
            int mid = list.length/2;
            String[] left = null; 
            String[] right = null;
            if ((list.length % 2) == 0) {
                left = new String[list.length/2];
                right = new String[list.length/2];
            } else { 
                left = new String[list.length/2];
                right = new String[(list.length/2)+1];
            }
            int x=0;
            int y=0;
            for ( ; x < mid; x++) {
                left[x] = list[x];
            }
            for ( ; x < list.length; x++) {
                right[y++] = list[x];
            }
            left = mergeSort(left);
            right = mergeSort(right);
            sorted = mergeArray(left,right);
        }

        return sorted;
    }

    private static String[] mergeArray(String[] left, String[] right) {
        String[] merged = new String[left.length+right.length];
        int lIndex = 0;
        int rIndex = 0;
        int mIndex = 0;
        int comp = 0;
        while (lIndex < left.length || rIndex < right.length) {
            if (lIndex == left.length) {
                merged[mIndex++] = right[rIndex++];
            } else if (rIndex == right.length) {
                merged[mIndex++] = left[lIndex++];
            } else {  
                comp = left[lIndex].compareTo(right[rIndex]);
                if (comp > 0) {
                    merged[mIndex++] = right[rIndex++];
                } else if (comp < 0) {
                    merged[mIndex++] = left[lIndex++];
                } else { 
                    merged[mIndex++] = left[lIndex++];
                }
            }   
        }
        return merged;
    }
      
    public static void main(String[] args) throws IOException 
    { 
        Reader s=new Reader();
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = 0; //Integer.parseInt(bf.readLine());
        n = 50000;
        long time = Calendar.getInstance().getTimeInMillis();
        System.out.println(time);
        while(n != 0)
        {
        	int maxSR = 0;
        	String[] subarrays = new String[n];
        	int[] array = new int[n];
        	//String l = bf.readLine();
        	String l = "";
        	for(int i = 0; i < n; i ++)
        		l+= "1 ";
        	l = l.replace(" ", "");
        	//byte[] lb = l.getBytes();
        	//Agrega valores al arreglo principal y genera subarreglos.
        	while(n-- > 0)
        	{
        		int tmp = s.nextInt();
        		array[array.length-(n+1)] = tmp;
        		int pos = array.length-(n+1);
        		subarrays[pos] = new String(l.substring(pos));
        		System.out.println(pos);
        	}
        	
        	System.out.println(Calendar.getInstance().getTimeInMillis()-time);
        	
        	subarrays = mergeSort(subarrays);
        	
        	for(int i = 0; i < subarrays.length - 1; i++)
        	{
        		String sba = subarrays[i];
        		String nxt = subarrays[i+1];
        		int c = countEquals(sba, nxt);
        		maxSR = c > maxSR ? c : maxSR;
        	}
        	
        	//Imprime la respuesta
        	System.out.println(maxSR);
        	
        	//Lee siguiente valor
//        	n = s.nextInt();
        	n=0;
        }
        long fin = Calendar.getInstance().getTimeInMillis() - time;
        Date d = new Date(fin);
//        System.out.println(d.getDay() + " days "+ d.getHours() + " hours " + d.getMinutes() + " min " + d.getSeconds());
        System.out.println(fin);
    }
}