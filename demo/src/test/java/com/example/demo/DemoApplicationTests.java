package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
    private BookController controller;

		
	@Test
	public void contextLoads() {
        assertThat(controller).isNotNull();
	}

	
	@Test
	public void addingBook()
	{
		Library lib = new Library();
		
		lib.addBook("harry", "rowling");
		
        assertTrue(lib.getBooks().contains("harry"));
        
        lib.closedb();
	}
	
	
	@Test
	public void retrievingBookbyAuthor()
	{
		Library lib = new Library();
		lib.addBook("harry", "rowling");
				
        assertTrue(lib.getBooks("rowling").contains("harry"));
        
       lib.closedb();
	}
	
	@Test
	public void retrievingBookbyAuthor2()
	{
		Library lib = new Library();
		lib.addBook("harry", "rowling");
				
        assertFalse(lib.getBooks("rowlings").contains("harry"));
        
        lib.closedb();
	}
	
	@Test
	public void retrievingBookbyPartialName1()
	{
		Library lib = new Library();
		lib.addBook("harry", "rowling");		
		
        assertTrue(lib.getBooksPartial("ha").contains("harry"));
        
        lib.closedb();
	}
	
	@Test
	public void retrievingBookbyPartialName2()
	{
		Library lib = new Library();
				
        assertTrue(lib.getBooksPartial("arr").contains("harry"));
        
        lib.closedb();
	}
	
	@Test
	public void retrievingBookbyPartialName3()
	{
		Library lib = new Library();
				
        assertTrue(lib.getBooksPartial("ry").contains("harry"));
        
        lib.closedb();
	}
    
}
