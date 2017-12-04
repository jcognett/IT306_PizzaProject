import java.io.*;
import java.util.*;

/**
 *
 * @author j3ste
 */
public class Order 
{
  private static final String ORDER_FILE = "orders.txt";  
//can use printf to add "O-00000" and pad with appropriate amount of zeros in front when storing to text file
  private int orderID = 1;
  
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
  
  private boolean saveOrder() throws IOException{
    //File IO
    File orderFile = new File(Order.ORDER_FILE);
    orderFile.createNewFile();
    
    boolean saved = false;
    String toSave = "";
    toSave += "" + orderID + ", " + numPizzas + ", " + Double.toString(calculateCost()) + ", " + String.valueOf(wasDelivered);
    
    PrintWriter writer = null; 
    try{
      writer = new PrintWriter(new FileOutputStream(orderFile, true));
      writer.append(toSave);
      saved = true;
    } 
    catch (FileNotFoundException e){
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    writer.close();
    return saved;
  }
  
  
}
