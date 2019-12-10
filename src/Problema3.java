import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Juan Rubio y Juan Andrés Avelino
 *
 */
public class Problema3 {

	static class Punto
	{
		int x,y;
		public Punto(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while(true)
			{
				String[] data = br.readLine().split(" ");
				int M = Integer.parseInt(data[0]);
				int n = Integer.parseInt(data[1]);
				if(M+n==0)
					break;
				Punto p = new Punto(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
				String[] data1 = br.readLine().split(" ");
				Punto[] pun = new Punto[n];
				for(int i =0, j=0; i < 2*n;i=i+2, j++)
				{
					pun[j]= new Punto(Integer.parseInt(data1[i]),Integer.parseInt(data1[i+1]));
				}
				System.out.println(estaDentro(pun,n, p));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//TOMADO DE: GEeks for geeks https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/
	static boolean enBorde(Punto a, Punto b, Punto c)
	{

		if (b.x <= Math.max(a.x, c.x) && b.x >= Math.min(a.x, c.x) && b.y <= Math.max(a.y, c.y) && b.y >= Math.min(a.y, c.y)) 
		{ 
			return true; 
		} 
		return false; 	
	}
	//TOMADO DE: GEeks for geeks https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/

	static int orientacion(Punto p, Punto q, Punto r)  
	{ 
		long valor = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); 
		if (valor == 0)  
		{ 
			return 0; 
		} 
		return (valor > 0) ? 1 : 2;  
	} 
	//TOMADO DE: GEeks for geeks https://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/

	static boolean Interseca(Punto p1, Punto q1,  
			Punto p2, Punto q2)  
	{ 

		int o1 = orientacion(p1, q1, p2); 
		int o2 = orientacion(p1, q1, q2); 
		int o3 = orientacion(p2, q2, p1); 
		int o4 = orientacion(p2, q2, q1); 

		if (o1 != o2 && o3 != o4) 
		{ 
			return true; 
		} 
		else if (o1 == 0 && enBorde(p1, p2, q1))  
		{ 
			return true; 
		} 
		else if (o2 == 0 && enBorde(p1, q2, q1))  
		{ 
			return true; 
		} 
		else if (o3 == 0 && enBorde(p2, p1, q2)) 
		{ 
			return true; 
		} 
		else if (o4 == 0 && enBorde(p2, q1, q2)) 
		{ 
			return true; 
		} 

		return false;  
	}
	
    static int estaDentro(Punto poligono[], int n, Punto p) 
    { 
  
        Punto extremo = new Punto(infinito, p.y); 
  
        int cuenta = 0, i = 0; 
        do 
        { 
           int sig = (i + 1) % n; 
 
            if (Interseca(poligono[i], poligono[sig], p, extremo))  
            { 
            	if (orientacion(poligono[i], p, poligono[sig]) == 0) 
                { 
                    return enBorde(poligono[i], p, poligono[sig])?0:-1; 
                } 
                cuenta++; 
            } 
            i = sig; 
        } while (i != 0); 
  
        return (cuenta % 2 == 1)?1:-1; 
    } 
    static int infinito= 10000; 

}
