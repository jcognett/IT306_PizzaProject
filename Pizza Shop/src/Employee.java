/**
 * Employee type class, child of User and parent of Driver and Manager
 * @author Sharif Elkasse, John Cognetti
 */

public class Employee extends User
{
    //can use printf to add "E-00000" and pad with appropriate amount of zeros in front when storing to text file
    private  int employeeID = 1;
    
    public Employee(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password){
      super(firstName, lastName, address, city, state, zip, phone, email, password);
    }
    
    private void editOrder()
    {
        
    }
}
