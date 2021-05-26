import java.util.Arrays;
import java.util.stream.Collectors;

public class Movies {

  public static void main(String[] args) {

    Movie[] movies = new Movie[8];
    movies[0] = new Movie("The Godfather", 1972);
    movies[1] = new Movie("The Good, the Bad, and the Ugly", 1966);
    movies[2] = new Movie("Pulp Fiction", 1994);
    movies[3] = new Movie("Shindler's List", 1993);
    movies[4] = new Movie("Casablanca", 1942);
    movies[5] = new Movie("Wizard of Oz", 1939);
    movies[6] = new Movie("Citizen Kane", 1941);
    movies[7] = new Movie("Some Like It Hot", 1959);

//    Movie[] movies = new Movie[4];
//    movies[0] = new Movie("c",0);
//    movies[1] = new Movie("b",0);
//    movies[2] = new Movie("d",0);
//    movies[3] = new Movie("a",0);

    nameSort(movies);
    display(movies);
  }

  public static void nameSort(Movie[] array) {

    int a, b;
    Movie temp;
    int highSub = array.length;

//    for (a = 0; a < highSub; ++a) {
//      for (b = 0; b < highSub-1; ++b) { // nicht bis zum Ende laufen
//        String first = array[b].getName();
//        String second = array[b + 1].getName();
//        if (first.compareTo(second) > 0) {
//          temp = array[b];
//          array[b] = array[b+1]; // zweiter Index auf b+1 geändert
//          array[b + 1] = temp;
//        }
//        System.out.println(Arrays.stream(array).map(Movie::getName).collect(Collectors.toList()));
//      }
//    }
    int c = 0;

//    for (a = 0; a < highSub; ++a) {
//      for (b = 0; b < highSub; ++b) {
//        c++;
//        String first = array[a].getName(); // index von b auf a geändert
//        String second = array[b].getName(); // index von b+1 auf b geändert
//        if (first.compareTo(second) < 0) {
//          temp = array[a]; // index von b auf a geändert
//          array[a] = array[b]; // index vorne von b auf a geändert
//          array[b] = temp;
//        }
//        System.out.println(Arrays.stream(array).map(Movie::getName).collect(Collectors.toList()));
//      }
//    }

    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (b = 0; b < highSub - 1; ++b) { // nicht bis zum Ende laufen
        c++;
        String first = array[b].getName();
        String second = array[b + 1].getName();
        if (first.compareTo(second) > 0) {
          temp = array[b];
          array[b] = array[b + 1]; // zweiter Index auf b+1 geändert
          array[b + 1] = temp;
          swapped = true;
        }
        System.out.println(Arrays.stream(array).map(Movie::getName).collect(Collectors.toList()));
      }
    }


    System.out.println(c);
  }

  public static void display(Movie[] s) {

    int len = s.length;
    String msg = "";
    for (int i = 0; i < len; i++) {
      msg = msg + s[i].getName() + ", " + s[i].getYear() + "\n";
    }

    System.out.println(msg);

  }

}

class Movie {

  private String name;
  private int year;

  Movie(String s, int y) {
    name = s;
    year = y;
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }
}
