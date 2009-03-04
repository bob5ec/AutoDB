package bz.asd.autodb.data;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lars
 */
public class GroupTreeTest extends TestCase {

    public GroupTreeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class GroupTree.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        List<Groupable<Model>> elements = createModels(2);

        int[] order = new int[1];
        order[0] = Model.DRUCK;

        int groupLevel = 0;
        
        GroupTree result = GroupTree.create(elements, order, groupLevel);
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)result.getRoot();
        Enumeration<DefaultMutableTreeNode> dfEnum = root.depthFirstEnumeration();
        assertTrue(dfEnum.hasMoreElements());
        DefaultMutableTreeNode node = dfEnum.nextElement();
        Object[] path = node.getUserObjectPath();
        
        path = node.getUserObjectPath();
        assertEquals(2, path.length);
        assertEquals(path[0], null);
        assertEquals("Test 0", ((Model)path[1]).getDruck());
        //assertEquals("Test 1", ((Model)path[1]).getDruck());

        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(2, path.length);
        assertEquals(path[0], null);
        assertEquals("Test 1", ((Model)path[1]).getDruck());

        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(1, path.length);
        assertEquals(path[0], null);
    }

    @Test
    public void testCreateBig() {
        System.out.println("createBig");
        List<Groupable<Model>> elements = createModels(5);
        ((Model)elements.get(0)).setArt("1");
        ((Model)elements.get(1)).setArt("1");
        ((Model)elements.get(2)).setArt("1");
        ((Model)elements.get(3)).setArt("0");
        ((Model)elements.get(4)).setArt("0");

        int[] order = new int[1];
        order[0] = Model.ART;

        int groupLevel = 1;

        GroupTree result = GroupTree.create(elements, order, groupLevel);
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)result.getRoot();
        //Enumeration<DefaultMutableTreeNode> dfEnum = root.depthFirstEnumeration();
        //assertTrue(dfEnum.hasMoreElements());
        //DefaultMutableTreeNode node = dfEnum.nextElement();
        assertEquals(2, root.getChildCount());
        assertEquals("0", root.getChildAt(0).toString());
        assertEquals("1", root.getChildAt(1).toString());

        DefaultMutableTreeNode curNode = (DefaultMutableTreeNode) root.getChildAt(0);
        assertEquals(2, curNode.getChildCount());
        Model m = (Model)((DefaultMutableTreeNode)curNode.getChildAt(0)).getUserObject();
        assertEquals("Test 3", m.getDruck());
        m = (Model)((DefaultMutableTreeNode)curNode.getChildAt(1)).getUserObject();
        assertEquals("Test 4", m.getDruck());

        /*Object[] path = node.getUserObjectPath();

        // ART - ...DRUCK...

        // 0 "Test3"
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(3, path.length);
        assertEquals(path[0], null);
        assertEquals("0", ((Group)path[1]).toString());
        assertEquals("Test 3", ((Model)path[2]).getDruck());

        // 0 "Test4"
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(3, path.length);
        assertEquals(path[0], null);
        assertEquals("0", ((Group)path[1]).toString());
        assertEquals("Test 4", ((Model)path[2]).getDruck());

        // 0
        path = node.getUserObjectPath();
        assertEquals(2, path.length);
        assertEquals(path[0], null);
        assertEquals("0", ((Group)path[1]).toString());


        // 1 "Test0"
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(3, path.length);
        assertEquals(path[0], null);
        assertEquals("1", ((Group)path[1]).toString());
        assertEquals("Test 0", ((Model)path[1]).getDruck());

        // 1 "Test1"
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(3, path.length);
        assertEquals(path[0], null);
        assertEquals("1", ((Group)path[1]).toString());
        assertEquals("Test 1", ((Model)path[1]).getDruck());


        // 1 "Test2"
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(3, path.length);
        assertEquals(path[0], null);
        assertEquals("1", ((Group)path[1]).toString());
        assertEquals("Test 2", ((Model)path[1]).getDruck());

        // 1
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(2, path.length);
        assertEquals(path[0], null);
        assertEquals("1", ((Group)path[1]).toString());
        
        assertTrue(dfEnum.hasMoreElements());
        node = dfEnum.nextElement();
        path = node.getUserObjectPath();
        assertEquals(1, path.length);
        assertEquals(path[0], null);
        */
    }

    private List<Groupable<Model>> createModels(int count) {
        List<Groupable<Model>> models = new LinkedList<Groupable<Model>>();

        for(int i=0; i<count; i++) {
            Model m = createDummyModel();
            m.setDruck("Test "+i);
            models.add(m);
        }
        
        return models;
    }

    private Model createDummyModel() {
        Model m = new Model() {
            @Override
            protected void setHasChanged(boolean hasChanged) {
                System.out.println("Dummy impl without backend");
            }
        };

        return m;
    }

}