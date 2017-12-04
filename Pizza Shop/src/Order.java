import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * Order Data definition class, consists of Pizzas and includes functionality to mark as delivered
 * @author Sharif Elkasse, John Cognetti
 */
public class Order 
{
  protected static final String ORDER_FILE = "orders.txt";  
//can use printf to add "O-00000" and pad with appropriate amount of zeros in front when storing to text file
  private static int orderID = 1;
  
  //Changed to protected, since Driver needs to see
  protected boolean wasDelivered;
  private int numPizzas;
  private Pizza[] pizzas;
  
  public Order()
  {
     ++orderID; 
  }
  
  public Order(int numPizzas) 
  {
    this();
    this.numPizzas = numPizzas;
    this.pizzas = new Pizza[numPizzas];
    
  }
  
  /**
   * Loops through the pizzas array to calculate cost
   * @return double the cost of the order
   */
  private double calculateCost()
  {
    System.out.println("Entering calculateCost()\n numPizzas is " + numPizzas);
    double cost = 0.0;
    for(int i=0; i<numPizzas; i++)
    {

      //need to create the pizza
      //currently causing a NullPointerException due to there being no pizzas in the array
      //instead of starting the prompt with how many pizzas, should perform a "While answer = yes" loop
        //continue to ask the user if they would like to add another pizza to the order
     
      cost += pizzas[i].calcCost();
    }
    return cost;
  }
  
  /**
   * getPizzas is the input dialog for the user to input their order
   */
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

    public void setPizzas(Pizza[] pizzas) {
        this.pizzas = pizzas;
        this.numPizzas = pizzas.length;
    }
  
  
  /**
   * saveOrder is the method to write an order to a file in order to save it perminantly.
   * @param email the email that will be associtated with the order
   */
  protected boolean saveOrder(String email) throws Exception{
    //File IO
    File orderFile = new File(Order.ORDER_FILE);
    orderFile.createNewFile();
    
    System.out.println("Printing order information to save to file:\n\n");
    System.out.println("orderID: " + orderID);
    System.out.println("numPizzas: " + numPizzas);
    System.out.println("[pizzas]: ");
    for(int i = 0; i < pizzas.length; i++)
    {
        System.out.println(pizzas[i]);
    }
    System.out.println("calculateCost: " + Double.toString(calculateCost()));
    
    boolean saved = false;
    String toSave = "";
    toSave += email+ "," + orderID + "," + numPizzas + "," + Double.toString(calculateCost()) + "," + String.valueOf(wasDelivered) + ",\r\n";
    
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
