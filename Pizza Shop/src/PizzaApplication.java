import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PizzaApplication 
{
    private static final String USER_FILE = "src/users.txt";
    private static final String[] STATES = {"DC", "MD", "VA"};
    private static final String[] ACCOUNT_TYPES = {"Customer", "Employee","Manager", "Driver"};
    
    public static void main(String[] args) throws IOException
    {
        //Create a File object
        File userFile = new File(PizzaApplication.USER_FILE);

        //Validate the file
        boolean validFile = validateFile(userFile);
        boolean validLogin = false;

        if(!validFile)
            //Display file system error, terminate program
            JOptionPane.showMessageDialog(null,
                "File system error\n\nTerminating application",
                "System Error", JOptionPane.ERROR_MESSAGE);
        else
        {
            //Continue executing program
            
            validLogin = displayLogin(userFile);
            if(validLogin)
            {
                promptForUserType();
            }
        }
       
            
    }
    
    private static void promptForUserType()
    {
        String menu ="1- Customer\n2-Employee\n3-Driver\n4-Manager\n5-exit";
        int option = 0;
        do{
                option = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch(option){
                case 1:
                        displayCustomerMenu();
                        break;
                case 2:
                        displayEmployeeMenu();
                        break;
                case 3:
                        displayDriverMenu();
                        break;
                case 4:
                        displayManagerMenu();
                        break;
                case 5:
                        System.exit(0); 
                }
        }while(true);
    }
    
    private static void displayCustomerMenu()
    {
        String menu ="1- New Order\n2-Check My Orders\n3-Recent Orders\n4-My Profile\n5-back";
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
                        promptForUserType();
                        //System.exit(0); 
                }
        }while(true);
    }
    
    private static void displayEmployeeMenu()
    {
       //Employee should be an abstract class. Will implement and remove
    }
       
    private static void displayDriverMenu()
    {
        String menu ="1- New Order\n2-Deliveries\n3-My Delivery History\n4-Back";
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
                        promptForUserType();
                        //System.exit(0); 
                        
                }
        }while(true);
    }
    
    private static void displayManagerMenu()
    {
        String menu ="1- New Order\n2-Outstanding Orders\n3-Order History\n4-Add Driver\n5-My Profile\n6-back";
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
                        //TODO
                        promptForUserType();
                        //System.exit(0); 
                }
        }while(true);
    }

    private static boolean  displayLogin(File userFile) throws FileNotFoundException
    {
        boolean validEmail = false;
        boolean authenticationValid = false;
        String email = null;
       
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Email: ");
        JLabel passwordLabel = new JLabel("Password:");
        char[] password=null;
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
            password = pass.getPassword();
            //System.out.println("You entered: " + email + " and "+ new String(password));
            authenticationValid = authenticateUser(userFile, email, new String(password));
           
        }

        return authenticationValid;
    }
    
    private static boolean authenticateUser(File userFile, String email, String password) throws FileNotFoundException
    {
        //Authentication currently not working due to null values being read at the ends of each line in text file
        boolean authenticated = true;
        
        Scanner scanner = new Scanner(new FileInputStream(userFile));
    
        //scanner.useDelimiter("~");
        String[] thisLine = new String[50];
        String[] emails = new String[User.numUsers];
        String[] passwords = new String[User.numUsers];
        
        int tokenCounter = 0;
 
       
        
        while(scanner.hasNextLine())
        {
            //System.out.println("entering hasnextline");
            Scanner rowScan = new Scanner(scanner.nextLine());
            rowScan.useDelimiter("~");
            while (rowScan.hasNext())
            {
                thisLine[tokenCounter] = rowScan.next().trim();
                tokenCounter++;

            }
            
            //System.out.println("Results for this line: ");
//            for(int i= 0; i < thisLine.length; i++)
//            {
//                System.out.println("Printing out thisLine[" +i+"]: " +thisLine[i] + " ");
//            }
//            
//            System.out.println();  
        }

       
        
        return authenticated;
    }
    
    private static boolean validateFile(File userFile) throws IOException
    {
        boolean valid = false;
        boolean fileExists = checkFileExistence(userFile);
        
        if(fileExists)
        {
            boolean userInFile = checkForUser(userFile);
            if (userInFile)
                return true;      
        }
        else
            //Create the file if it does not exist 
            userFile.createNewFile();
        
        
        JOptionPane.showMessageDialog(null,"No users found\n\nPress OK to create a user account",
                "User file empty", JOptionPane.WARNING_MESSAGE);
        
      

        
        valid = promptForUserEntry(userFile);
        
        
        return valid;
    }

    private static boolean checkFileExistence(File userFile)
    {
        boolean exists = false;
        if(userFile.exists() && !userFile.isDirectory()) 
            return true;
        
        JOptionPane.showMessageDialog(null,"User file not found\n\nCreating file",
                "User file empty", JOptionPane.WARNING_MESSAGE);
        
        return exists;
    }

    private static boolean checkForUser(File userFile) throws FileNotFoundException, IOException
    {
        boolean userFound = false;
        BufferedReader reader = new BufferedReader(new FileReader(userFile));  
        
        if (reader.readLine() != null)
            return true;

        return userFound;       
    }
    
    private static boolean promptForUserEntry(File userFile)
    {
        //Still need to implement validation
        boolean successful = false;
        
        String accountType = (String) JOptionPane.showInputDialog(null, 
            "Choose an account type:",
            "Input",
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            PizzaApplication.ACCOUNT_TYPES, 
            PizzaApplication.ACCOUNT_TYPES[0]);
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
        String password = getUserPassword();
        
        successful = true;
        
        if(successful)
        {
            if(accountType.equals(PizzaApplication.ACCOUNT_TYPES[0]))
            {
                //Customer
                User customer = new Customer(firstName, lastName, streetAddress, city, state, zip, phone, email, password);
                
            }
            else if(accountType.equals(PizzaApplication.ACCOUNT_TYPES[1]))
            {
                //Employee
                User employee = new Employee(firstName, lastName, streetAddress, city, state, zip, phone, email, password);
                
            }
            else if(accountType.equals(PizzaApplication.ACCOUNT_TYPES[2]))
            {
                //Manager
                User manager = new Manager(firstName, lastName, streetAddress, city, state, zip, phone, email, password);

            }
            else
            {
                //Driver
                User driver = new Driver(firstName, lastName, streetAddress, city, state, zip, phone, email, password);
                
            }
            
           String[] userInfo = fillArray(accountType, firstName, lastName, streetAddress, 
                    city, state, zip,phone, email, password);
           
           writeToFile(userFile, userInfo); 
        }
            
        
        return successful;
    }
    
    private static void writeToFile(File userFile, String[] userInfo)
    {
        PrintWriter writer = null; 
		try {
			writer = new PrintWriter(new FileOutputStream(userFile, true));
                        
//                        for(String attribute : userInfo)
//                        {
//                            writer.append(attribute +"~");
//                        }
                        for(int i = 0; i < userInfo.length; i++)
                        {
                            if(i != userInfo.length-1)
                            {
                                writer.append(userInfo[i] + "~");
                            }
                            else
                            {
                                writer.append(userInfo[i] +"\n");
                            }
                            
                        }
			//writer.append("\n"+content);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.close();
    }
    
    private static String[] fillArray(String accountType, 
            String firstName, String lastName, String streetAddress, 
            String city, String state, String zip, String phone, String email, String password)
    {
            String[] userInfo = new String[9];
            userInfo[0] = accountType;
            userInfo[1] = firstName;
            userInfo[2] = lastName;
            userInfo[3] = streetAddress;
            userInfo[4] = city;
            userInfo[5] = state;
            userInfo[6] = zip;
            userInfo[7] = email;
            userInfo[8] = password;
            
            return userInfo;
    }
    
    
    
    private static String getUserPassword()
    {

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        char[] password=null;
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Enter Password",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            password = pass.getPassword();
            //System.out.println("Your password is: " + new String(password));
        }
        
        return new String(password);
    }
    
   
}

