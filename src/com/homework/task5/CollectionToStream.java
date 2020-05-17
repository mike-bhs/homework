package com.homework.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

//Task 5. (20 points) Collection to stream
//        1. Create the following classes:
//        a. Author with fields:
//         String name
//         short age
//         List<Book> books
//        b. Book with fields
//         String title
//         List<Author> authors
//         int numberOfPages
//        2. Create two arrays: Author[] authors and Book[] books. Their elements must use
//        elements from the neighboring array.
//        3. Create a stream of books and then:
//        I. check if some/all book(s) have more than 200 pages;
//        II. find the books with max and min number of pages;
//        III. filter books with only single author;
//        IV. sort the books by number of pages/title;
//        V. get list of all titles;
//        VI. print them using forEach method;
//        VII. get distinct list of all authors
//        4. Use peek method for debugging intermediate streams during execution the previous task.
//        5. Use parallel stream with subtask #3.
//        6. Analyze with mentor results of previous subtask execution, explain him the difference
//        between stream and parallel stream.
//        7. Use the Optional type for determining the title of the biggest book of some author.
public class CollectionToStream {
    public static void execute() {
        Author authorAdam = new Author("Adam", (short) 32);
        Author authorJohn = new Author("John", (short) 43);
        Author authorBruce = new Author("Bruce", (short) 50);
        Author authorJorge = new Author("Jorge", (short) 46);

        Book storyBook = new Book("Wonderful Story", 283);
        storyBook.addAuthors(new Author[]{authorAdam, authorBruce});

        Book horrorBook = new Book("Horror Story", 450);
        horrorBook.addAuthors(new Author[]{authorJohn, authorJorge});

        Book adventureBook = new Book("Funny Adventures", 180);
        adventureBook.addAuthors(new Author[]{authorBruce});

        Book detectiveBook = new Book("Detective Story", 320);
        detectiveBook.addAuthors(new Author[]{authorJorge, authorJohn});

        authorAdam.addBooks(new Book[]{storyBook});
        authorJohn.addBooks(new Book[]{horrorBook, detectiveBook});
        authorBruce.addBooks(new Book[]{storyBook, adventureBook});
        authorJorge.addBooks(new Book[]{detectiveBook, horrorBook});

        Book[] allBooks = new Book[]{storyBook, horrorBook, adventureBook, detectiveBook};
        Author[] allAuthors = new Author[]{authorAdam, authorBruce, authorJohn, authorJorge};

//        Stream.of(allBooks).
        System.out.println("Following books have more than 200 pages:");
        Stream.of(allBooks)
                .filter((b) -> b.getNumberOfPages() > 200)
                .map(Book::getTitle)
                .forEach(System.out::println);

        System.out.println("Following books have one author:");
        Stream.of(allBooks)
                .filter((b) -> b.getAuthors().size() == 1)
                .map(Book::getTitle)
                .forEach(System.out::println);

        System.out.println("Books sorted by number of pages");
        Stream.of(allBooks)
                .sorted(new BookPageComparator())
                .forEach((b) -> System.out.println(b.getTitle() + " " + b.getNumberOfPages()));

        System.out.println("Books sorted by number of pages (PARALLEL)");
        Stream.of(allBooks)
                .parallel()
                .sorted(new BookPageComparator())
                .forEach((b) -> System.out.println(b.getTitle() + " " + b.getNumberOfPages()));

        System.out.println("Distinct list of authors:");
        Stream.of(allBooks)
                .flatMap((b) -> Stream.of(b.getAuthors().toArray()))
                .distinct()
                .forEach((el) -> System.out.println(((Author) el).getName()));
    }
}

class Author {
    private final String name;
    private final short age;
    private List<Book> books;

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBooks(Book[] books) {
        this.books.addAll(Arrays.asList(books));
    }
}

class Book {
    private final String title;
    private final int numberOfPages;
    private List<Author> authors;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthors(Author[] authors) {
        this.authors.addAll(Arrays.asList(authors));
    }
}

class BookPageComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getNumberOfPages() - b2.getNumberOfPages();
    }
}
