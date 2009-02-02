package bz.asd.autodb.test;

import bz.asd.autodb.data.db4o.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bz.asd.autodb.data.Database;
import bz.asd.autodb.data.Model;

public class TestDb4o extends TestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		(new File("test")).delete();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		(new File("test")).delete();
	}
	
	public void tearDown() {
		(new File("test")).delete();
	}

	@Test
	public void testOpenClose() {
		Database db = null;
		try {
			db = new Db4oDatabase("test");
			db.open();
			db.close();
		} catch (Exception e) { 
			fail(e.toString());
		}
		assertNotNull(db);
	}

	@Test
	public void testCreateModel() {
		Database db = null;
		try {
			db = new Db4oDatabase("test");
			db.open();
			
			Model model = db.createModel();
			assertNotNull(model);
			
			db.close();
		} catch (Exception e) { 
			fail(e.toString());
		}
		assertNotNull(db);
	}

	@Test
	public void testDeleteModel() {
		Database db = null;
		try {
			db = new Db4oDatabase("test");
			db.open();
			
			// delete without saving to disk
			Model model = db.createModel();
			model.setAchsfolge("achsfolge");
			Collection ttt = db.getModels();
			
			assertTrue(db.getModels().size() == 1);
			
			db.deleteModel(model);
			assertTrue(db.getModels().size() == 0);
			
			db.save();
			db.close();
			db.open();
			assertTrue(db.getModels().size() == 0);
			
			// delete with saving to disk
			model = db.createModel();
			model.setAchsfolge("achsfolge2");
			
			assertTrue(db.getModels().size() == 1);
			
			db.save();
			db.close();
			db.open();
			
			assertTrue(db.getModels().size() == 1);
			
			
			Collection<Model> models = db.getModels();
			Iterator<Model> it = models.iterator();
			assertTrue(it.hasNext());
			
			db.deleteModel(it.next());
			assertTrue(db.getModels().size() == 0);
			
			db.save();
			db.close();
			db.open();
			int size = db.getModels().size();
			assertTrue(size == 0);
			db.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		assertNotNull(db);
	}

	@Test
	public void testSave() {
		Database db = null;
		try {
			db = new Db4oDatabase("test");
			db.open();

			Model model = db.createModel();
			model.setAchsfolge("achsfolge");

			assertTrue(db.getModels().size() == 1);
			
			Collection<Model> models = db.getModels();
			Iterator<Model> it = models.iterator();
			assertTrue(it.hasNext());
			
			assertEquals(it.next().getAchsfolge(), "achsfolge");
			
			// save
			db.save();
			db.save(); // should not fail
			db.close();
			db.open();
			
			models = db.getModels();
			it = models.iterator();
			assertTrue(it.hasNext());
			
			assertEquals(it.next().getAchsfolge(), "achsfolge");
			
			db.close();
		} catch (Exception e) { 
			fail(e.toString());
		}
		assertNotNull(db);

	}

}
