package ch.makery.address.model;

import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class BookListWrapper
{
	private List<Book> books;

    @XmlElement(name = "book")
    public List<Book> getBooks()
    {
        return books;
    }

    public void setbooks(List<Book> books)
    {
        this.books = books;
    }
}
