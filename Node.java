
public class Node{  
    Investment data;  
    Node next;  

    public Node(){    
    	//non arguments constructor
    }
    
    public Node(Investment data) {  
    	//Constructor
        this.data = data;  
        this.next = null;  
    }

    //Getters and setters
	public Investment getData() {
		return data;
	}

	public void setData(Investment data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "[data=" + data + ", next=" + next + "]";
	}  
	 
}
