
public class Company {
	private String name;
	private double price;
	
	public Company() {
		
	}
	
	public Company(String company) {
		//X ,30
		int comma1 = company.indexOf(",",0);
		this.name= company.substring(0, comma1).trim();
		
		double price =Double.parseDouble(company.substring(comma1+1).trim()); 
		this.price= price;  
	} 

	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//to String 
	@Override
	public String toString() {
		return "[name=" + name + ", price=" + price + "]";
	} 
}
