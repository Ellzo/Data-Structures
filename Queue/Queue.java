public class Queue{
	private int[] data;
	private int front = -1; //Also called as head
	private int rear = -1; //Also called as tail
	
	Queue(int capacity){
		data = new int[capacity];
	}
	
	void enqueue(int element){
		if(isFull()){
			System.out.println("Capacity Exceeded!");
			return;
		}else if(isEmpty()){
			front = 0;
			rear = 0;
		}else{
			rear = (rear + 1) % data.length;
		}
		
		data[rear] = element;
		System.out.println(element + " added!");
	}
	
	void dequeue(){
		if(isEmpty()){
			System.out.println("Empty!");
		}else{
			System.out.println(data[front] + " deleted!");
			if(front == rear){
				front = -1;
				rear = -1;
			}else{
				front = (front + 1) % data.length;
			}
		}
	}
	
	void printPeek(){
		if(isEmpty()){
			System.out.println("Empty!");
		}else{
			System.out.println("Peek is: " + data[front]);
		}
	}
	
	boolean isEmpty(){
		return rear == -1 && front == -1;
	}
	
	boolean isFull(){
		return (rear + 1) % data.length == front; 
	}
}
