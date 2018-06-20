package com.example.demo;

import java.util.ArrayList;
import org.bson.types.ObjectId;
import com.mongodb.*;

public class Library{

	private MongoClient mongo;
	private DB db;
	private DBCollection dbcollection;
	private final String DBNAME = "demodb";
	private final String COLLECTIONNAME = "librarycollection";
	

	public DB getDB()
	{
		return db;
	}
	
	public DBCollection getDBCollection()
	{
		return dbcollection;
	}

	public Library() {
		try
		{
			mongo = new MongoClient();
			db = mongo.getDB(DBNAME);
			dbcollection = db.getCollection(COLLECTIONNAME);
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<String> getBooks()
	{		
		ArrayList<String> temp = new ArrayList<String>();		
		
		BasicDBObject all = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject();
		
		fields.put("name", 1);
		
		DBCursor cursor = dbcollection.find(all, fields);
		
		while (cursor.hasNext()) {
			temp.add((String)cursor.next().get("name"));
		}

		return temp;
	}

	public ArrayList<String> getBooks(String author)
	{
		ArrayList<String> temp = new ArrayList<String>();		
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("author", author);
		
		DBCursor cursor = dbcollection.find(whereQuery);
		while(cursor.hasNext()) {
			temp.add((String)cursor.next().get("name"));
		}

		return temp;
	}

	public ArrayList<String> getBooksPartial(String partialTitle)
	{
		ArrayList<String> ret = new ArrayList<String>();
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name",
		        new BasicDBObject("$regex", partialTitle)
		        .append("$options", "i"));
		
		DBCursor cursor = dbcollection.find(whereQuery);
		while(cursor.hasNext()) {
			ret.add((String)cursor.next().get("name"));
		}

		return ret;
	}
	
	public void addBook(String name, String author)
	{
		addObjectToCollection(name, author);
	}
	
	public void addObjectToCollection(String name, String author)
	{		
		DBObject dbo = new BasicDBObject("_id", ObjectId.get()).append("name", name).append("author", author);
		dbcollection.insert(dbo);
	}
	
	public void closedb()
	{
		mongo.close();
	}



}
