class Stack{
	int[] data;
	int top = -1;
	Stack(int size){
		data = new int[size];
	}
	
	void push(int item){
		if(top < data.length - 1){
			data[++top] = item;
			System.out.println(item + " inseted!");
		}else{
			System.out.println("Stack Overflow!");
		}
	}
	
	void pop(){
		if(top <= -1){
			System.out.println("Stack Underflow!");
		}else{
			System.out.println(data[top] + " is deleted!");
			top--;
		}
	}
	
	boolean isEmpty(){
		return top < 0;
	}
}
