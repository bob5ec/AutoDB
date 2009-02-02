package bz.asd.poc.db4o;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Test {

	public Test() {
		
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		//t.gen();
		
		byte[] in = new byte[100];
		try {
			System.in.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.get();
	}
	
	public void get() {

		ObjectContainer db=Db4o.openFile("performance");
		try {
			Query query = db.query();
			query.constrain(Modell.class);
			query.descend("a").orderAscending();
			query.descend("b").orderDescending();
			long t1 = System.currentTimeMillis();
			ObjectSet<Modell> result = query.execute();
			long t2 = System.currentTimeMillis();
			long diff = t2 - t1;
			//listResult(result);
			System.out.println("Time to query and sort with  SODA: "
					+ diff + " ms.");
			

		}
		finally {
			db.close(); 
		}


	}
	
	public void gen() {
		List<Modell> modelle = new LinkedList<Modell>();
		for(int i=0; i<10000; i++) {
			modelle.add(new Modell());
		}
		
		new File("performance").delete();
		ObjectContainer db=Db4o.openFile("performance");
		try {
			long t1 = System.currentTimeMillis();
			for (Modell m : modelle) {
				db.set(m);
			}
			long t2 = System.currentTimeMillis();
			long diff = t2 - t1;
			
			System.out.println("Time to insert: " +diff+ " ms.");
			

		}
		finally {
			db.close(); 
		}


	}

	
	public static void listResult(ObjectSet<Modell> res) {
		for (Modell m : res) {
			System.out.println(m.toString());
		}
		
	}

}
