/**
 * @Author John Green & Stacy Austin
 * @sources www.mkyong.com, www.sitepoint.com, www.stackoverflow.com
 */

public class Driver {


 public static void main(String args[]) {
  NewTable cars = new NewTable("cars.txt");

  //restrict the cars table to toyotas producing a NewTable named toyotas
  NewTable toyotas = Algebra.Restrict(cars, "MAKE='Toyota'");
  //project just three columns from the toyotas table producing a NewTable named answer
  NewTable answer = Algebra.Project(toyotas, "Make,Model,Price");
  //display the contents of the answer table
  Algebra.Display(answer);

  //= to 21,000
  NewTable equals = Algebra.Restrict(cars, "PRICE=21000");
  Algebra.Display(equals);

  //greaterThan or = 21,000
  NewTable greaterThan = Algebra.Restrict(cars, "PRICE>=21000");
  Algebra.Display(greaterThan);

  //lessThan than or = 21,000
  NewTable lessThan = Algebra.Restrict(cars, "PRICE<=21000");
  Algebra.Display(lessThan);
 }
}