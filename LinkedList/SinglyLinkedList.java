public class SinglyLinkedList<T> {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
        head = null;
    }

    public SinglyLinkedList(int size) throws Exception {
        if (size > 0) {
            this.size = size;
            Node current = new Node(null);
            for (int i = size - 1; i > 0; i--) {
                current = new Node(null, current);
            }
            head = current;
        } else {
            throw new Exception("Size must be a strictly positive integer!");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 || head == null;
    }

    public void pushFront(T data) {
        if (head == null) {
            head = new Node(data);
        } else {
            head = new Node(data, head);
        }
        size++;
    }

    public T topFront() throws Exception {
        if (head == null || size == 0)
            throw new Exception("List is empty!");
        return head.getData();
    }

    public void popFront() throws Exception {
        if (head == null || size == 0)
            throw new Exception("List is empty!");

        if (head.getNextNode() == null) {
            head = null;
        } else {
            head = head.getNextNode();
        }
        size--;
    }

    public void pushBack(T data) {
        if (head == null || size == 0) {
            head = new Node(data);
        } else {
            Node current = head;
            while (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            current.setNextNode(new Node(data));
        }
        size++;
    }

    public T topBack() throws Exception {
        if (head == null || size == 0)
            throw new Exception("List is empty!");

        Node current = head;
        while (current.getNextNode() != null) {
            current = current.getNextNode();
        }

        return current.getData();
    }

    public void popBack() throws Exception {
        if (head == null || size == 0)
            throw new Exception("List is empty!");

        if (size == 1)
            head = null;
        else {
            Node current = head;
            while (current.getNextNode().getNextNode() != null) {
                current = current.getNextNode();
            }
            current.clearNextNode();
        }
        size--;
    }

    public int getIndex(T data) {
        if (size == 0 || head == null) {
            return -1;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getData().equals(data))
                return i;
            current = current.getNextNode();
        }
        return -1;
    }

    public boolean remove(T data) {
        if (size == 0 || head == null || data == null) {
            return false;
        }

        if (head.getData() != null && head.getData().equals(data)) {
            if (size == 1 || head.getNextNode() == null) {
                clear();
            } else {
                head = head.getNextNode();
                size--;
            }
            return true;
        }

        Node current = head;

        while (current.getNextNode() != null) {
            if (current.getData() != null && current.getNextNode().getData().equals(data)) {
                current.setNextNode(current.getNextNode().getNextNode());
                size--;
                return true;
            }
            current = current.getNextNode();
        }

        return false;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    private class Node {
        private T data;
        private Node nextNode;

        Node(T data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        Node(T data) {
            this.data = data;
            this.nextNode = null;
        }

        T getData() {
            return data;
        }

        void setData(T newData) {
            this.data = newData;
        }

        Node getNextNode() {
            return nextNode;
        }

        void setNextNode(Node newNextNode) {
            this.nextNode = newNextNode;
        }

        void clearNextNode() {
            this.nextNode = null;
        }
    }
}
