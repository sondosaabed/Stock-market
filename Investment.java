import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Investment {
	private int sharesNum;
	private Company company= new Company();
	Calendar date;
	
	public Investment() {
		//non argument constructor
	}
	
	public Investment(String investment) {
		//constructor
		//Number of shares, Price per share, Company, Date 
		//130, 30, X, 1/3/2021  
		
		int comma1 = investment.indexOf(",",0);
		int comma3 = investment.lastIndexOf(","); 
		
		//30 ,X this substring to get the second comma
		String s= investment.substring(comma1+1,comma3);
		int comma2=s.lastIndexOf(","); 
		
		double price =Double.parseDouble(s.substring(0,comma2).trim());
		sharesNum = Integer.parseInt(investment.substring(0,comma1));
		
		company.setPrice(price); 
		company.setName(s.substring(comma2+1).trim()); 
		String date=investment.substring(comma3+1).trim();
		
		//1/3/2021   
		setDate(Integer.parseInt(date.substring(date.lastIndexOf("/")+1)),Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/"))),Integer.parseInt(date.substring(0,date.indexOf("/"))));
	 
	}

	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("dd MM yyyy");
		return "Investment [sharesNum=" + sharesNum + ", company=" + company + ", Invest Date=" + format.format(date.getTime())  + "]";
	}
	
	//Getters and Setters
	public int getSharesNum() {
		return sharesNum;
	} 
	
	public void setSharesNum(int sharesNum) {
		this.sharesNum = sharesNum;
	} 
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Calendar getDate() {
		return date;
	}
	public void setDate(int year, int month, int date) {
		this.date = Calendar.getInstance();
		this.date.set(year, month-1, date); 
	}
}
