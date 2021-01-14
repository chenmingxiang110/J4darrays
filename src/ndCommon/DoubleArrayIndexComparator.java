package ndCommon;

import java.util.Comparator;

public class DoubleArrayIndexComparator implements Comparator<Integer>
{
    private final double[] array;
    private final boolean reverse;

    public DoubleArrayIndexComparator(double[] array, boolean reverse)
    {
        this.reverse = reverse;
        this.array = array;
    }

    public DoubleArrayIndexComparator(double[] array)
    {
        this.reverse = false;
        this.array = array;
    }

    public Integer[] createIndexArray()
    {
        Integer[] indexes = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            indexes[i] = i; // Autoboxing
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2)
    {
        if (array[index1]==(array[index2])) {
            return 0;
        } else if (array[index1]<(array[index2])) {
            return this.reverse?1:-1;
        } else {
            return this.reverse?-1:1;
        }
    }
}
