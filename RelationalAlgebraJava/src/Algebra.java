import java.io.PrintStream;
import java.util.Scanner;
/**
 * @Author John Green & Stacy Austin
 * @sources www.mkyong.com, www.sitepoint.com, www.stackoverflow.com
 * writes new NewTable using tilde delimited text file
 * @Algebra  comparators for NewTables
 */
public class Algebra {

 public static NewTable Project(NewTable tbl, String clms) {

  int num1 = num1(clms);
  int num2 = tbl.number();
  int num3 = 0;


  String[] array = buildTable(num1, clms);

  String[][] retreive = new String[num2][num1];


  int x = 0;
  while (x < array.length) {
   for (int z = 0; z < tbl.tbl[0].length; z++) {

    if (array[x].equalsIgnoreCase(tbl.tbl[0][z])) {

     for (int y = 0; y < tbl.tbl.length; y++) {

      retreive[y][num3] = tbl.tbl[y][z];
     }
     num3 += 1;
    }
   }
   x++;
  }
  return new NewTable(retreive);
 }

 /**
  * Restrict the tbl by a string.
  *
  * @param tbl the tbl to restrict
  * @param string the string contains the restr
  * @return 
  */
 public static NewTable Restrict(NewTable tbl, String string) {

  String c1 = c1(string);
  String restr = restrict(string);
  String oper = oper(string);

  switch (oper) {
   case ">=":
    return new NewTable(gtlteq(tbl, c1, restr, oper));
 
   case ">":
    return new NewTable(gtlteq(tbl, c1, restr, oper));

   case "<=":
    return new NewTable(gtlteq(tbl, c1, restr, oper));

   case "<":
    return new NewTable(gtlteq(tbl, c1, restr, oper));
   
   case "=":
     return new NewTable(eq(tbl, c1, restr));

   case "!=":
    return new NewTable(eqTbl(tbl, c1, restr));

   default:
    System.out.println("ERROR CANNOT PERFORM THIS COMPARISON");
  }

  return tbl;
 }

 public static void Display(NewTable tbl) {

  String[][] a = tbl.tbl;
  String columns = "";

  for (String item: a[0]) {
   columns = item;
   PrintStream printf;
   printf = System.out.printf("%-16s", columns);
  }
  System.out.println();

  for (String item: a[0]) {
   System.out.print("-----           ");
  }

  for (int x = 1; x < a.length; x++) {

   System.out.println("");

   for (int y = 0; y < a[0].length; y++) {

    System.out.printf("%-16s", a[x][y]);
   }

  }

  System.out.println("\n");
 }

 /**
  * performs the not equals operation
  * @param tbl the tbl the operation is performed on
  * @param clm the c1 to the value is not equal to
  * @param restr the value that is being restricted
  * @return
  */
 private static String[][] eqTbl(NewTable tbl, String clm, String restr) {
  int clm2 = tbl.c1(clm);
  int row2 = 1;

  String[][] array = new String[rrow(tbl, clm, restr)][tbl.rcol()];

  System.arraycopy(tbl.tbl[0], 0, array[0], 0, tbl.tbl[0].length);

  for (int y = 1; y < tbl.tbl.length; y++) {
   if (!tbl.tbl[y][clm2].equalsIgnoreCase(restr)) {

    System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

    row2 += 1;
   }
  }
  return array;
 }

 /**
  * compares tables based upon > < >= <=
  * @param tbl the tbl the operation is performed on
  * @param clm the c1 to the value is equal to
  * @param restr the value that is being restricted
  * @param exe is the operation being performed
  * @return
  */
 private static String[][] gtlteq(NewTable tbl, String clm, String restr, String exe) {

  int clm2 = tbl.c1(clm);
  int row2 = 1;

  String[][] array = new String[rrow(tbl, clm, restr, exe)][tbl.rcol()];

  // Load Columns
  System.arraycopy(tbl.tbl[0], 0, array[0], 0, tbl.tbl[0].length);

  if (exe.equals(">")) {
   for (int y = 1; y < tbl.tbl.length; y++) {
    if (Integer.parseInt(tbl.tbl[y][clm2]) > Integer.parseInt(restr)) {
     System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

     row2 += 1;
    }
   }
  }

  if (exe.equals("<")) {
   for (int y = 1; y < tbl.tbl.length; y++) {
    if (Integer.parseInt(tbl.tbl[y][clm2]) < Integer.parseInt(restr)) {
     System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

     row2 += 1;
    }
   }
  }

  if (exe.equals("<=")) {
   for (int y = 1; y < tbl.tbl.length; y++) {
    if (Integer.parseInt(tbl.tbl[y][clm2]) <= Integer.parseInt(restr)) {
     System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

     row2 += 1;
    }
   }
  }

  if (exe.equals(">=")) {
   for (int y = 1; y < tbl.tbl.length; y++) {
    if (Integer.parseInt(tbl.tbl[y][clm2]) >= Integer.parseInt(restr)) {
     System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

     row2 += 1;
    }
   }
  }

  return array;
 }


 /**
  * returns the number of rows for a new tbl array
  * @param tbl the tbl containing the rows
  * @param clm the c1
  * @param restr the restr
  * @return
  */
 private static int rrow(NewTable tbl, String clm, String restr) {

  int clm2 = tbl.c1(clm);
  int roID = 0;
  if (clm2 < 0) {
   System.out.println("ERROR COLUMN DOES NOT EXIST");
   return -1;
  }

  for (int x = 1; x < tbl.tbl.length; x++) {
   if (tbl.tbl[x][clm2].equalsIgnoreCase(restr))
    roID += 1;
  }
  return roID + 1; // Have to include the r1 for the columns as well with + 1
 }

 /**
  * returns the number of rows after a particular operation has occurred x.e. > < >= <=
  * @param tbl the tbl containing the rows
  * @param clm the c1 in the restr
  * @param restr the restr
  * @param exe the operation to be performed
  * @return
  */
 private static int rrow(NewTable tbl, String clm, String restr, String exe) {

  int clm2;
  clm2 = tbl.c1(clm);
  int roID = 0;


  if (clm2 < 0) {
   System.out.println("ERROR COLUMN DOES NOT EXIST");
   return -1;
  }

   /*  if (exe.equals("=")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) <= Integer.parseInt(restr))
     roID += 1;

   }
  }
   
   if (exe.equals("!=")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) <= Integer.parseInt(restr))
     roID += 1;

   }
  }
  */  
   
 if (exe.equals(">=")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) >= Integer.parseInt(restr))
     roID += 1;

   }
  }
 
  if (exe.equals(">")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) > Integer.parseInt(restr))
     roID += 1;

   }
  }

   if (exe.equals("<=")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) <= Integer.parseInt(restr))
     roID += 1;

   }
  }
   
  if (exe.equals("<")) {
   for (int x = 1; x < tbl.tbl.length; x++) {
    if (Integer.parseInt(tbl.tbl[x][clm2]) < Integer.parseInt(restr))
     roID += 1;

   }
  }
  
  return roID + 1; // Have to include the r1 for the columns with + 1
 }

 /**
  * @param tbl the tbl the operation is performed on
  * @param clm the c1 to the value is equal to
  * @param restr the value that is being restricted
  * @return
  */
 private static String[][] eq(NewTable tbl, String clm, String restr) {
  int clm2 = tbl.c1(clm);
  int row2 = 1;

  String[][] array = new String[rrow(tbl, clm, restr)][tbl.rcol()];

  System.arraycopy(tbl.tbl[0], 0, array[0], 0, tbl.tbl[0].length);

  for (int y = 1; y < tbl.tbl.length; y++) {
   if (tbl.tbl[y][clm2].equalsIgnoreCase(restr)) {

    System.arraycopy(tbl.tbl[y], 0, array[row2], 0, tbl.tbl[0].length);

    row2 += 1;
   }
  }
  return array;
 }

 /**
  * returns the restr from the restr string
  * @param restrict tbl restr
  * @return
  */
 private static String restrict(String restrict) {
  String str = "";
  Scanner scan2 = new Scanner(restrict);
  scan2.useDelimiter(">=|>|<=|<|!=|=|'");
  scan2.next();

  while (scan2.hasNext()) {
   String a = scan2.next();
   if (!a.contains(" ")) {
    str = a;
   }
  }
  return str;
 }

 /**
  * returns the operator of a restr string
  * @param restrict is a string containing a tbl restr
  * @return
  */
 private static String oper(String restrict) {
  return restrict.replaceAll("[^=<>!]", "");
 }

 /**
  * returns the c1 from a restr string
  * @param restrict is a string containing a tbl restr
  * @return
  */
 private static String c1(String restrict) {
  Scanner scan2 = new Scanner(restrict);
  scan2.useDelimiter("=|>|<|>=|<=|!=|'");

  return scan2.next();
 }

 /**
  * return the num3 of columns and only counts duplicates once
  * @param a is a String array
  * @param b is a String array
  * @return
  */
 private static int num1(String[] a, String[] b) {

  int num3 = 0;

  for (String a2: a) {
   for (String a3: b) {
    if (a2.equalsIgnoreCase(a3)) {
     num3 += 1;
    }
   }
  }
  return a.length + b.length - num3;
 }

 /**
  * returns number of columns contained in a string
  *
  * @param clms is a string containing columns
  * @return
  */
 private static int num1(String clms) {

  int num3 = 0;

  Scanner scan = new Scanner(clms);
  scan.useDelimiter(", *");

  while (scan.hasNext()) {
   scan.next();
   num3 += 1;
  }
  return num3;
 }

 /**
  * Takes the determined number of columns and extracts those columns from a string into a String array
  * @param cols the number of columns that should be extracted from the string
  * @param clms the string containing the columns
  * @return
  */
 private static String[] buildTable(int cols, String clms) {
  String[] a = new String[cols];

  int num3 = 0;

  Scanner scan = new Scanner(clms);
  scan.useDelimiter(", *");

  while (scan.hasNext()) {

   a[num3] = scan.next();
   num3 += 1;


  }
  return a;

 }
}