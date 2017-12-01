/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author j3ste
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
