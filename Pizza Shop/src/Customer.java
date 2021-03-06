/**
 * Customer type class, child of User
 * @author Sharif Elkasse, John Cognetti
 */
public class Customer extends User
{
    //can use printf to add "C-00000" and pad with appropriate amount of zeros in front when storing to text file
    private int customerID = 1;

    public Customer(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password) 
    {
        super(firstName, lastName, address, city, state, zip, phone, email, password);
        this.customerID = ++customerID;
    }
    
    
    
    
}
