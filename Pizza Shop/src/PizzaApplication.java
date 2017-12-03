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
        displayManagerMenu();
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
    //Authentication currently not working due to null values being read at the ends of each line in text file
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
      if((email == userLine[8]) && (password == userLine[9])){
        authenticatedUserType = Integer.parseInt(userLine[0]);
        break;
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
  
  private static void displayManagerMenu()
  {
    String menu ="1- New Order\n2- Outstanding Orders\n3- Order History\n4- Add Driver\n5- My Profile\n6-Logout";
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
          break;
        case 6:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          System.exit(0);
      }
    }while(true);
  }
  
//  private static boolean checkForUser(File userFile) throws FileNotFoundException, IOException
//  {
//    boolean userFound = false;
//    BufferedReader reader = new BufferedReader(new FileReader(userFile));  
//    
//    if (reader.readLine() != null)
//      return true;
//    
//    return userFound;       
//  }
  
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
    
    
    userInfo += accountType + ", " + firstName + ", " + lastName + ", " + streetAddress + ", " + city + ", " + state + ", " + zip + ", " + phone + ", " + email + ", " + password + "\n";
    writeToFile(userFile, userInfo);
    
    displayLogin(userFile);
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
  
  
  
  
//  private static String[] fillArray(String accountType, 
//                                    String firstName, String lastName, String streetAddress, 
//                                    String city, String state, String zip, String phone, String email, String password)
//  {
//    String[] userInfo = new String[9];
//    userInfo[0] = accountType;
//    userInfo[1] = firstName;
//    userInfo[2] = lastName;
//    userInfo[3] = streetAddress;
//    userInfo[4] = city;
//    userInfo[5] = state;
//    userInfo[6] = zip;
//    userInfo[7] = email;
//    userInfo[8] = password;
//    
//    return userInfo;
//  }
//  
  
  
//  private static String getUserPassword()
//  {
//    
//    JPanel panel = new JPanel();
//    JLabel label = new JLabel("Enter a password:");
//    char[] password=null;
//    JPasswordField pass = new JPasswordField(10);
//    panel.add(label);
//    panel.add(pass);
//    String[] options = new String[]{"OK", "Cancel"};
//    int option = JOptionPane.showOptionDialog(null, panel, "Enter Password",
//                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
//                                              null, options, options[1]);
//    if(option == 0) // pressing OK button
//    {
//      //Where is this saved?
//      password = pass.getPassword();
//      //System.out.println("Your password is: " + new String(password));
//    }
//    
//    return new String(password);
//  }
  
  
}

