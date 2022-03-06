
public class QueueList {
	Node front = null;
	Node rear = null;
	int size;
	
	public QueueList() {
		
	} 
	
	public QueueList(int size){
		this.size=size;
	}
	
	public boolean isEmpty() {
		return rear == null; 
	}
	 
 	//Add first to the queue
 	public void enQueue(Investment investment) {
 		Node newNode = new Node(investment);
 		//Case is empty
 		if (front == null) {
 			front = rear =newNode; 
 		} else {
 			rear.setNext(newNode);
 			rear = newNode;
 			size++;
 		} 
 	}
	
	//Delete first
	public Node deQueue() {
		//is empty case
		if(isEmpty())
			return null;
		else if(front==rear) 
			front=rear=null; 
		else {
			front = front.getNext();
		}
			return front; 
	} 
	
	//Search
	public Node search(Investment investment) { 
		Node curr = front;
		while (curr != null) {
			if (curr.getData() == investment)
				return curr;
			curr = curr.getNext(); 
		} 
		return curr; 
	}
	
//	//Search
//		public QueueList searchName(String name) { 
//			QueueList sharesWNames= new QueueList();
//			Node curr = front;
//			
//			while (curr != null) {
//				if (curr.getData().getCompany().getName() == name)
//					sharesWNames.enQueue(curr.data); 
//				curr = curr.getNext(); 
//			} 
//			return sharesWNames; 
//		}
		
	//To print the stack elements
    public void  traverse(){ 
        if (isEmpty()) {
            System.out.printf("Empty Queue"); 
        }
        else {
            Node temp = front;
            while (temp != null) { 
                System.out.printf("\n->"+temp.data.toString()); 
                temp = temp.getNext();
            }
        }
    }
    
	//Getters and setters
	public Node getFirst() {
		return front;
	}

	public void setFirst(Node front) {
		this.front = front;
	}

	public Node getLast() {
		return rear;
	}

	public void setLast(Node rear) {
		this.rear = rear;
	} 
}
