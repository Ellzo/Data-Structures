public class DoublyLinkedList<T> {
    private Node head;
    private int size;
    private Node tail;

    public DoublyLinkedList() {
        this.size = 0;
        head = null;
        tail = null;
    }

    public DoublyLinkedList(int size) throws Exception {
        if (size > 1) {
            this.size = size;
            Node current0 = new Node(null);
            tail = current0;
            Node current1 = new Node(null, current0);
            for (int i = 1; i < size - 1; i++) {
                current0.previousNode = current1;
                current0 = new Node(null, current1);
                current1 = current0;
                current0 = current1.getNextNode();
            }
            head = current1;
        } else if (size == 1) {
            head = new Node(null);
            tail = head;
        } else {
            throw new Exception("Size must be a strictly positive integer!");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0 || head == null || tail == null;
    }

    public void pushFront(T data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
        } else {
            head = new Node(data, head);
            head.getNextNode().setPreviousNode(head);
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
            tail = null;
        } else {
            head = head.getNextNode();
            head.clearPreviousNode();
        }
        size--;
    }

    public void pushBack(T data) {
        if (tail == null) {
            tail = new Node(data);
            head = tail;
        } else {
            tail = new Node(tail, data);
            tail.getPreviousNode().setNextNode(tail);
        }
        size++;
    }

    public T topBack() throws Exception {
        if (tail == null || size == 0)
            throw new Exception("List is empty!");

        return tail.getData();
    }

    public void popBack() throws Exception {
        if (tail == null || size == 0)
            throw new Exception("List is empty!");

        if (tail.getPreviousNode() == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPreviousNode();
            tail.clearNextNode();
        }
        size--;
    }

    public void addAt(int index, T data) throws Exception {
        if (index >= size || index < 0)
            throw new Exception(index + " cannot be accessed for size " + size);

        if (index == 0) {
            pushFront(data);
            return;
        }

        if (index == size - 1) {
            pushBack(data);
            return;
        }

        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 1; i < index; i++) {
                current = current.getNextNode();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPreviousNode();
            }
        }
        current = new Node(current, data, current.getNextNode());
        current.getNextNode().setPreviousNode(current);
        current.getPreviousNode().setNextNode(current);
    }

    public void setAt(int index, T newData) throws Exception {
        if (index >= size || index < 0)
            throw new Exception(index + " cannot be accessed for size " + size);

        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNextNode();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPreviousNode();
            }
        }
        current.setData(newData);
    }

    public void removeAt(int index) throws Exception {
        if (index >= size || index < 0)
            throw new Exception(index + " cannot be accessed for size " + size);

        if (index == 0) {
            popFront();
            return;
        }

        if (index == size - 1) {
            popBack();
            return;
        }

        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNextNode();
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPreviousNode();
            }
        }
        current.getPreviousNode().setNextNode(current.getNextNode());
        current.getNextNode().setPreviousNode(current.getPreviousNode());
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
        if (size == 0 || head == null || tail == null || data == null) {
            return false;
        }

        if (head.getData() != null && head.getData().equals(data)) {
            if (size == 1 || head.getNextNode() == null) {
                clear();
            } else {
                try {
                    popFront();
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }

        if (tail.getData() != null && tail.getData().equals(data)) {
            if (size == 1 || head.getNextNode() == null) {
                clear();
            } else {
                try {
                    popBack();
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }

        Node current = head;

        while (current.getNextNode() != null) {
            if (current.getData() != null && current.getNextNode().getData().equals(data)) {
                current.setNextNode(current.getNextNode().getNextNode());
                current.getNextNode().setNextNode(current);
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
        tail = null;
    }

    private class Node {
        private Node previousNode;
        private T data;
        private Node nextNode;

        Node(T data) {
            this.previousNode = null;
            this.data = data;
            this.nextNode = null;
        }

        Node(Node previousNode, T data) {
            this.previousNode = previousNode;
            this.data = data;
            this.nextNode = null;
        }

        Node(Node previousNode, T data, Node nextNode) {
            this.previousNode = previousNode;
            this.data = data;
            this.nextNode = nextNode;
        }

        Node(T data, Node nextNode) {
            this.previousNode = null;
            this.data = data;
            this.nextNode = nextNode;
        }

        Node getPreviousNode() {
            return previousNode;
        }

        void setPreviousNode(Node newPreviousNode) {
            this.previousNode = newPreviousNode;
        }

        void clearPreviousNode() {
            this.previousNode = null;
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
