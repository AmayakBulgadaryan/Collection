import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class MyLinkedList<E extends Comparable> {

    private Node firstNode;

    private Node lastNode;

    private static int length;

    public MyLinkedList(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            add((E) iterator.next());
        }
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
        Node node = firstNode;
        int k = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                E firstElement = node.element;
                Node nextNode = node.next;
                E secondElement = nextNode.element;
                if (secondElement.compareTo(secondElement) < 0) {
                    node.element = secondElement;
                    nextNode.element = firstElement;
                } else {
                    k++;
                }
                if (k == length - 1) {
                    return;
                }
                node = node.next;
            }
        }

    }

    public E get(int index) {
        if (index <= 0 || index > length) {
            return null;
        }

        Node node = firstNode;

        for (int i = 0; i < length ; i++) {
            if(index == i + 1) {
                return node.element;
            }
            node = node.next;
            i++;
        }

        return null;
    }

    public void remove(int index) {
        if (index <= 0 || index > length) {
            return;
        }
        Node node = firstNode;
        for (int i = 0; i < length ; i++) {
            if(index == i + 1) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node = null;
                return;
            }
            node = node.next;
            i++;
        }
    }

    public void add(E element) {
        if (length == 0) {
            Node newNode = new Node(element, null, null);
            firstNode = newNode;
            lastNode = newNode;
            length++;
        } else {
            Node newNode = new Node(element, null, lastNode);
            lastNode.next = newNode;
            lastNode = newNode;
            length++;
        }
    }

    public void add(int index, E element) {
        Object[] nodes = null;
        if (length != 0) {
            nodes = new Object[length];
            Node node = firstNode;
            for (int i = 0; i < length; i++) {
                nodes[i] = node;
                node = node.next;
            }
        }
        if (index <= 0 || index > length) {
            return;
        }
        for (int i = 0; i < length ; i++) {
            if (index == 1) {
                Node nextNode = (Node) nodes[0];
                Node newNode = new Node(element, nextNode, null);
                nextNode.prev = newNode;
                firstNode = newNode;
                length++;
                return;
            }
            if(index == i + 1) {
                Node nextNode = (Node) nodes[i];
                Node prevNode = (Node) nodes[i - 1];
                Node newNode = new Node(element, nextNode, prevNode);
                nextNode.prev = newNode;
                prevNode.next = newNode;
                length++;
                if (index == length) {
                    lastNode = newNode;
                }
                return;
            }
            i++;
        }
    }

    public void addAll(Collection collection) {
        Iterator iterator = collection.iterator();
        Object[] objects = new Object[collection.size()];
        int i = 0;
        while (iterator.hasNext()) {
            if (i == 0) {
                objects[i] = new Node((E)iterator.next(), null, null);
                firstNode = (Node)objects[i];
            } else {
                Node prevNode = (Node)objects[i - 1];
                objects[i] = new Node((E)iterator.next(),null, prevNode);
                prevNode.next = (Node)objects[i];
                if (i == collection.size() - 1) {
                    lastNode = (Node) objects[i];
                }
            }
            i++;
        }
    }

    class Node {

        E element;
        Node next;
        Node prev;

        Node(E element, Node next, Node prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
            length++;
        }
    }

}
