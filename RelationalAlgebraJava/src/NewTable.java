import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @Author John Green & Stacy Austin
 * @sources www.mkyong.com, www.sitepoint.com, www.stackoverflow.com
 * writes NewTable using tilde delimited text file
 */


public final class NewTable {

 private File newFile;
 /**
  * The Table.
  */
 public final String[][] tbl;
 /**
  * The Parser.
  */
 public Scanner scan;

 /**
  *
  * @param writeFile  in a newFile and creates a tbl in memory
  */
 public NewTable(String writeFile) {

  this.newFile = new File("src/" + writeFile);

  tbl = new String[roID()][clm2()];
  makeTable();

 }

 /**
  *
  * @param tbl2
  */
 public NewTable(String[][] tbl2) {

  this.tbl = tbl2;
 }


 private void makeTable() {

  try {

   int r1 = 0;

   try (Scanner scan2 = new Scanner(newFile)) {
    // scan2.useDelimiter("~");
    while (scan2.hasNextLine()) {

     int c1 = 0;
     String next = scan2.nextLine();

     scan = new Scanner(next);
     scan.useDelimiter("~ *");
     while (scan.hasNext()) {

      tbl[r1][c1] = scan.next();
      c1 += 1;
     }

     r1 += 1;
    }
   }
  } catch (FileNotFoundException e) {}

 }

 /**
  * Return an array of columns from the tbl.
  *
  * @return the string [ ]
  */
 public String[] Carray() {

  String[] array = new String[tbl[0].length];

  System.arraycopy(tbl[0], 0, array, 0, tbl[0].length);

  return array;

 }

 /**
  * Gets the length of the tbl as the amount of columns.
  *
  * @return the number of columns
  */
 public int rcol() {

  return this.tbl[0].length;
 }

 /**
  * Gets the total amount of columns from the parsed column string.
  *
  * @param column the column
  * @return 
  * @returns c1 
  */
 public int c1(String column) {

  for (int numcol = 0; numcol < tbl[0].length; numcol++) {
   if (tbl[0][numcol].equalsIgnoreCase(column)) {
    return numcol;
   }
  }
  return -1;
 }


 /**
  * Gets column number from newFile.
  *
  * @return the column number
  */
 public int clm2() {

  int num3 = 0;

  try {

   try (Scanner scan2 = new Scanner(newFile)) {
    String next = scan2.nextLine();

    scan = new Scanner(next);
    scan.useDelimiter("~");
    while (scan.hasNext()) {
     scan.next();
     num3 += 1;

    }
   }
  } catch (FileNotFoundException e) {}

  return num3;
 }

 /**
  * Gets r1 numbers from newFile.
  *
  * @return the r1 number from newFile
  */
 public int roID() {

  int num3 = 0;

  try {

   try (Scanner scan2 = new Scanner(newFile)) {
    while (scan2.hasNextLine()) {


     String next = scan2.nextLine();

     //Must check numcol for empty lines in the newFile
     if (next.length() > 0) {
      num3 += 1;
     }


    }
   }
  } catch (FileNotFoundException e) {}


  return num3;
 }

 /**
  * Gets amount of rows in the tbl by the length.
  *
  * @return the r1 number
  */
 public int number() {

  return tbl.length;
 }
}