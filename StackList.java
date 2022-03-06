
public class StackList {
	private Node head;
	private int size;

	public StackList(){
		head=null;
	}

	public StackList(int size){
		this.size=size;
	} 
    
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){ 
		return head==null;
	}  
 
    //Insert at first
    public void push(Investment x){ 
        Node curr = new Node();  
        curr.setData(x);
        curr.setNext(head); 
        head =  curr;
        size++;
    } 
    
	//Delete at first
    public void pop(){
        if (isEmpty()) {
            System.out.print("\nEmpty stack");
            return;
        } 
        head = (head).next;
        size--;
    }  
  
    // return top element in the stack
    public Investment peek(){ 
        if (!isEmpty()) {
            return head.getData();
        }
        else {
            System.out.println("\nEmpty stack");
            return null;
        }
    } 
    
  //Search
  	public Node search(Investment investment) { 
  		Node curr = head;
  		while (curr != null) {
  			if (curr.getData() == investment)
  				return curr;
  			curr = curr.getNext(); 
  		} 
  		return curr; 
  	}
  	
    //To print the stack elements
    public void  traverse(){ 
        if (isEmpty()) {
            System.out.printf("Empty stack"); 
        }
        else {
            Node temp = head;
            while (temp != null) { 
                System.out.printf(" \n->"+temp.data.toString()); 
                temp = temp.getNext();
            }
        }
    }
    
	//Getters and setters
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
