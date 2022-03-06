
public class NodeLinkedList{  
   	Company data;  
    NodeLinkedList next;  

    public NodeLinkedList(){    
    	//non arguments constructor
    }
    
    public NodeLinkedList(Company data) {  
    	//Constructor
        this.data = data;  
        this.next = null;  
    }

    //Getters and setters
	public Company getData() {
		return data;
	}

	public void setData(Company data) {
		this.data = data;
	}

	public NodeLinkedList getNext() {
		return next;
	}

	public void setNext(NodeLinkedList next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "[data=" + data + ", next=" + next + "]";
	}  
	 
}
