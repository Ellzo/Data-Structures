import java.util.EmptyStackException;

public class DynamicArray {

    private int[] arr;
    private int size = 0;

    public DynamicArray() {
        arr = new int[2];
    }

    public DynamicArray(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }

        arr = new int[initCapacity];
    }

    public void set(int index, int data) {
        if (index < size && index >= 0) {
            arr[index] = data;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int get(int index) {
        if (index < size && index >= 0) {
            return arr[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void pushFront(int data) {
        if (size == arr.length) {
            int[] temp = new int[arr.length * 2];

            System.arraycopy(arr, 0, temp, 1, arr.length);

            arr = temp;
        } else if (size > 0) {
            for (int i = size - 1; i >= 0; i--) {
                arr[i + 1] = arr[i];
            }
        }

        arr[0] = data;
        size++;
    }

    public void popFront() {
        if (!isEmpty()) {
            if ((size - 1) * 2 == arr.length) {
                int[] temp = new int[arr.length * 2];

                System.arraycopy(arr, 1, temp, 0, size - 1);

                arr = temp;
            } else {
                for (int i = 1; i < size; i++) {
                    arr[i - 1] = arr[i];
                }
            }

            size--;
        } else {
            throw new EmptyStackException();
        }
    }

    public void pushBack(int data) {
        if (size == arr.length) {
            int[] temp = new int[arr.length * 2];

            System.arraycopy(arr, 0, temp, 0, arr.length);

            arr = temp;
        }

        arr[size++] = data;
    }

    public void popBack() {
        if (!isEmpty()) {
            size--;

            if (size * 2 == arr.length) {
                int[] temp = new int[arr.length * 2];

                System.arraycopy(arr, 0, temp, 0, size);

                arr = temp;
            }
        } else {
            throw new EmptyStackException();
        }
    }

    public void addAt(int index, int data) {
        if (index == 0) {
            pushFront(data);
        } else if (index == size) {
            pushBack(data);
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if (size == arr.length) {
                int[] temp = new int[arr.length * 2];

                for (int i = 0; i < index; i++) {
                    temp[i] = arr[i];
                }

                for (int i = size - 1; i >= index; i--) {
                    temp[i + 1] = arr[i];
                }

                arr = temp;
            } else {
                for (int i = size - 1; i >= index; i--) {
                    arr[i + 1] = arr[i];
                }
            }

            arr[index] = data;
            size++;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        arr = new int[2];
    }

}
