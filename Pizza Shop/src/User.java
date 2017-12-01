/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author j3ste
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
    private Order[] orderHistory;
    
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
