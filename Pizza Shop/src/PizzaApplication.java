import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * PizzaApplication is the main class of the program, it hodls the majority of the UI elements and controls the data flow.  
 * @author Sharif Elkasse, John Cognetti
 */
public class PizzaApplication 
{
  private static final String USER_FILE = "users.txt";
  private static final String[] STATES = {"DC", "MD", "VA"};
  //Customer is account 1, Employee is 2, Manager is 3, and Driver is 4
  private static final String[] ACCOUNT_TYPES = {"Customer", "Employee","Manager", "Driver"};
  private static String[] currentUser = new String[10]; 
  
  
  public static void main(String[] args) throws IOException, Exception
  {
    //Create a File object
    File userFile = new File(PizzaApplication.USER_FILE);
    //Make sure it exists
    userFile.createNewFile();
    
    //start the first menu
    initialMenu(userFile);
    
  }
  
  /**
   * The initial menu which asks the user to login, create a new account, or exit.
   * @param userFile the file that holds account information
   */
  private static void initialMenu(File userFile) throws FileNotFoundException, Exception
  {
      
      
     /*
      
      String[] options = new String[]{"OK", "Exit"};
     int option = JOptionPane.showOptionDialog(null, panel, "Login",
                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                              null, options, options[1]);
      */
      
        String[] options = {"Login", "Create New Account","Exit" };
        
        //String menu = "1- Login\n2- Create New Account\n3- Exit";
        //int option = 0;
        //option = Integer.parseInt(JOptionPane.showInputDialog(menu));
        
        int option = JOptionPane.showOptionDialog(null, null, "Login",
                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                              null, options, options[0]);
        System.out.println(option);
        if(option == 0)
        {
//            switch(option){
//            case 1:
//              displayLogin(userFile);
//              break;
//            case 2:
//              promptForUserEntry(userFile);
//              break;
//            case 3:
//              JOptionPane.showMessageDialog(null, "Have a Nice Day!");
//              System.exit(0);
//          } 
            displayLogin(userFile);
        }
        else if (option == 1)
        {
            promptForUserEntry(userFile);
        }
        else
        {
            System.exit(0);
        }
        
  }
  
  /**
   * The main login screen, asks for an email and a password before sending to a helper method to authenticate
   * @param userFile the file that contains all the account information
   */
  private static void displayLogin(File userFile) throws FileNotFoundException, Exception
  {
    boolean validEmail = false;
    int authenticatedType = 0;
    String email = null;
    //using Jpanels so that password is hidden
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
    String[] options = new String[]{"OK", "Exit"};
    int option = JOptionPane.showOptionDialog(null, panel, "Login",
                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                              null, options, options[0]);
    System.out.println(option);
    
    if(option == 0) // pressing OK button
    {
        email = emailInput.getText();
        password = charsToString(pass.getPassword());
        //authenticatedType is the type of account labled from 1-4
        authenticatedType = authenticateUser(userFile, email, password);

        switch(authenticatedType){
        case 0:
          //If it is default, something went wrong and they need to try again
          JOptionPane.showMessageDialog(null, "Invalid Credentials \n Please Try Again!");
          displayLogin(userFile);
        case 1:
          displayCustomerMenu(userFile);
        case 2:
          displayEmployeeMenu(userFile);
        case 3:
          displayManagerMenu(userFile);
        case 4:
          displayDriverMenu(userFile);
      }
    }
    else
    {
        System.exit(0);
    }
    
  }
  
  /**
   * helper method for the displayLogin, since a JPanel password is a char array
   * @param chars array to become a string
   * @return returns a string
   */
  private static String charsToString(char[] chars){
    String out = "";
    for(int i=0; i<chars.length; i++){
      out += chars[i];
    }
    return out;
  }
  
  /**
   * Main authentication method, scans the userFile and compares the username and password to the ones the user input
   * @param userFile the file that contains all account information
   * @param email the email set by the user
   * @param password the password set by the user
   * @return Integer value of the account type
   */
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
        currentUser[i] = userLine[i]; 
        i++;
      }
      //Authentication check
      if((email.equals(userLine[8])) && (password.equals(userLine[9]))){
        authenticatedUserType = Integer.parseInt(userLine[0]);
      }
    }
    return authenticatedUserType;
  }
  
  /**
   * The main customer menu, shown once they have authenticated and logged in
   * @param userFile holds all the account information
   */
  private static void displayCustomerMenu(File userFile) throws Exception
  {
    //The customer's menu
//       String[] options = {"New Order", "Check My Orders","Recent Orders", "My Profile", "Logout" };
//        
//       
//        
//        int option = JOptionPane.showOptionDialog(null, null, "Customer Menu",
//                                              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
//                                              null, options, options[0]);
//      
//      
//        switch(option)
//        {
//            case 0:
//              makeOrder(userFile);
//              break;
//            case 1:
//              listOrders();
//              //displayCustomerMenu(userFile);
//              break;
//            case 2:
//              listRecentOrder();
//              break;
//            case 3:
//              myProfile(userFile);
//              break;
//            case 4:
//              JOptionPane.showMessageDialog(null, "Logging you out...");
//              displayLogin(userFile);
//      }
        
        
        
        String menu ="1- New Order\n2- Check My Orders\n3- Recent Order\n4- My Profile\n5- Logout";
        int option = 0;
        do{
            option = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            switch(option)
            {
                case 1:
                  makeOrder(userFile);
                  break;
                case 2:
                  listOrders();
                  //displayCustomerMenu(userFile);
                  break;
                case 3:
                  listRecentOrder();
                  break;
                case 4:
                  myProfile(userFile);
                  break;
                case 5:
                  JOptionPane.showMessageDialog(null, "Logging you out...");
                  displayLogin(userFile);
            }
            
        }while(true);
  }
  
  /**
   * The main employee menu, very similar functionality to the customer
   * @param userFile file that holds account information
   */
  private static void displayEmployeeMenu(File userFile) throws Exception
  {
    System.out.println("Entering displayEmployeeMenu");
    String menu ="1- New Order\n2- Check My Orders\n3- Recent Order\n4- Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          makeOrder(userFile);
          break;
        case 2:
          listOrders();
          break;
        case 3:
          listRecentOrder();
          break;
        case 4:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
  /**
   * the main driver menu, drivers can make orders, deliver orders, and check old deliveries
   * @param userFile holds all user information
   */
  private static void displayDriverMenu(File userFile) throws Exception
  {
    String menu ="1- New Order\n2- Deliveries\n3- My Delivery History\n4- Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          makeOrder(userFile);
          break;
        case 2:
          listOutstandingOrders();
          break;
        case 3:
          listOutstandingOrders();
          break;
        case 4:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
  /**
   * Manager's main menu, managers have the ability to make orders, check outstanding orders, order history, and add new users
   * @param userFile holds user account information
   */
  private static void displayManagerMenu(File userFile) throws FileNotFoundException, Exception
  {
    String menu ="1- New Order\n2- Outstanding Orders\n3- Order History\n4- Add User\n5- My Profile\n6-Logout";
    int option = 0;
    do{
      option = Integer.parseInt(JOptionPane.showInputDialog(menu));
      switch(option){
        case 1:
          makeOrder(userFile);
          break;
        case 2:
          listOutstandingOrders();
          break;
        case 3:
          listAllOrders();
          break;
        case 4:
          promptForManagerEntry(userFile);
          break;                
        case 5:
          myProfile(userFile);
          break;
        case 6:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
  /**
   * Menu to create a new account, follows the same steps as stored in the file
   * @param userFile holds user account information
   */
  private static void promptForUserEntry(File userFile) throws FileNotFoundException, Exception
  {
    //Still need to implement validation
    String userInfo = "";
    //standard customer accounts is type 1
    String accountType = "1";
    String firstName = JOptionPane.showInputDialog(null,"First Name: ");
    String lastName = JOptionPane.showInputDialog(null,"Last Name: ");
    String phone = JOptionPane.showInputDialog(null,"Phone Number: ");
    String streetAddress = JOptionPane.showInputDialog(null,"Street Address: ");
    String city = JOptionPane.showInputDialog(null,"City: ");
    String state = (String) JOptionPane.showInputDialog(null, 
                                                        "State:",
                                                        "Create New User",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        PizzaApplication.STATES, 
                                                        PizzaApplication.STATES[2]);
    
    String zip = JOptionPane.showInputDialog(null,"Zip Code: ");
    String email = JOptionPane.showInputDialog(null,"Email Address: ");
    String password = JOptionPane.showInputDialog(null, "Password:");
    
    //Output string to be saved
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\r\n";
    writeToFile(userFile, userInfo);
    
    displayLogin(userFile);
  }
  
  /**
   * Slightly different from normal user entry, the manager can decide the type of account
   */
  private static void promptForManagerEntry(File userFile) throws FileNotFoundException, Exception
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
                                                        "State input for Manager",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        PizzaApplication.STATES, 
                                                        PizzaApplication.STATES[2]);
    
    String zip = JOptionPane.showInputDialog(null,"Zip Code: ");
    String email = JOptionPane.showInputDialog(null,"Email Address: ");
    String password = JOptionPane.showInputDialog(null, "Password:");
    
    //output string to be saved
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\r\n";
    writeToFile(userFile, userInfo);
    
    displayManagerMenu(userFile);
  }
  
  /**
   * Call to create an order, asks the user for number of pizzas and begind another dialog
   * @param userFile holds user account information
   */
  private static void makeOrder(File userFile) throws Exception
  {
    //create a while loop that continues to prompt ask the user if they would like to add another pizza to the order
    //each time a pizza is successfully created, increment numPizzas
    
    
    //int numPizza = Integer.parseInt(JOptionPane.showInputDialog(null, "How many pizzas will you be ordering?"));
    //Order order = new Order(numPizza);
    //int numPizzas = 0;
    ArrayList<Pizza> pizzaList = new ArrayList<Pizza>();
    Order order = new Order();
    String email = currentUser[8];
    
    do
    {
      //main dialog 
      String size = getPizzaSize();
      String crust = getCrustType();
      String sauce = getSauceType();
      String[] toppings = getToppings();
      Pizza thisPizza = null;
      
      System.out.println("printing out Pizza info before creating pizza ojbect:");
      System.out.println("size: " + size);
      System.out.println("crust: " + crust);
      System.out.println("sauce: " + sauce);
      System.out.println("toppings:");
      for(int i = 0; i < toppings.length; i++)
      {
        System.out.println(toppings[i]);
      }
      
      if (toppings.length > 0)
      {
        thisPizza = new Pizza(size, crust, sauce, toppings);
      }
      else
      {
        thisPizza = new Pizza(size, crust, sauce);
      }
      
      //numPizzas++;
      pizzaList.add(thisPizza);
      
    }
    while(JOptionPane.showConfirmDialog(null, "Add another pizza?", "WARNING",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    
    
    Pizza[] pizzas = pizzaList.toArray(new Pizza[pizzaList.size()]);
    
    System.out.println("Printing out the array of pizzas for this order before adding them to the order object:");
    for(int i = 0; i < pizzas.length; i++)
    {
      System.out.println(pizzas[i].toString());
    }
    
    order.setPizzas(pizzas);
    //save the order
    if(order.saveOrder(email))
      JOptionPane.showMessageDialog(null, "Order Placed");
  }
  
  
  private static String[] getToppings()
  {
    //create toppings after you get the count of the num elements in temp array
    //String[] toppings = null;
    
    
    //String[] temp = new String[20];
    //int numToppings = 0;
    ArrayList<String> toppingsList = new ArrayList<String>();
    
    int option = (JOptionPane.showConfirmDialog(null, "Add toppings to your pizza?", "WARNING",
                                                JOptionPane.YES_NO_OPTION));
    
    if(option == JOptionPane.YES_OPTION)
    {
      
      do
      {
        String topping = (String) JOptionPane.showInputDialog(null, 
                                                              "Add a topping:",
                                                              "Toppings",
                                                              JOptionPane.QUESTION_MESSAGE, 
                                                              null, 
                                                              Pizza.TOPPINGS, 
                                                              Pizza.TOPPINGS[0]);
        
        
        toppingsList.add(topping);
        
      }
      while(JOptionPane.showConfirmDialog(null, "Add another topping?", "WARNING",
                                          JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
      
    }
    
    
    String[] toppings = toppingsList.toArray(new String[toppingsList.size()]);
    return toppings;
  }
  
  
  private static String getSauceType()
  {
    String sauce = (String) JOptionPane.showInputDialog(null, 
                                                        "Choose a sauce:",
                                                        "Sauce",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        Pizza.SAUCES, 
                                                        Pizza.SAUCES[0]);
    return sauce;
  }
  private static String getCrustType()
  {
    String crust = (String) JOptionPane.showInputDialog(null, 
                                                        "Choose a crust:",
                                                        "Crust",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        Pizza.CRUSTS, 
                                                        Pizza.CRUSTS[0]);
    return crust;
  }
  
  private static String getPizzaSize()
  {
    String size = (String) JOptionPane.showInputDialog(null, 
                                                       "Choose a pizza size:",
                                                       "Size",
                                                       JOptionPane.QUESTION_MESSAGE, 
                                                       null, 
                                                       Pizza.SIZES, 
                                                       Pizza.SIZES[0]);
    return size;
  }
  
  /**
   * Lists the orders placed by a user
   */
  private static void listOrders() throws Exception{
    //needed to check against in the file
    String email = currentUser[8];
    String orders = "";
    
    Scanner scan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    
    while(scan.hasNextLine())
    {
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      if(linescan.next().equals(email)){
        orders += "Order ID: " + linescan.next() + "\nNumber of Pizzas: " + linescan.next() + "\nCost of the order: $" + linescan.next() + "\nOrder delivered? " + linescan.next() + "\n\n";
      }
    }
    orders += "If anything seems incorrect, please call the store at 555-555-5555";
    JOptionPane.showMessageDialog(null, orders);
    scan.close();
  }
  
  /**
   * Very similar to listOrders(), instead it only shows the most recent order
   */
  private static void listRecentOrder() throws Exception{
    String email = currentUser[8];
    String orders = "";
    
    Scanner scan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    
    while(scan.hasNextLine())
    {
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      if(linescan.next().equals(email)){
        orders = "Order ID: " + linescan.next() + "\nNumber of Pizzas: " + linescan.next() + "\nCost of the order: $" + linescan.next() + "\nOrder delivered? " + linescan.next() + "\n\n";
      }
    }
    orders += "If anything seems incorrect, please call the store at 555-555-5555";
    JOptionPane.showMessageDialog(null, orders);
    scan.close();
  }
  
  /**
   * Reserved for a manager, this lists all the orders in the file.  
   */
  private static void listAllOrders() throws Exception{
    String orders = "";
    
    Scanner scan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    
    while(scan.hasNextLine())
    {
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      orders += "Email: " +linescan.next()+ "\nOrder ID: " +linescan.next()+ "\nNumber of Pizzas: " +linescan.next()+ "\nCost of the order: $" +linescan.next()+ "\nOrder delivered? " +linescan.next()+ "\n\n";
    }
    orders += "If anything seems incorrect, please call operations at 111-111-1111";
    JOptionPane.showMessageDialog(null, orders);
    scan.close();
  }
  
  /**
   * Lists all orders still marked not delivered
   */
  private static void listOutstandingOrders() throws Exception{
    String orders = "";
    
    Scanner scan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    
    String[] order = new String[5]; 
    while(scan.hasNextLine())
    {
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      for(int i=0; i<order.length; i++){
        order[i] = linescan.next();
      }
      if(Boolean.valueOf(order[4]) == false){
        orders += "Email: " +order[0]+ "\nOrder ID: " +order[1]+ "\nNumber of Pizzas: " +order[2]+ "\nCost of the order: $" +order[3]+ "\n\n";
      }
    }
    orders += "If anything seems incorrect, please call operations at 111-111-1111";
    JOptionPane.showMessageDialog(null, orders);
  }
  
  /**
   * Used by the driver to determine which orders still need to be delivered
   */
  private static void listDeliveryOrderInfo(File userFile) throws Exception{
    String out = "";
    String[] userLine = new String[10];
    
    Scanner orderScan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    Scanner userScan = new Scanner(new FileInputStream(USER_FILE));
    
    while(orderScan.hasNextLine())
    {
      Scanner falselinescan = new Scanner(orderScan.nextLine());
      Scanner linescan = new Scanner(userScan.nextLine());
      linescan.useDelimiter(",");
      falselinescan.useDelimiter(",");
      if(falselinescan.nextBoolean() == false){
        String email = falselinescan.next();
        out += "\nOrder ID: " +falselinescan.next()+ "\nNumber of Pizzas: " +falselinescan.next()+ "\nCost of the order: $" +falselinescan.next()+ "\n -----\n";
        while(userScan.hasNextLine()){
          linescan.useDelimiter(",");
          int i = 0;
          while (linescan.hasNext()){
            userLine[i] = linescan.next();
            i++;
          }
          if(email.equals(userLine[8])){
            out += userLine[1] + " " + userLine[2] + "\n" + userLine[3] + " " + userLine[4]+" " +userLine[5]+" "+userLine[6] + "\n" + userLine[7] + "\n\n";
          }
        }
      }
    }
    out += "If anything seems incorrect, please call operations at 111-111-1111";
    JOptionPane.showMessageDialog(null, out);
  }
  
  /**
   * Used by the customer class to change their information.
   * @param userFile holds user account information
   */
  private static void myProfile(File userFile) throws Exception{
    String profile = "";
    String update = "";
    
    for(int i=1; i<currentUser.length; i++){
      profile += i +": " + currentUser[i] + "\r\n";
    }
    
    //Shows the user their current information and asks which needs updating
    try
    {
        int change = Integer.parseInt(JOptionPane.showInputDialog(null, "Please type the number of what you wish to update"  + profile));
        switch(change){
          case 1:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[1] = update;
            break;
          case 2:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[2] = update;
            break;
          case 3:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[3] = update;
            break;
          case 4:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[4] = update;
            break;
          case 5:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[5] = update;
            break;
          case 6:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[6] = update;
            break;
          case 7:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[7] = update;
            break;
          case 8:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[8] = update;
            break;
          case 9:
            update = JOptionPane.showInputDialog(null, "Please type your changes");
            currentUser[9] = update;
            break;
          case 10:
            break;
        } 
    }
    catch (NumberFormatException e)
    {
        //handle exception
    }
    
    
    String toSave = currentUser[0] + ",";
    for(int i=1; i<currentUser.length; i++){
      toSave += currentUser[i] + ",";
    }
    toSave += "\r\n";
    //Saves the changes
    writeToFile(userFile, toSave);
    
  }
  
  /**
   * helper method for file clases, needed to write changes to a file
   * @param file holds account information
   * @param info any information that needs to be saved
   */
  private static void writeToFile(File file, String info)
  {
    PrintWriter writer = null; 
    try {
      writer = new PrintWriter(new FileOutputStream(file, true));
      writer.append(info);
    } 
    catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    writer.close();
  }
  
}

