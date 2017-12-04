import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PizzaApplication 
{
  private static final String USER_FILE = "users.txt";
  private static final String[] STATES = {"DC", "MD", "VA"};
  //Customer is account 1, Employee is 2, Manager is 3, and Driver is 4
  private static final String[] ACCOUNT_TYPES = {"Customer", "Employee","Manager", "Driver"};
  
  public static void main(String[] args) throws IOException
  {
    //Create a File object
    File userFile = new File(PizzaApplication.USER_FILE);
    //Make sure it exists
    userFile.createNewFile();
    
    //start the first menu
    initialMenu(userFile);
    
  }
  
  private static void initialMenu(File userFile) throws FileNotFoundException{
    String menu = "1- Login\n2- Create New Account\n3- Exit";
    int option = 0;
    option = Integer.parseInt(JOptionPane.showInputDialog(menu));
    switch(option){
      case 1:
        displayLogin(userFile);
        break;
      case 2:
        promptForUserEntry(userFile);
        break;
      case 3:
        JOptionPane.showMessageDialog(null, "Have a Nice Day!");
        System.exit(0);
    }
  }
  
  private static void displayLogin(File userFile) throws FileNotFoundException
  {
    boolean validEmail = false;
    int authenticatedType = 0;
    String email = null;
    
    JPanel panel = new JPanel();
    JLabel usernameLabel = new JLabel("Email: ");
    JLabel passwordLabel = new JLabel("Password:");
    String password=null;
    JTextField emailInput = new JTextField(25);
    JPasswordField pass = new JPasswordField(25);
    panel.add(usernameLabel);
    panel.add(emailInput);
    panel.add(passwordLabel);
    panel.add(pass);
    String[] options = new String[]{"OK", "Cancel"};
    int option = JOptionPane.showOptionDialog(null, panel, "Login",
                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                              null, options, options[1]);
    if(option == 0) // pressing OK button
    {
      email = emailInput.getText();
      password = charsToString(pass.getPassword());
      authenticatedType = authenticateUser(userFile, email, password);
    }
    switch(authenticatedType){
      case 0:
        JOptionPane.showMessageDialog(null, "Invalid Credentials \n Please Try Again!");
        displayLogin(userFile);
      case 1:
        displayCustomerMenu();
      case 2:
        displayEmployeeMenu();
      case 3:
        displayManagerMenu(userFile);
      case 4:
        displayDriverMenu();
    }
  }
  
  private static String charsToString(char[] chars){
    String out = "";
    for(int i=0; i<chars.length; i++){
      out += chars[i];
    }
    return out;
  }
  
  
  private static int authenticateUser(File userFile, String email, String password) throws FileNotFoundException
  {
    int authenticatedUserType = 0;
    String[] userLine = new String[10];
    
    Scanner scan = new Scanner(new FileInputStream(userFile));
    
    while(scan.hasNextLine())
    {
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      int i = 0;
      while (linescan.hasNext()){
        userLine[i] = linescan.next();
        i++;
      }
      if((email.equals(userLine[8])) && (password.equals(userLine[9]))){
        authenticatedUserType = Integer.parseInt(userLine[0]);
      }
    }
    return authenticatedUserType;
  }
  
  private static void displayCustomerMenu()
  {
    String menu ="1- New Order\n2- Check My Orders\n3- Recent Orders\n4- My Profile\n5- Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          //TODO
          break;
        case 2:
          //TODO
          break;
        case 3:
          //TODO
          break;
        case 4:
          //TODO
          break;
        case 5:
          //TODO
          JOptionPane.showMessageDialog(null, "Logging you out...");
          System.exit(0);
      }
    }while(true);
  }
  
  private static void displayEmployeeMenu()
  {
    //Employee should be an abstract class. Will implement and remove
  }
  
  private static void displayDriverMenu()
  {
    String menu ="1- New Order\n2- Deliveries\n3- My Delivery History\n4- Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          //TODO
          break;
        case 2:
          //TODO
          break;
        case 3:
          //TODO
          break;
        case 4:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          System.exit(0);
          
      }
    }while(true);
  }
  
  private static void displayManagerMenu(File userFile) throws FileNotFoundException
  {
    String menu ="1- New Order\n2- Outstanding Orders\n3- Order History\n4- Add User\n5- My Profile\n6-Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          //TODO
          break;
        case 2:
          //TODO
          break;
        case 3:
          //TODO
          break;
        case 4:
          promptForManagerEntry(userFile);
          break;                
        case 5:
          //TODO
          break;
        case 6:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          System.exit(0);
      }
    }while(true);
  }
  
  private static void promptForUserEntry(File userFile) throws FileNotFoundException
  {
    //Still need to implement validation
    String userInfo = "";
    String accountType = "1";
    String firstName = JOptionPane.showInputDialog(null,"First Name: ");
    String lastName = JOptionPane.showInputDialog(null,"Last Name: ");
    String phone = JOptionPane.showInputDialog(null,"Phone Number: ");
    String streetAddress = JOptionPane.showInputDialog(null,"Street Address: ");
    String city = JOptionPane.showInputDialog(null,"City: ");
    String state = (String) JOptionPane.showInputDialog(null, 
                                                        "State:",
                                                        "Input",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        PizzaApplication.STATES, 
                                                        PizzaApplication.STATES[2]);
    
    String zip = JOptionPane.showInputDialog(null,"Zip Code: ");
    String email = JOptionPane.showInputDialog(null,"Email Address: ");
    //String password = getUserPassword();
    String password = JOptionPane.showInputDialog(null, "Password:");
    
    
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\n";
    writeToFile(userFile, userInfo);
    
    displayLogin(userFile);
  }
  
  private static void promptForManagerEntry(File userFile) throws FileNotFoundException
  {
    //Still need to implement validation
    String userInfo = "";
    String accountType = JOptionPane.showInputDialog(null,"Account Type (1 = Customer, 2 = Employee, 3 = Manager, 4 = Driver): ");
    String firstName = JOptionPane.showInputDialog(null,"First Name: ");
    String lastName = JOptionPane.showInputDialog(null,"Last Name: ");
    String phone = JOptionPane.showInputDialog(null,"Phone Number: ");
    String streetAddress = JOptionPane.showInputDialog(null,"Street Address: ");
    String city = JOptionPane.showInputDialog(null,"City: ");
    String state = (String) JOptionPane.showInputDialog(null, 
                                                        "State:",
                                                        "Input",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        PizzaApplication.STATES, 
                                                        PizzaApplication.STATES[2]);
    
    String zip = JOptionPane.showInputDialog(null,"Zip Code: ");
    String email = JOptionPane.showInputDialog(null,"Email Address: ");
    //String password = getUserPassword();
    String password = JOptionPane.showInputDialog(null, "Password:");
    
    
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\n";
    writeToFile(userFile, userInfo);
    
    displayManagerMenu(userFile);
  }
  
  
  private static void writeToFile(File userFile, String userInfo)
  {
    PrintWriter writer = null; 
    try {
      writer = new PrintWriter(new FileOutputStream(userFile, true));
      writer.append(userInfo);
    } 
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    writer.close();
  }
  
}

