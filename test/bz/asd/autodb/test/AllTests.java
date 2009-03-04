package bz.asd.autodb.test;

import bz.asd.autodb.data.GroupTreeTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for bz.asd.autodb");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestDb4o.class);
        suite.addTestSuite(GroupTreeTest.class);
		//$JUnit-END$
		return suite;
	}

}
