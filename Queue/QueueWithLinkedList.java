import java.util.LinkedList;

public class QueueWithLinkedList<T> {
    private final LinkedList<T> list;

    public QueueWithLinkedList() {
        list = new LinkedList<T>();
    }

    public void enqueue(T element) {
        list.addLast(element);
    }

    public void dequeue() {
        list.removeFirst();
    }

    public T peek() {
        return list.peek();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
