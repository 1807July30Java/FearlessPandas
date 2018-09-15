package com.revature.repository;

import com.revature.domain.Auction;
import com.revature.domain.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository(value = "bookRepository")
@Transactional
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AuctionRepository auctionRepository;

    public Book getBookById(int bookId) {
        Session s = sessionFactory.getCurrentSession();
        Book book = null;
        book = (Book) s.get(Book.class, bookId);
        return book;
    }

    public List<Book> getBooksByInfo(String title, String author, String publisher) {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.getNamedQuery("getBooksByGeneralInfo");
        q.setString("title", title);
        q.setString("author", author);
        q.setString("publisher", publisher);
        return q.list();
    }

    public List<Auction> getAuctionBooksByInfo(int isbn, String title, String author, String publisher, int minimumPrice, int buyItNow, Date endDate) {
        Session s = sessionFactory.getCurrentSession();
        List<Auction> auction = new ArrayList<>();
        Query q = s.getNamedQuery("getBooksByGeneralInfo");
        q.setString("title", '%' + title + '%');
        q.setString("author", '%' + author + '%');
        q.setString("publisher", '%' + publisher + '%');
        q.setString("isbn", '%' + Integer.toString(isbn) + '%');
        List<Book> books = new ArrayList<>(q.list());
        System.out.println("-------------------------------------------------------------" + books.size());

        for (Book book : books) {
            System.out.println("************************************ " + book.getBookId());
            auction.addAll(auctionRepository.getAuctionSearch(book.getBookId(), minimumPrice, buyItNow, endDate));
        }

        return auction;
    }

    public List<Book> getBooksByArgs(List<String> args) {
        Session s = sessionFactory.getCurrentSession();
        List<Book> ret = new ArrayList<>();
        Query q = s.getNamedQuery("getBookByinfo");
        Iterator<String> i = (Iterator<String>) args.iterator();
        while (i.hasNext()) {
            String a0 = i.next();
            Iterator j = i;
            while (j.hasNext()) {
                String a1 = (String) j.next();
                Iterator k = j;
                while (k.hasNext()) {
                    q.setString("title", (String) k.next());
                    q.setString("author", a1);
                    q.setString("publisher", a0);
                    ret.addAll(q.list());
                    q.setString("title", (String) k.next());
                    q.setString("author", a0);
                    q.setString("publisher", a1);
                    ret.addAll(q.list());
                    q.setString("title", a1);
                    q.setString("author", (String) k.next());
                    q.setString("publisher", a0);
                    ret.addAll(q.list());
                    q.setString("title", a1);
                    q.setString("author", a0);
                    q.setString("publisher", (String) k.next());
                    ret.addAll(q.list());
                    q.setString("title", a0);
                    q.setString("author", (String) k.next());
                    q.setString("publisher", a1);
                    ret.addAll(q.list());
                }
            }
        }
        return ret;
    }

}
