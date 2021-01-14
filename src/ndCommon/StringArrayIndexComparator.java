package ndCommon;

import java.util.Comparator;

public class StringArrayIndexComparator implements Comparator<Integer>
{
    private final String[] array;
    private final boolean reverse;

    public StringArrayIndexComparator(String[] array, boolean reverse)
    {
        this.reverse = reverse;
        this.array = array;
    }

    public StringArrayIndexComparator(String[] array)
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
        // Autounbox from Integer to int to use as array indexes
        if (this.reverse) {
            return -array[index1].compareTo(array[index2]);
        } else {
            return array[index1].compareTo(array[index2]);
        }
    }
}
