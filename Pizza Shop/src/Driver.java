/**
 * Driver type class, child of Employee
 * @author Sharif Elkasse, John Cognetti
 */
public class Driver extends Employee
{
    public  Driver(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password)
    {
      
        super(firstName, lastName, address, city, state, zip, phone, email, password);
    }
    
    private void deliverOrder(Order order)
    {
        order.wasDelivered = true;
    }
    
}
