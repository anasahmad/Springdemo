package com.example.demo;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private Library library = new Library();
    
    @RequestMapping({"/","/*","/*/*"})
    public String general()
    {
    	return "/book -> Displays all books <p>"
    			+ "/book?author=yourvalue -> Displays the book with this author (myvalue is the author) <p>"
    			+ "/bookpartial?name=yourvalue -> Displays the book with this partial title (myvalue is the author) <p>"
    			+ "/add?name=yourvalue1&author=yourvalue2 -> Adds a book and display all books (myvalue1 is the name, myvalue2 is the author)";
    }
    
    @RequestMapping("/book")
    public ArrayList<String> book(@RequestParam(value="author", defaultValue="none") String author) {
    	    	
    	if(author.equals("none"))
    	{
        	return library.getBooks();
    	}
    	
    	if(author.equals(""))
    	{
    		return null;
    	}
    	
    	else
    	{
    		return library.getBooks(author);
    	}
    	
    	
    }
    
    @RequestMapping("/bookpartial")
    public ArrayList<String> bookpartial(@RequestParam(value="name", defaultValue="none") String partial){
    	
    	return library.getBooksPartial(partial);
    }
    
    @RequestMapping("/add")
    public ArrayList<String> add(@RequestParam(value="name",defaultValue="") String name, @RequestParam(value="author",defaultValue="") String author)
    {
    	
    	if(!name.equals("") && !author.equals(""))
    		library.addBook(name, author);
    	
    	return library.getBooks();
    }

}
