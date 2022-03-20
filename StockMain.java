import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Scanner; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StockMain extends Application{ 
	public static ToggleGroup group = new ToggleGroup();
	public static RadioButton rbOF = new RadioButton("Old shares first"); 
	public static RadioButton rbNF = new RadioButton("New shares first");
	public static FileChooser fileChooser = new FileChooser();
	public static File selectedFile;
	public static File selectedFile1; 
	private static StackList sharesStack = new StackList();
	private static QueueList sharesQueue = new QueueList(); 
	private static StackList tempS = new StackList();
	private static QueueList tempQ = new QueueList(); 
	private static LinkedList prices = new LinkedList(); 
	public static Calendar date = Calendar.getInstance();
	public static DateFormat format = new SimpleDateFormat("dd MMM yyyy");
	public static DateFormat format2 = new SimpleDateFormat("dd MM yyyy");
	public static NodeLinkedList current;
	public static Node currentt;
	public static double totalGain=0;
	public static double profitt=0;
	public static double losss=0;
	
	@Override
	public void start(Stage stage) throws Exception {
		  
		//Background and gridPane
		BackgroundFill fill = new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(fill); 
		BackgroundFill fill2 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background2 = new Background(fill2); 
		GridPane pane = new GridPane();  
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(150,280,150,280));
		pane.setHgap(19);
		pane.setVgap(19); 
		pane.setBackground(background);
		
		//add title label
		Label title = new Label("Stock Share");
		title.setTextFill(Color.BROWN);
		title.setFont(Font.font(20));  
		pane.add(title, 0, 0);
		
		//add date label
	    Label dat = new Label(format.format(date.getTime())); 
	    GridPane.setHalignment(dat, HPos.RIGHT);
	    dat.setTextFill(Color.BLUEVIOLET);
	    dat.setFont(Font.font(14));  
	    pane.add(dat, 2, 1);
				
		//add label to select
  	  	Label select0 =new Label("Please select daily prices file:");
  	  	select0.setFont(Font.font(14));
		pane.add(select0, 0, 1); 
		
		//add button to browse file
		Button bt0 = new Button("Browse");
		bt0.setTextFill(Color.BROWN);
		bt0.setFont(Font.font(14));
		GridPane.setHalignment(bt0, HPos.LEFT);
		pane.add(bt0, 1, 1); 
		
		//add label to select
  	  	Label select01 =new Label("Please select your Stoks file:");
  	  	select01.setFont(Font.font(14));
		pane.add(select01, 0, 2); 
		
		//add button to browse file
		Button bt01 = new Button("Browse");
		bt01.setTextFill(Color.BROWN);
		bt01.setFont(Font.font(14));
		GridPane.setHalignment(bt01, HPos.LEFT);
		pane.add(bt01, 1, 2); 		
		
		//add label to select
  	  	Label select1 =new Label("Select accounting principle:");
  	  	select1.setFont(Font.font(14));
		pane.add(select1, 0, 3); 
		
		//add radio button to pane
		rbOF.setTextFill(Color.BLACK); 
		rbOF.setFont(Font.font(14)); 
		pane.add(rbOF,1,4);
		rbNF.setTextFill(Color.BLACK);
		rbNF.setFont(Font.font(14));
		pane.add(rbNF,2,4);
		
		//set rb in toggle group
		rbNF.setToggleGroup(group);
		rbOF.setToggleGroup(group); 
		
		//Creating process label so the user get the operation outputs and update 
		Label process= new Label(("\nThis is process Label\n will change by process\n"));
		process.setTextFill(Color.BLUEVIOLET);
		process.setFont(Font.font(14));
		GridPane.setHalignment(process, HPos.CENTER);
		pane.add(process,1,5);
		
		//Creating process label so the user get the profit update 
		Label profit= new Label(("\nYour profit from\n this transaction is:\n"+"          "+profitt));
		profit.setTextFill(Color.RED); 
		 pane.add(profit,0,5);
				
		 //Creating process label so the user get the operation outputs and update 
		 Label loss= new Label(("\nYour loss from\n this transaction is:\n"+"          "+losss));
		 loss.setTextFill(Color.RED); 
		  GridPane.setHalignment(loss, HPos.RIGHT);
		 pane.add(loss,2,5);
		 
		//creating chose label
  	  	Label chose=new Label("Choose from following:");
  	  	chose.setFont(Font.font(14));
		pane.add(chose, 0, 6);
		
		//add 6 buttons for operations list
		Button bt1 = new Button("Show daily prices");
		bt1.setTextFill(Color.BROWN);
		bt1.setFont(Font.font(14));
		
		Button bt2 = new Button("Buy new shares");
		bt2.setTextFill(Color.BROWN);
		bt2.setFont(Font.font(14));
		
		Button bt3 = new Button("Your captial gain");
		GridPane.setHalignment(bt3, HPos.CENTER);
		bt3.setTextFill(Color.BROWN);
		bt3.setFont(Font.font(14));    
		
		Button bt4 = new Button("Show your shares");
		bt4.setTextFill(Color.BROWN); 
		bt4.setFont(Font.font(14));  
		
		Button bt5 = new Button("Sell your shares");
		bt5.setTextFill(Color.BROWN);
		bt5.setFont(Font.font(14));  
		
		Button bt6 = new Button("Save today's shares");
		bt6.setTextFill(Color.BROWN);
		bt6.setFont(Font.font(14));
		
		Button btExit = new Button("Exit");
	 	GridPane.setHalignment(btExit, HPos.RIGHT);
	 	btExit.setBackground(background2);
		btExit.setTextFill(Color.RED); 
		
		pane.add(bt1, 0, 7); 
		pane.add(bt2, 1, 7);
		pane.add(bt3, 2, 7);
		pane.add(bt4, 0, 8);
		pane.add(bt5, 1, 8);
		pane.add(bt6, 2, 8);
		pane.add(btExit, 2, 0);
		
						/** Create and register the Events handlers**/
		//button 0 handler
		bt0.setOnAction(e -> {   
            selectedFile = fileChooser.showOpenDialog(stage); 
            if(selectedFile == null) {
            	process.setText("You have not selected\n a file, Please do");
            }
            else {
            	readFileCompany(selectedFile);
            	process.setText("You have selected\n"+selectedFile.getName()+" as prices");
            } 
        });
		
		//button 01 handler
		bt01.setOnAction(e -> {  
			selectedFile1 = fileChooser.showOpenDialog(stage); 
		 	if(selectedFile1 == null) {
		    	process.setText("You have not selected\n a file, Please do");
		 	}else {
		     	readFileShares(selectedFile1);
		      	process.setText("You have selected\n"+selectedFile1.getName()+" as your file"); 
			}           
		});
				
		//button 1 handler
		bt1.setOnAction(e -> { 
				//create button for next investment
				Button next = new Button("Next");
				next.setFont(Font.font(14));
				next.setTextFill(Color.BLUEVIOLET);
				GridPane.setHalignment(next, HPos.CENTER);
				pane.add(next,2,11); 
				
				//create investment label 
				current =prices.getFirstNode(); 
				try {
				Label investment= new Label(current.data.toString()); 
				investment.setFont(Font.font(14));
				pane.add(investment,1,11);
				 
				
				//create button to cancel
				Button cancel = new Button("Cancel");
				cancel.setFont(Font.font(14));
				cancel.setTextFill(Color.RED);
				GridPane.setHalignment(cancel, HPos.CENTER);
				pane.add(cancel,1,12);
				
				//cancel handler
				cancel.setOnAction(u -> {
						pane.getChildren().remove(cancel); 
						pane.getChildren().remove(next);
						pane.getChildren().remove(investment); 
				});
				
				//next handler
				next.setOnAction(c -> { 
				try {	
					if(current!=null) {
						current= current.next;
						investment.setText(current.data.toString());  
					}  
				} catch(NullPointerException x) {
					investment.setText("        Last company!");
					System.out.println(x+"last Node");
				}
				});  
				current= prices.firstNode;
				} catch(NullPointerException x) { 
					System.out.println(x);
					process.setText("Your daily price\nfile is empty");
				} 
		});
		
		//button 2 handler
		bt2.setOnAction(e -> {  
		    	  
		    	  //Create number of shares label
		    	  Label number= new Label(("       Number of shares to buy:"));
		    	  number.setFont(Font.font(14));
		    	  pane.add(number,0,11); 
		    	  
		    	  //Create company name label
		    	  Label company= new Label(("       The name of the company:"));
		    	  company.setFont(Font.font(14));
		    	  pane.add(company, 0, 12); 
		    	  
		    	  //Create number of shares TextField
		    	  TextField number1 = new TextField();
		    	  pane.add(number1, 1, 11); 
		    	  
		    	  //Create company name comboBox
		    	  ComboBox<String> comboBox = new ComboBox<String>();
		    	  NodeLinkedList curr= prices.firstNode;
		    	  while(curr!=null) {
		    		  comboBox.getItems().add(curr.data.getName());
		    		  curr=curr.next;
		    	  } 
		    	  pane.add(comboBox, 1, 12);  

		    	  //create button to buy shares
		    	  Button btBuy = new Button("Buy shares");
		    	  btBuy.setTextFill(Color.RED);
		    	  btBuy.setFont(Font.font(14));
		    	  GridPane.setHalignment(btBuy, HPos.CENTER);
		    	  pane.add(btBuy, 0, 14); 
		    	  
		    	  //create button to cancel 
		    	  Button btCancel = new Button("Cancel");
		    	  btCancel.setTextFill(Color.RED);
		    	  btCancel.setFont(Font.font(14));
		    	  pane.add(btCancel, 1, 14);
 
				  //Create and register the handler of cancel button
		    	  btCancel.setOnAction(k -> { 
				    	  //update process
				    	  pane.getChildren().remove(number);
				    	  pane.getChildren().remove(company);
				    	  pane.getChildren().remove(number1);
				    	  pane.getChildren().remove(comboBox);
				    	  pane.getChildren().remove(btBuy);
				    	  pane.getChildren().remove(btCancel); 
				    });  
		    	  
		    	//Create and register the handler of add button
		    	  btBuy.setOnAction(t -> { 
				    	  Investment investment= new Investment(); 
				    	  //handling Number Format Exception
				    	  try {
				    		  
				    		  //read user entries
				    		  investment.setSharesNum(Integer.parseInt(number1.getText().trim())); 
				    		
				    		  String name =comboBox.getSelectionModel().getSelectedItem();
				    		
				    		  //X ,30
				    		  investment.setCompany(new Company(name+" ,"+prices.search(name).data.getPrice()));  
					    		  String datt= "" +format2.format(date.getTime());
					    		 
					    		  //10 12 2021
					    		  int splash =datt.indexOf(" ");
					    		  int splash2 =datt.lastIndexOf(" ");
					    		  //year month date
					    		  investment.setDate(Integer.parseInt(datt.substring(splash2+1)),Integer.parseInt(datt.substring(splash+1, splash2)),Integer.parseInt(datt.substring(0, splash).trim()));
					    		  if(group.getSelectedToggle().equals(rbOF) ) {
					    			  sharesQueue.enQueue(investment); 
					    			  sharesStack.push(investment); 
					    			  sharesQueue.traverse(); 
					    		  }
					    		  else if(group.getSelectedToggle().equals(rbNF)) {
					    			  sharesStack.push(investment);
					    			  sharesQueue.enQueue(investment); 
					    		  	  sharesStack.traverse(); 
					    		  }
					    		  
				    	  	}catch(NumberFormatException x) {
				    		  process.setText("You must enter a number!!"); 
				    		  System.out.println(x);
				    	  	 } catch (NullPointerException x) {
				    	  		process.setText("You must choose accounting principle!!"); 
				    	  		System.out.println(x);
				    	  	 } 
				    	  //update progress label
				    	  process.setText("\n You have bought "+ investment.getSharesNum() +"\n shares from comany "+ investment.getCompany().getName());
				    	  
				    	  //remove after use
				    	  pane.getChildren().remove(number);
				    	  pane.getChildren().remove(company);
				    	  pane.getChildren().remove(number1);
				    	  pane.getChildren().remove(comboBox);
				    	  pane.getChildren().remove(btBuy);
				    	  pane.getChildren().remove(btCancel); 
			      }); 
		    });
		
		//button 3 handler
		bt3.setOnAction(e -> {
				process.setText("You're capital gain\nfor this session is "+totalGain );   
        });
		
		//button 4 handler
		bt4.setOnAction(e -> { 
			//create button for next investment
			Button next = new Button("Next");
			next.setFont(Font.font(14));
			next.setTextFill(Color.BLUEVIOLET);
			GridPane.setHalignment(next, HPos.CENTER);
			pane.add(next,2,11); 
			
			//create button to cancel
			Button cancel = new Button("Cancel");
			cancel.setFont(Font.font(14));
			cancel.setTextFill(Color.RED);
			GridPane.setHalignment(cancel, HPos.CENTER);
			pane.add(cancel,1,12);
			
			//create investment label
			try {
			if(group.getSelectedToggle().equals(rbOF))
				currentt =sharesQueue.front;
			else if(group.getSelectedToggle().equals(rbOF))
				currentt =sharesStack.getHead(); 
			Label investment= new Label(currentt.data.getCompany()+">"+currentt.data.getSharesNum()+" shares");
			investment.setFont(Font.font(14));
			pane.add(investment,1,11);
			
			
			   
			
			//cancel handler
			cancel.setOnAction(d -> { 
					pane.getChildren().remove(cancel); 
					pane.getChildren().remove(investment); 
					pane.getChildren().remove(next);
			});
			
			//next handler
			next.setOnAction(z ->  {    
				try {  
					 if(currentt!=null) { 
						 currentt=currentt.next;
						 investment.setText(currentt.data.getCompany()+">"+currentt.data.getSharesNum()+" shares");
						
					 } 
				} catch(NullPointerException x) {
					 investment.setText("       Last stock!");
							System.out.println(x+"last Node");
						}
					 
			});}catch(NullPointerException x) {
				process.setText("Please chose accounting\nprinciple");
				System.out.println(x);
			}  
		});
 
		//button 5 handler
		bt5.setOnAction(e -> {  
			//Create seat label
			Label number= new Label(("       Number of shares to sell:"));
			number.setFont(Font.font(14));
			pane.add(number,0,10); 
		    	  
			//Create company label
			Label company= new Label(("       The name of the company:"));
			company.setFont(Font.font(14));
			pane.add(company, 0, 11); 
		    	  
			//Create seat TextField
			TextField number1 = new TextField();
		 	pane.add(number1, 1, 10); 
		    	  
		 	//Create company name comboBox
			ComboBox<String> comboBox = new ComboBox<String>();
			NodeLinkedList curr= prices.firstNode;
			while(curr!=null) {
		    	comboBox.getItems().add(curr.data.getName());
		    	curr=curr.next;
		 	} 
			pane.add(comboBox, 1, 11);  

		 	//create button to add student
		  	Button btSell = new Button("Sell shares");
		   	btSell.setTextFill(Color.RED);
		  	btSell.setFont(Font.font(14));
		 	pane.add(btSell, 0, 13);
		 	GridPane.setHalignment(btSell, HPos.CENTER);
		    	  
		 	 //create button to cancel 
		 	Button btCancel = new Button("Cancel");
			btCancel.setTextFill(Color.RED);
			btCancel.setFont(Font.font(14));
		 	pane.add(btCancel, 1, 13);
 
			 //Create and register the handler of cancel button
		 	btCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override // Override the handle method
			public void handle(ActionEvent ev) {   
				//update process
				pane.getChildren().remove(number);
				pane.getChildren().remove(company);
				pane.getChildren().remove(number1);
				pane.getChildren().remove(comboBox);
				pane.getChildren().remove(btSell);
				pane.getChildren().remove(btCancel);
			}
			}); 
		    	  
		 	// Create and register the handler of add button
		 	btSell.setOnAction(new EventHandler<ActionEvent>() {
			@Override // Override the handle method
			public void handle(ActionEvent ev) {   
				//handling Number Format Exception
 				try {
 			    		//read user entries
 				    	int number = Integer.parseInt(number1.getText().trim());
				    	String name =comboBox.getSelectionModel().getSelectedItem().trim(); 
				    	  
 				   		if(group.getSelectedToggle().equals(rbOF)) {  
 				    		  //Case old first queue
 				    		  sellShareQ(name,number); 
 				    	}   
 					    else if(group.getSelectedToggle().equals(rbNF)) { 
 					    	//Case new first stack
				    	    sellShareS(name,number);
 					    } 
 			    	}catch(NumberFormatException x) {
 				    	process.setText("You must enter a number!!");
 				    	System.out.println(x);
 				  	}catch(NullPointerException x) {
 						process.setText("\nMust chose a principle to operate\n");
 						System.out.println(x+"\nMust chose a principle to operate\n");
 					} 
					 //update progress label
					process.setText("\n "+  " Sold!\n");
				    	  
				 	//remove after use
					pane.getChildren().remove(number);
				 	pane.getChildren().remove(company);
				 	pane.getChildren().remove(number1);
				 	pane.getChildren().remove(comboBox);
					pane.getChildren().remove(btSell);
					pane.getChildren().remove(btCancel);
					}
				});  
		    });
		
		//button 6 handler
		bt6.setOnAction(e ->  {  
			reSave();
			if(reSave()==0||reSave()==-1){
				process.setText("No chosen file! or\nacounting principle!");
			}
			else if(reSave()==1) {
				process.setText("Your file has\nbeen updated!"); 
			}  
        });
		
		//button Exit handler 
		btExit.setOnAction(e -> Platform.exit()); 
		
		//Set scene
		Scene scen= new Scene(pane);
		stage.setScene(scen);
		
		//set title
		stage.setTitle("Stock Share");
		stage.show();
	}
	
	public static void main(String[] args) {  
		launch(args);  
	} 

	//This method is created by me to read the shrae files  
	public static void readFileShares(File selectedFile) {
		try {
			 Scanner scan = new Scanner( new FileInputStream(selectedFile));
 		     while (scan.hasNextLine()){ 
				Investment investment = new Investment(scan.nextLine());
			    sharesStack.push(investment);
			    sharesQueue.enQueue(investment);  
			 } 
			 scan.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace(); 
		}    
	}  
	
	//This method is created by me to read the companies files
	public static void readFileCompany(File selectedFile) { 
		try {
			 Scanner scan = new Scanner( new FileInputStream(selectedFile)); 
			while (scan.hasNextLine()){ 
				Company company = new Company(scan.nextLine());
				 prices.insertLast(company); 
			 }
			 scan.close();  
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (Exception e) {
			 e.printStackTrace(); 
		 }  
	}  
	
	//This method is created by me to sell shares from first, sell Shares Queue
	public static void sellShareQ(String name, int number) {
		Node curr =sharesQueue.front; 
		Node curr1 =tempQ.front; 
		double newPrice=prices.search(name).data.getPrice();
		double pastPrice = 0; 
		String datt= "" +format2.format(date.getTime()); 
		//10 12 2021
		int splash =datt.indexOf(" ");
		int splash2 =datt.lastIndexOf(" ");

		if(sharesQueue.isEmpty()) {
			System.out.println("Empty Queue"); 
		}
		
		while(curr!=null) {
			if(curr.data.getCompany().getName().equalsIgnoreCase(name)) { 
				if(curr.data.getSharesNum()>number) {
					//x 30 , want to sell 10
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number); 
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					curr.data.setSharesNum(curr.data.getSharesNum()-number); 	
					curr.data.setDate(Integer.parseInt(datt.substring(splash2+1)),Integer.parseInt(datt.substring(splash+1, splash2)),Integer.parseInt(datt.substring(0, splash).trim()));
					break;
				}
				else if(curr.data.getSharesNum()<number) {
					//x 30, want to sell 40
					//rest 10     
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number);  
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					//tempQ.enQueue(curr.data);
					sharesQueue.deQueue();  
					sellShareQ(name,number -curr.data.getSharesNum());  
					break;
				}else if(curr.data.getSharesNum()==number){
					//case equal shares number of current data
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number); 
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					sharesQueue.deQueue();
					break;
				} 
			}else { 
				tempQ.enQueue(curr.data);
				sharesQueue.deQueue();   
				curr=curr.next; 
 			}  
		} 
		while(curr1!=null) {
			sharesQueue.enQueue(curr1.data); 
			curr1=curr1.next;
			tempQ.deQueue();  
		} 
		sharesQueue.traverse();
	}
	
	//Sell Shares Stack
	public static void sellShareS(String name, int number) { 
		//This method is created by me to sell shares from stack
		Node curr =sharesStack.getHead(); 
		Node curr1 =tempS.getHead(); 
		double newPrice=prices.search(name).data.getPrice();
		double pastPrice = 0; 
		String datt= "" +format2.format(date.getTime()); 
		//10 12 2021
		int splash =datt.indexOf(" ");
		int splash2 =datt.lastIndexOf(" ");

		if(sharesStack.isEmpty()) {
			System.out.println("Empty Stack"); 
		}
		
		while(curr!=null) {
			if(curr.data.getCompany().getName().equalsIgnoreCase(name)) { 
				if(curr.data.getSharesNum()>number) {
					//x 30 , want to sell 10
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number); 
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					curr.data.setSharesNum(curr.data.getSharesNum()-number); 	
					curr.data.setDate(Integer.parseInt(datt.substring(splash2+1)),Integer.parseInt(datt.substring(splash+1, splash2)),Integer.parseInt(datt.substring(0, splash).trim()));
					break;
				}
				else if(curr.data.getSharesNum()<number) {
					//x 30, want to sell 40
					//rest 10     
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number);  
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					//tempQ.enQueue(curr.data);
					sharesStack.pop(); 
					sellShareS(name,number -curr.data.getSharesNum());  
					break;
				}else if(curr.data.getSharesNum()==number){
					//case equal shares number of current data
					pastPrice=curr.getData().getCompany().getPrice();
					totalGain=totalGain+((newPrice-pastPrice)*number); 
					if(newPrice>pastPrice) {
						profitt=0;
						losss=0;
						profitt=profitt+(newPrice-pastPrice)*number;
					} else if(newPrice<pastPrice ) {
						losss=0;
						profitt=0;
						losss=(newPrice-pastPrice)*number;
					}else {
						losss=0;
						profitt=0;
					}
					sharesStack.pop(); 
					break;
				} 
			}else { 
				tempQ.enQueue(curr.data);
				sharesStack.pop();   
				curr=curr.next; 
 			}  
		} 
		while(curr1!=null) {
			sharesStack.push(curr1.data); 
			curr1=curr1.next;
			tempS.pop(); 
		} 
		sharesStack.traverse();
	} 
	
	//This method is created by me to save user process in her/his file as queue or stack
	public static int reSave() { 
		PrintWriter out;
		try {
			out = new PrintWriter(selectedFile1);
			Node current1 = null; 
			if(group.getSelectedToggle().equals(rbOF))
				current1= sharesStack.getHead();
			else if (group.getSelectedToggle().equals(rbNF))
				current1= sharesQueue.front;
			
			while(current1!=null) {
				  //130, 30, X, 1/3/2021
				  out.print(current1.getData().getSharesNum()+", "+current1.getData().getCompany().getPrice()+", "
				  +current1.getData().getCompany().getName()+", "+ format2.format(current1.getData().getDate().getTime()) +"\n"); 
				  current1= current1.next;
			  }
			out.close();
			return 1;
		} catch (FileNotFoundException e) { 
			System.out.println(e); 
			return  0;
		} 
		catch (NullPointerException e) { 
			System.out.println(e); 
			return -1;
		} 
	}  
}
