/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author j3ste
 */
public class Manager extends Employee
{
    public Manager(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password)
    {
        super(firstName, lastName, address, city, state, zip, phone, email, password);
    }
    
    //this should probably be return type of Employee, placed here to avoid error
    private void createEmployeeAccount(String firstName, String lastName, String address, String city, String state, String zip, String phone, String email, String password, int type)
    {
      //type 1 is a regular employee
      if (type == 1){
        Employee emp = new Employee(firstName, lastName, address, city, state, zip, phone, email, password);
      }
      //type 2 is a driver
      if(type == 2){
        Driver drive = new Driver(firstName, lastName, address, city, state, zip, phone, email, password);
      }
    }
    
    //this should probably be a return type of Order[], placed here to avoid error
    public void viewAllOrders()
    {
        
    }
}
