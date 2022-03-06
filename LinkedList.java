
public class LinkedList { 
    public NodeLinkedList firstNode = null;  
    public NodeLinkedList LastNode = null;  
    public int size=0;
    
	public LinkedList() {
		//non arguments constructor 
	}
	
	public boolean isEmpty() {
		//this method is to know whether the linked list is empty or not
		if(firstNode==null) {
			return true;
		}
		else 
			return false;
	} 
	
	public void insertLast(Company data) {  
		//this method is to add to the linked list at last if needed
		NodeLinkedList newNode = new NodeLinkedList(data);
		if(this.isEmpty()) { 
			firstNode = newNode;  
			LastNode = firstNode;
			size++;
		}
		else {  
			LastNode.next = newNode;  
			LastNode = newNode;
			size++;
		}
	} 
	
	public NodeLinkedList search(String name) {
	//this method is created to search for student with specific seat number
		NodeLinkedList current = firstNode;
		if(this.isEmpty()) {
			//is Empty case
    		System.out.println("this is an empty linked list");
    	}
		while (current != null) {
			if (current.getData().getName().equalsIgnoreCase(name)) 
				//traverse for the same seat number
				return current;
			current = current.getNext(); 
		}
		return current; 
	}
	
    public void deleteStudent(String name) { 
    //this method is created to delete a student with specific seat number	
    	NodeLinkedList current = firstNode;
    	
    	if(search(name)==null) {
    		//search method includes this is an empty linked list case
    		System.out.println("there is no student with this seat number!");
    	} 
    	else if(firstNode.data.getName() == name) {
    		  firstNode = firstNode.next;
    		  size--;
    	} 
    	while (current!= null) {
    		if (current.next.data.getName() == name) {
    		      current.next = current.next.next; 
    		      size--;
    		}
    		current = current.next;
    	} 
    }
    
    public void traverse() {   
    //this method is created to traverse the linked list and print it 
        NodeLinkedList current = firstNode;  
        if(this.isEmpty()) {  
            System.out.println("Your LinkedList is empty!");   
        }  
        while(current!= null) {   
            System.out.print(current.data.toString() + "\n");  
            current = current.next;  
        }     
    } 
    
    //getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public NodeLinkedList getFirstNode() {
		return firstNode;
	}
	public void setFirstNode(NodeLinkedList firstNode) {
		this.firstNode = firstNode;
	}
	public NodeLinkedList getLastNode() {
		return LastNode;
	}
	public void setLastNode(NodeLinkedList lastNode) {
		LastNode = lastNode;
	} 	 
}
