package red.black.tree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestRedBlackTree {

    static Random rand = new Random();
    RedBlackTree<Integer, Integer> tree;

    public static <T> void swap(final T[] array, final int a, final int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static <T> void shuffle(T[] array) {
        for (int i = 0; i < array.length; ++i) {
            swap(array, i, i + rand.nextInt(array.length - i));
        }
    }
    
    @Before
    public void setUp() {
        tree = new RedBlackTree<Integer, Integer>();
    }
    
    @Test
    public void testLeftRotation() {
        RedBlackTree<Integer, Integer>.Node x = tree.new Node(), y = tree.new Node(), p = tree.new Node(),
                alpha = tree.new Node(), beta = tree.new Node(), gamma = tree.new Node();
        
        x.p = p;
        x.left = alpha;
        alpha.p = x;
        x.right = y;
        y.p = x;
        y.left = beta;
        beta.p = y;
        y.right = gamma;
        gamma.p = y;
        
        tree.leftRotate(x);
        
        assertTrue(y.p == p);
        assertTrue(gamma.p == y);
        assertTrue(y.right == gamma);
        assertTrue(y.left == x);
        assertTrue(x.p == y);
        assertTrue(x.right == beta);
        assertTrue(beta.p == x);
        assertTrue(x.left == alpha);
        assertTrue(alpha.p == x);
    }
    
    @Test
    public void testRightRotation() {
        RedBlackTree<Integer, Integer>.Node x = tree.new Node(), y = tree.new Node(), p = tree.new Node(),
                alpha = tree.new Node(), beta = tree.new Node(), gamma = tree.new Node();
        
        // notice that the assignments are the same as the assertions in the test for left rotation
        y.p = p;
        gamma.p = y;
        y.right = gamma;
        y.left = x;
        x.p = y;
        x.right = beta;
        beta.p = x;
        x.left = alpha;
        alpha.p = x;
        
        tree.rightRotate(y);
        
        // notice that the assertions are the same as the assignments in the test for left rotation
        assertTrue(x.p == p);
        assertTrue(x.left == alpha);
        assertTrue(alpha.p == x);
        assertTrue(x.right == y);
        assertTrue(y.p == x);
        assertTrue(y.left == beta);
        assertTrue(beta.p == y);
        assertTrue(y.right == gamma);
        assertTrue(gamma.p == y);
    }
    
    @Test
    public void testInsertion() {
        int iterations = 10000;
        
        Integer[] arr = new Integer[iterations];
        for (int i = 0; i < iterations; ++i) {
            arr[i] = i;
        }
        shuffle(arr);

        assertTrue(tree.isRedBlackTree());
        for (int i = 0; i < iterations; ++i) {
            tree.insert(arr[i], arr[i]);
            assertTrue(tree.isRedBlackTree());
            assertEquals(arr[i], tree.get(arr[i]));
        }
        assertTrue(tree.isRedBlackTree());
    }
    
}