import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Pizza type class, Map data structure used for the prices and in calculating cost.
 * @author Sharif Elkasse, John Cognetti
 */
public class Pizza 
{
  private String size;
  private String crust;
  private String sauce;
  private String[] toppings;
 
  public static final String[] SIZES = {"Kids","Small","Medium","Large","Party"};
  public static final String[] CRUSTS = {"Thin", "Deep-Dish", "Regular"};
  public static final String[] SAUCES = {"White", "Tomato","Rosemary Tomato"};
  public static final String[] TOPPINGS = {"Pepperoni", "Onions", "Mushrooms", "Pineapple", "Ham","Olives","Bell Peppers", "Feta"};
  
//      The following rules also apply to pizza pricing:
  
//    o Price​ ​for​ ​Deep​ ​Dish​ ​additional​ ​$3.00 
//    o Price​ ​of​ ​3​ ​to​ ​4​ ​topping​ ​additional​ ​$3.00 
//    o Price​ ​of​ ​5​ ​or​ ​more​ ​topping​ ​additional​ ​$4.00 
  public static final Map <String, Double> pMap = new HashMap<String,Double>()
  {{
    put("Kids", 6.00);
    put("Small", 7.00);
    put("Medium", 8.00);
    put("Large", 9.00);
    put("Party", 10.00); 
  }};
  
  public static final Map <String, Double> cMap = new HashMap<String,Double>()
  {{
    put("Thin", 0.0);
    put("Deep-Dish", 3.00);
    put("Regular", 0.0);
  }};
  
  /**
   * Pizza constructor
   * 
   * @param size Size category of pizza
   * @param crust crust style of the pizza
   * @param sauce sauce of the pizza
   * @param toppings Array of toppings
   * 
   * @exception Exception Throw an error that there was a pizza mistake 
   */
  
  public Pizza(){}
  
  public Pizza(String size, String crust, String sauce, String[] toppings) throws Exception{
    if(Arrays.asList(SIZES).contains(size)){
      this.size = size;
    }
    else
      throw new Exception("Invalid Pizza");
    if(Arrays.asList(CRUSTS).contains(crust)){
      this.crust = crust;
    }
    else
      throw new Exception("Invalid Pizza");
    if(Arrays.asList(SAUCES).contains(sauce)){
      this.sauce = sauce;
    }
    else
      throw new Exception("Invalid Pizza");
    boolean flag = true;
    for(int i=0; i<toppings.length; i++){
      if(!(Arrays.asList(TOPPINGS).contains(toppings[i]))){
        flag = false;
      }
    }
    if(flag){
      this.toppings = toppings;
    }
    else
      throw new Exception("Invalid Pizza");
  } 

    public Pizza(String size, String crust, String sauce) {
        this.size = size;
        this.crust = crust;
        this.sauce = sauce;
    }
  
  
  
  /**
   * CalcCost method returns the cost of an individual pizza
   * @return the cost of the pizza
   */
  public double calcCost(){
    double cost = 0;
    cost += pMap.get(size);
    cost += cMap.get(crust);
    //Toppings are priced at 3.00 for 3-4 topppings, and 4.0 for 5+ toppings
    
    if(toppings !=null)
    {
        if((toppings.length == 3) || (toppings.length == 4)){
           cost += 3.0;
        }
        if (toppings.length > 4){
          cost += 4.0;
        }
    }
    
    return cost;
  }

    @Override
    public String toString() {
        return "Pizza{" + "size=" + size + ", crust=" + crust + ", sauce=" + sauce + ", toppings=" + toppings + '}';
    }
  
  
  
}
