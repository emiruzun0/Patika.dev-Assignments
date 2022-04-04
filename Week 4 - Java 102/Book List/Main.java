import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book("A", 500, "AX", "30.12.2005");
        Book book2 = new Book("B", 523, "BX", "08.02.1996");
        Book book3 = new Book("C", 256, "CX", "30.08.1998");
        Book book4 = new Book("D", 152, "DX", "30.10.1997");
        Book book5 = new Book("E", 155, "EX", "13.09.1993");
        Book book6 = new Book("F", 410, "FX", "23.01.1992");
        Book book7 = new Book("G", 896, "GX", "24.06.1996");
        Book book8 = new Book("H", 325, "HX", "14.08.1997");
        Book book9 = new Book("I", 254, "IX", "18.12.1789");
        Book book10 = new Book("J", 125, "JX", "29.05.1853");

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);
        bookList.add(book6);
        bookList.add(book7);
        bookList.add(book8);
        bookList.add(book9);
        bookList.add(book10);

        HashMap<String, String> bookWriterMatch = new HashMap<>();
        bookList.forEach(book -> bookWriterMatch.put(book.getName(), book.getWriterName()));
        bookWriterMatch.keySet().forEach(name -> System.out.println(name + " " + bookWriterMatch.get(name)));

        ArrayList<Book> bookListFiltered = new ArrayList<>();
        bookList.stream().filter(book -> book.getPageNum() > 100).forEach(bookListFiltered::add);
        bookListFiltered.forEach(book -> System.out.println(book.getName()));

    }
}
