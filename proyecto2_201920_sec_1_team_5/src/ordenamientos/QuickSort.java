package ordenamientos;

public class QuickSort <T extends Comparable<T>>
{
	public QuickSort(T[] arreglo)
	{
		sort(arreglo, 0, arreglo.length -1);
	}

	private  void sort(T[] a, int lo, int hi)
	{  
		if (hi <= lo) 
			return;
		int lt = lo;
		int i = lo+1; 
		int gt = hi;
		T v = a[lo];
		while (i <= gt)
		{
			int cmp = a[i].compareTo(v);
			if      (cmp < 0) 
				exch(a, lt++, i++);
			else if (cmp > 0) 
				exch(a, i, gt--);
			else              
				i++;
		}  
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	private void exch(T[] arreglo, int i, int j)
	{
		T t = arreglo[i]; 
		arreglo[i] = arreglo[j];
		arreglo[j] = t;
	}
	
	public boolean isSorted(T[] arreglo)
	{
		for(int i = 1; i < arreglo.length; i++)
		{
			if(arreglo[i].compareTo(arreglo[i-1]) <0)
				return false;
		}
		return true;
	}
}
