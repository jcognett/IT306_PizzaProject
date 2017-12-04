import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author j3ste
 */
public class Order 
{
  private static final String ORDER_FILE = "orders.txt";  
//can use printf to add "O-00000" and pad with appropriate amount of zeros in front when storing to text file
  private static int orderID = 1;
  
  //Changed to protected, since Driver needs to see
  protected boolean wasDelivered;
  private int numPizzas;
  private Pizza[] pizzas;
  
  public Order(int numPizzas) 
  {
    this.orderID = orderID++;
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
  
  private void getPizzas() throws Exception{
    for(int i=0; i<numPizzas; i++){
      String size = JOptionPane.showInputDialog(null, "What size pizza will you be ordering? \n Options are: Kids, Small, Medium, Large, Party");
      String crust = JOptionPane.showInputDialog(null, "What style of crust would you like? \n Options are: Thin, Deep-Dish, Regular");
      String sauce = JOptionPane.showInputDialog(null, "What Sauce would you like? \n Options are: White, Tomato, Rosemary Tomato");
      int numToppings = Integer.parseInt(JOptionPane.showInputDialog(null, "How many toppings would you like?"));
      String[] toppings = new String[numToppings];
      for(int j=0; j<numToppings; j++){
        toppings[j] = JOptionPane.showInputDialog(null, "Add one topping at a time. \n Options are: Pepperoni, Onions, Mushrooms, Pineapple, Ham, Olives, Bell Peppers, Feta");
      }
      Pizza pizza = new Pizza(size, crust, sauce, toppings);
      pizzas[i] = pizza;
    }
  }
  
  protected boolean saveOrder() throws Exception{
    //File IO
    File orderFile = new File(Order.ORDER_FILE);
    orderFile.createNewFile();
    
    boolean saved = false;
    String toSave = "";
    toSave += "" + orderID + "," + numPizzas + "," + Double.toString(calculateCost()) + "," + String.valueOf(wasDelivered);
    
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
