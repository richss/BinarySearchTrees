/**
 * @author Richard S. Stansbury
 * @date 10/29/2017
 * @Description:  This is an implementation of a binary tree node.
 */

public class BTNode<K extends Comparable<K>,T>
    implements Comparable<BTNode<K, T>> {

    K key;
    T info;

    BTNode<K,T> left;
    BTNode<K,T> right;

    /**
     * Default constructor
     * @param key - key for the node
     * @param info - info stored in the node.
     */
    public BTNode(K key, T info) {
        this.key = key;
        this.info = info;
        this.left = null;
        this.right = null;
    }

    ///////////////////////////////////
    // GETTERS

    /**
     * @return key of the node of type K.
     */
    public K getKey() {
        return key;
    }

    /**
     * @return info of the node of type T
     */
    public T getInfo() {
        return info;
    }

    /**
     * @return root of left subtree
     */
    public BTNode<K,T> getLeft() {
        return left;
    }

    /**
     * @return root of right subtree
     */
    public BTNode<K,T> getRight() {
        return right;
    }


    ///////////////////////////////////
    // SETTERS

    /**
     * Updates the node's key.
     * @param key - new key value
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Updates the node's info
     * @param info - new info value
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Updates the node's left sub-tree
     * @param left - new left subtree root
     */
    public void setLeft(BTNode<K,T> left) {
        this.left = left;
    }

    /**
     * Updates the node's right sub-tree
     * @param right - new right subtree root
     */
    public void setRight(BTNode<K,T> right) {
        this.right = right;
    }

    ///////////////////////////////////
    // COMPARISON

    /**
     * Returns integer where:
     *   a. < 0 implies key < other.key
     *   b. > 0 implies key > other.key
     *   c. == 0 implies key == other.key
     * @param other
     * @return
     */
    public int compareTo(BTNode<K,T> other) {
        return key.compareTo(other.getKey());
    }

}
