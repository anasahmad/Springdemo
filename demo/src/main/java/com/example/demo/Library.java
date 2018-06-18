package com.example.demo;

import java.util.ArrayList;

public class Library{

	ArrayList<Book> Books = new ArrayList<Book>();
	ArrayList<String> BookNames = new ArrayList<String>(); //names of the books

	public Library() {
		
	}
	
	
	public ArrayList<String> getBooks()
	{
		return BookNames;
	}

	
	public ArrayList<String> getBooks(String author)
	{
		ArrayList<String> temp = new ArrayList<String>();

		for(Book Book: Books)
		{
			if(Book.getAuthor().equals(author))
			{
				temp.add(Book.getName());
			}
		}

		return temp;
	}

	public ArrayList<String> getBooksPartial(String partialTitle)
	{
		ArrayList<String> ret = new ArrayList<String>();
        int p = partialTitle.length();
        
		for (Book Book: Books)
		{
            int n = Book.getName().length();
                        
            for(int i = 0; i < n - p + 1; i++)
            {
            	if(partialTitle.toLowerCase().charAt(0) == Book.getName().toLowerCase().charAt(i))
            	{
                    String temp = Book.getName().substring(i, i + p).toLowerCase();
                    if(temp.equals(partialTitle.toLowerCase()))
                    {
                    	ret.add(Book.getName());
                    	continue;
                    }
            	}
            }
		}
		return ret;
	}
	
	public void addBook(String name, String Author)
	{
		Books.add(new Book(Books.size(), name, Author));
		BookNames.add(name);
	}



}
