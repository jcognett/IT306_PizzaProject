/**
 *User data definition class is the parent of all users on the system.  It holds all personal information on the accounts.  
 * @author Sharif Elkasse, John Cognetti
 */
public abstract class User 
{
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private String password;
    
    //public final static int MAX_ORDERS = 10;
    private Order currentOrder;
    private Order[] orderHistory = new Order[10];
    
    public static int numUsers = 0;
    
    public  User(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password){
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.phone = phone;
    this.email = email;
    this.password = password;
    
    numUsers++;
    }

    public void placeOrder()
    {
        
    }
    
    public Order viewOrder()
    {
        return currentOrder;
    }
    
    
    
    private void changePassword()
    {
        
    }
    
}
