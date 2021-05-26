public class StripTags {

  public static String removeTags(String text) {
    StringBuffer result = new StringBuffer();
    boolean quote = false, tag = false;
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (c == '"') {
        quote = !quote;
      }
      else if (c == '<' && !quote) {
        tag = true;
      }
      else if (c == '>' && !quote) {
        tag = false;
      }
      else if (!tag) result.append(c);
    }
    return result.toString();
  }


  public static void main(String[] args) {
    // Entfernen einfacher Tags funktioniert
    String s1 = removeTags("<p>Das ist ein Text, bei dem <b>alle</b> Tags entfernt wurden.</p>");
    System.out
        .println("'" + s1 + "'"); // => 'Das ist ein Text, bei dem alle Tags entfernt wurden.'

    // Tags in Anführungszeichen funktionieren auch
    String s2 = removeTags("<input type=\"text\" value=\"<Enter Name>\">");
    System.out.println("'" + s2 + "'"); // => ''

    String s3 = removeTags(
        "<p><it>\"If debugging is the process of removing software bugs, then programming must be the process of putting them in.\"</it> Edsger Dijkstra</p>");
    System.out.println("'" + s3 + "'"); // => ''

    String s4 = removeTags(
        "\"<it>Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it.</it>\", Brian Kernighan"); // => ''
    System.out.println("'" + s4 + "'"); // => ''


  }

}
