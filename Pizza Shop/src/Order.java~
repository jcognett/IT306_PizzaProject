/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author j3ste
 */
public class Order 
{
  //can use printf to add "O-00000" and pad with appropriate amount of zeros in front when storing to text file
  private  int orderID = 1;
  
  //Changed to protected, since Driver needs to see
  protected boolean wasDelivered;
  private int numPizzas;
  private Pizza[] pizzas;
  
  public void Order(int numPizzas) 
  {
    this.orderID = ++orderID;
    this.numPizzas = numPizzas;
    this.pizzas = new Pizza[numPizzas];
  }
  
  /**
   * Loops through the pizzas array to calculate cost
   * @return double the cost of the order
   */
  private double calculateCost()
  {
    double cost = 0.0;
    for(int i=0; i<numPizzas; i++){
      cost += pizzas[i].calcCost();
    }
    return cost;
  }
  
  
}
