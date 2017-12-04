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
  
  private static void initialMenu(File userFile) throws FileNotFoundException, Exception{
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
  
  private static void displayLogin(File userFile) throws FileNotFoundException, Exception
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
        displayCustomerMenu(userFile);
      case 2:
        displayEmployeeMenu(userFile);
      case 3:
        displayManagerMenu(userFile);
      case 4:
        displayDriverMenu(userFile);
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
        currentUser[i] = userLine[i]; 
        i++;
      }
      if((email.equals(userLine[8])) && (password.equals(userLine[9]))){
        authenticatedUserType = Integer.parseInt(userLine[0]);
      }
    }
    return authenticatedUserType;
  }
  
  private static void displayCustomerMenu(File userFile) throws Exception
  {
    String menu ="1- New Order\n2- Check My Orders\n3- Recent Order\n4- My Profile\n5- Logout";
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
          myProfile(userFile);
          break;
        case 5:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
  private static void displayEmployeeMenu(File userFile) throws Exception
  {
    String menu ="1- New Order\n2- Check My Orders\n3- Recent Order\n4- My Profile\n5- Logout";
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
          myProfile(userFile);
          break;
        case 5:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
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
          //TODO
          break;
        case 4:
          JOptionPane.showMessageDialog(null, "Logging you out...");
          displayLogin(userFile);
      }
    }while(true);
  }
  
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
  
  private static void promptForUserEntry(File userFile) throws FileNotFoundException, Exception
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
    
    
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\r\n";
    writeToFile(userFile, userInfo);
    
    displayLogin(userFile);
  }
  
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
                                                        "Input",
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, 
                                                        PizzaApplication.STATES, 
                                                        PizzaApplication.STATES[2]);
    
    String zip = JOptionPane.showInputDialog(null,"Zip Code: ");
    String email = JOptionPane.showInputDialog(null,"Email Address: ");
    String password = JOptionPane.showInputDialog(null, "Password:");
    
    userInfo += accountType + "," + firstName + "," + lastName + "," + streetAddress + "," + city + "," + state + "," + zip + "," + phone + "," + email + "," + password + ",\r\n";
    writeToFile(userFile, userInfo);
    
    displayManagerMenu(userFile);
  }
  
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
                                                              "Input",
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
                                                        "Input",
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
                                                        "Input",
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
                                                       "Input",
                                                       JOptionPane.QUESTION_MESSAGE, 
                                                       null, 
                                                       Pizza.SIZES, 
                                                       Pizza.SIZES[0]);
    return size;
  }
  
  private static void listOrders() throws Exception{
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
  
  private static void listOutstandingOrders() throws Exception{
    String orders = "";
    
    Scanner scan = new Scanner(new FileInputStream(Order.ORDER_FILE));
    
    while(scan.hasNextLine())
    {
      Scanner falsescan = new Scanner(scan.nextLine());
      Scanner linescan = new Scanner(scan.nextLine());
      linescan.useDelimiter(",");
      falsescan.useDelimiter(",");
      if(falsescan.nextBoolean() == false){
        orders += "Email: " +linescan.next()+ "\nOrder ID: " +linescan.next()+ "\nNumber of Pizzas: " +linescan.next()+ "\nCost of the order: $" +linescan.next()+ "\n\n";
      }
    }
    orders += "If anything seems incorrect, please call operations at 111-111-1111";
    JOptionPane.showMessageDialog(null, orders);
  }
  
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
  
  private static void myProfile(File userFile) throws Exception{
    String email = currentUser[8];
    String profile = "";
    String update = "";
    
    for(int i=1; i<currentUser.length; i++){
      profile += i +": " + currentUser[i] + "\r\n";
    }
    
    int change = Integer.parseInt(JOptionPane.showInputDialog(null, profile +"Or type 10 to cancel \r\n" + "Please type the number of what you wish to update"));
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
    
    String toSave = "";
    for(int i=1; i<currentUser.length; i++){
      toSave += currentUser[i] + ",";
    }
    toSave += "\r\n";
    writeToFile(userFile, toSave);
    
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

