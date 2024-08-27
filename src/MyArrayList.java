import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<T extends Comparable>{

    private int indexOflastAddedElement;

    public Object[] array;

    public MyArrayList(Collection<T> collection) {
        array = new Object[collection.size()];
        Iterator iterator = collection.iterator();
        int i = 0;
        while(iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        indexOflastAddedElement = array.length - 1;
    }

    public static <E extends  Comparable> void sort (Collection<E> collection) {
        Iterator iterator = collection.iterator();
        Object[] objects = new Object[collection.size()];
        int i = 0;
        while (iterator.hasNext()) {
            objects[i] = iterator.next();
            i++;
        }
        for (int j = 0; j < objects.length; j++) {
            for (int k = 1; k < objects.length; k++) {
                E firstElement = (E) objects[k - 1];
                E secondElement = (E) objects[k];
                if (firstElement.compareTo(secondElement) > 0 ) {
                    objects[k - 1] = secondElement;
                    objects[k] = firstElement;
                }
            }
        }
        collection.clear();
        for (int j = 0; j < objects.length; j++) {
            collection.add((E) objects[j]);
        }
    }

    public void sort() {
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                T firstElement = (T) array[i - 1];
                T secondElement = (T) array[i];
                if (secondElement.compareTo(secondElement) < 0) {
                    array[i - 1] = secondElement;
                    array[i] = firstElement;
                } else {
                    k++;
                }
                if (k == array.length - 1) {
                    return;
                }
            }
        }
    }

    public void add(T element) {
        if (indexOflastAddedElement == array.length - 1) {
            resize();
        }
        array[indexOflastAddedElement+1] = element;
        indexOflastAddedElement = indexOflastAddedElement + 1;
    }

    public void add(int index, T element) {
        if (indexOflastAddedElement == array.length - 1) {
            resize();
        }

        for (int i = 0; i < array.length ; i++) {
            if (index == i + 1) {
                T firstSavedElement = (T) array[i];
                for (int j = i; j <= indexOflastAddedElement ; j++) {
                    T e = (T) array[j + 1];
                    array[j + 1] = firstSavedElement;
                    firstSavedElement = e;
                }
                array[i] = element;
                return;
            }
        }
    }


    public void remove(T element) {
        Object[] newArray = new Object[array.length - 1];
        for (int i = 0; i <  array.length; i++) {
            if (!array[i].equals(element)) {
                newArray[i] = array[i];
            }
        }
        array = newArray;
    }

    public T get(int index) {
        if (index <= array.length && index >= 0) {
            return (T) array[index - 1] ;
        }
        return null;
    }

    public void addAll(Collection collection) {
        Iterator iterator = collection.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            array[index] = iterator.next();
            index++;
        }
    }


    private void resize() {
        Object[] newArray = new Object[array.length*2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

}
