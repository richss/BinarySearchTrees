/**
 * @author Richard S. Stansbury
 * @date 10/29/2017
 * @Description:  This is an implementation of a binary tree node.
 */
public class BST<K extends Comparable<K>, T> {

    BTNode<K, T> root;
    int size;

    /**
     * Default constructor.  Creates an empty tree.
     */
    public BST() {
        this.root = null;
        this.size = 0;
    }


    /**
     * Inserts a tree node into the tree as a leaf in the appropriate
     * location within the tree.
     * @param key - Key of new node
     * @param info - Info of new node.
     */
    public void insert(K key, T info) {

        size++;

        //Special case - empty tree
        if (root == null) {
            root = new BTNode<K,T>(key, info);
            return;
        }

        //Default case - add new node

        //References to track traversal
        BTNode <K, T> prev = null;
        BTNode <K, T> cur = root;

        //Traverse list moving left or right based upon
        // new key's value relative to each node's key.
        while (cur != null) {

            prev = cur;

            //If key's value is less than cur's key, traverse left.
            if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            }
            //Otherwise, traverse right.
            else {
                cur = cur.right;
            }
        }

        //Insert new key relative to prev (i.e. the last node
        // visited in the traversal
        if (key.compareTo(prev.key) < 0) {
            prev.left = new BTNode<K, T>(key, info);
        }
        else {
            prev.right = new BTNode<K, T>(key, info);
        }
    }

    /**
     * Returns the info of the node with the target key in the tree.
     * Null if not found
     * @param key - target node's key
     * @return info of target node, or null if not found
     */
    public T search(K key) {

        return search(key, root);
    }

    /**
     * Recursive method that searches for a key within a subtree
     * defined by cur as its root.
     *
     * @param key - traget node's key
     * @param cur - current root of subtree being searched.
     * @return info of target node, or null if not found.
     */
    private T search(K key, BTNode<K, T> cur) {
        if (cur == null) return null;

        if (key.compareTo(cur.key) == 0)
            return cur.info;
        else if (key.compareTo(cur.key) < 0)
            return search(key, cur.left);
        else
            return search(key, cur.right);
    }

    /**
     * @return Info for node with minimum key in BST
     */
    public T findMin() {
        BTNode<K,T> node = findMinNode(root);
        if (node == null)
            return null;
        else
            return node.info;
    }

    /**
     * Returns the node with minimum key value in the subtree
     * with cur as root.
     * @param cur - root of subtree
     * @return node with minimum key value.
     */
    private BTNode<K,T> findMinNode(BTNode<K, T> cur) {
        if (cur == null) return null;

        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    /**
     *
     * @return Info for node with the maximum key in BST
     */
    public T findMax() {
        BTNode<K,T> node = findMaxNode(root);
        if (node == null) return null;
        else
            return node.info;
    }

    /**
     * Returns the node with maximum key value in the subtree
     * with cur as root.
     * @param cur - root of subtree
     * @return node with maximum key value.
     */
    public BTNode<K,T> findMaxNode(BTNode<K,T> cur) {

        if (cur == null) return null;

        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    /**
     * Performs an inorder traversal of the free.
     */
    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    /**
     * Performs an inorder traversal of the tree given
     * current node as root of subtree being traversed.
     * @param node - root of current subtree.
     */
    private void inOrderTraverse(BTNode<K,T> node) {
        //Base case
        if (node == null) return;

        //Progress case
        inOrderTraverse(node.left);
        visit(node);
        inOrderTraverse(node.right);
    }

    /**
     * Performs an preorder traversal of the free.
     */
    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    /**
     * Performs an preorder traversal of the tree given
     * current node as root of subtree being traversed.
     * @param node - root of current subtree.
     */
    private void preOrderTraverse(BTNode<K,T> node) {
        //Base Case
        if (node == null) return;

        visit(node);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    /**
     * Performs an postorder traversal of the free.
     */
    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    /**
     * Performs an postorder traversal of the tree given
     * current node as root of subtree being traversed.
     * @param node - root of current subtree.
     */
    private void postOrderTraverse(BTNode<K,T> node) {
        //Base Case
        if (node == null) return;


        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        visit(node);
    }

    public void breadthTraverse()
    {
        Queue<BTNode<K,T>> queue = new ArrayQueue<>(size);
        queue.enqueue(root);

        BTNode<K,T> cur;
        while (!queue.isEmpty()) {
            cur = queue.dequeue();

            if (cur != null) {
                visit(cur);
                queue.enqueue(cur.left);
                queue.enqueue(cur.right);
            }
        }
    }

    /**
     * @return size of tree
     */
    public int getSize() {
        return size;
    }


    /**
     *
     * @return height of tree
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * Recursive private method to determine the height of a subtree given cur.
     * @param cur - root of current subtree
     * @return height of tree
     */
    private int getHeight(BTNode<K,T> cur) {
        if (cur == null) return 0;

        int leftHeight = getHeight(cur.left);
        int rightHeight = getHeight(cur.right);

        if (leftHeight > rightHeight)
            return 1 + leftHeight;
        else
            return 1 + rightHeight;
    }


    /**
     * Determines if tree is full or not.
     * @return true if balanced, false otherwise
     */
    public boolean isFull() {
        if (root == null) return false;
        else
            return isFull(root);
    }

    /**
     * Determines if subtree with cur as root is full.
     * @param cur - root of subtree
     * @return true if full, false otherwise.
     */
    private boolean isFull(BTNode<K,T> cur) {

        if (cur.left == null && (cur.right == null))
            return true;

        else if (cur.left == null || (cur.right == null))
            return false;

        else
            return isFull(cur.left) && isFull(cur.right);
    }

    private int getBalanceFactor(BTNode<K,T> cur)
    {
        return getHeight(cur.right) - getHeight(cur.left);
    }


    public boolean isBalanced() {
            return isBalanced(root);
    }

    private boolean isBalanced(BTNode<K,T> cur) {

        if (cur == null)
            return true;

        if (Math.abs(getBalanceFactor(cur)) <= 1) {
            return isBalanced(cur.left) && isBalanced(cur.right);
        } else {
            return false;
        }
    }

    /**
     * Performs a delete using deleteByCopy operations if deleted node has two children.
     * @param key - key of node we wish to delete
     */
    public void deleteNodeByCopy(K key) {

        //////////////////////
        //Find Node
        BTNode<K,T> prev=null, cur = root;
        while (cur != null && !cur.key.equals(key)) {
            prev = cur;
            if (key.compareTo(cur.key) <= 0)
                cur = cur.left;
            else
                cur = cur.right;
        }

        if (cur == null) return;

        //Decrement size since deletion is now guaranteed
        size--;

        //////////////////////
        //  Handle Copy from Immediate Predecessor

        BTNode<K,T> node = cur;

        //No left child
        if (cur.left == null)
            node = cur.right;

            //No right child
        else if (cur.right == null)
            node = cur.left;

            //If both children - then delete by copy
        else {
            //Find Right Most of Left Subtree

            BTNode<K,T> tmp = cur.left,
                    previous = cur;

            while(tmp.right != null) {
                previous = tmp;
                tmp = tmp.right;
            }

            //Copy value into node to be deleted.
            cur.key = tmp.key;
            cur.info = tmp.info;

            //Delete copied node
            if (previous == cur)
                previous.left = tmp.left;
            else previous.right = tmp.left;
        }


        //Update the appropriate "parent" node of the deleted
        if (cur == root)
            root = node;
        else if (cur == prev.left)
            prev.left = node;

        else
            prev.right = node;

    }


    /**
     * Performs a delete using deleteByMerge operations if deleted node has two children.
     * @param key - key of node we wish to delete
     */
    public void deleteNodeByMerge(K key) {

        //////////////////////
        //Find Node
        BTNode<K,T> prev=null, cur = root;
        while (cur != null && !cur.key.equals(key)) {
            prev = cur;
            if (key.compareTo(cur.key) <= 0)
                cur = cur.left;
            else
                cur = cur.right;
        }

        if (cur == null) return;

        //Decrement size since deletion is now guaranteed
        size--;

        //////////////////////
        //  Handle Delete

        BTNode<K,T> node = cur;

        //No left child
        if (cur.left == null)
            node = cur.right;

            //No right child
        else if (cur.right == null)
            node = cur.left;

            //If both children - then delete by copy
        else {
            //Find Right Most of Left Subtree

            BTNode<K,T> tmp = cur.left,
                    previous = cur;

            while(tmp.right != null) {
                previous = tmp;
                tmp = tmp.right;
            }
            //At this point tmp is our immediate predecessor.
            //We need to move the deleted node's RST as temp's RST
            tmp.right = cur.right;

            node = cur.left;
        }


        //Update the parent node to reference the appropriate child
        if (cur == root)
            root = node;
        else if (cur == prev.left)
            prev.left = node;

        else
            prev.right = node;

    }

    /**
     * @return number of nodes in tree.
     */
     int getCount()
    {
        return getCount(root);
    }

    /**
     *
     * @param cur - root of current subtree
     * @return number of nodes in subtree
     */
    private int getCount(BTNode<K,T> cur) {
        if (cur == null) return 0;
        else
            return 1 + getCount(cur.left) + getCount(cur.right);
    }

    /**
     * A method that "visits" a node to do some operation
     * for this example it only prints the node.
     * @param node - node to visit.
     */
    public void visit(BTNode<K,T> node) {
        System.out.print(node.info + " ");
    }


    /**
     *
     * @return array of all nodes from an inoder traversal
     */
    public BTNode<K,T> [] getTreeData() {
        BTNode<K,T> [] data = new BTNode[size];
        getTreeData(root, data, 0);
        return data;
    }

    /**
     * Generates an array of all nodes recursively using inorder traversal
     * @param cur - current node
     * @param data - data array
     * @param index - index for next insert in array
     * @return updated index for next insert in array
     */
    private  int getTreeData(BTNode<K,T> cur, BTNode<K,T> [] data, int index)
    {
        //Base case
        if (cur == null) return index;

        //Inorder Traversal recursion
        index = getTreeData(cur.left, data, index);
        data[index] = cur;
        index++;
        index = getTreeData(cur.right, data, index);

        return index;
    }

    /**
     * Rebalances the tree kickoff function
     */
    public void rebalance() {

        //Get data in sorted array
        BTNode<K,T> [] data = getTreeData();

        //Reset the tree
        root = null;

        //Rebalance method
        rebalance(data, 0, size-1);
    }

    /**
     * Recursive method to rebalance an array of tree node data.
     * @param data - sorted array of tree data
     * @param first - first index of subarray
     * @param last - last index of subarray
     */
    private void rebalance(BTNode<K,T> [] data, int first, int last) {
        if (first <= last) {
            int middle = (int) (first + last) / 2;
            insert(data[middle].key, data[middle].info);
            rebalance(data, first, middle - 1);
            rebalance(data, middle+1, last);

        }
    }




    public static void main(String [] args) {

        Integer [] keys = {8, 6, 7, 5, 3, 0, 9, 42};
        Integer [] info = {8, 6, 7, 5, 3, 0, 9, 42};

        /**
         *             8
         *       6           9
         *     5   7           42
         *   3
         * 0
         */

        BST<Integer, Integer> tree = new BST();

        for (int i=0; i < keys.length; i++) {
            tree.insert(keys[i], info[i]);
        }

        System.out.println("\nIn-order: ");
        tree.inOrderTraverse();

        System.out.println("\nPost-order: ");
        tree.preOrderTraverse();

        System.out.println("\nPost-order: ");
        tree.postOrderTraverse();

        System.out.println("\nBreadth: ");
        tree.breadthTraverse();

        System.out.println("\nSearch Tests:");
        System.out.println("Search(42) = " + tree.search(42));
        System.out.println("Search(9) = " + tree.search(9));
        System.out.println("Search(17) = " + tree.search(17));

        System.out.println("\nfindMin test");
        System.out.println("Min =" + tree.findMin());

        System.out.println("\nfindMax test");
        System.out.println("Max =" + tree.findMax());

        System.out.println("\nHeight Test:");
        System.out.println("Height = " + tree.getHeight());

        System.out.println("\nisFull Test:");
        System.out.println("isFull = " + tree.isFull());

        System.out.println("\nisBalanced Test:");
        System.out.println("isBalanced = " + tree.isBalanced());

        System.out.println("\nDelete by Copy");
        /**
         *             7
         *       6           9
         *     5               42
         *   3
         * 0
         */

        tree.deleteNodeByCopy(8);
        tree.breadthTraverse();


        System.out.println("\nDelete by Merge");
        /**
         *       6
         *     5     9
         *   3         42
         * 0
         */
        tree.deleteNodeByMerge(7);
        tree.breadthTraverse();

        System.out.println("\nCount Test");
        System.out.println("Size = " + tree.getSize());
        System.out.println("Size = " + tree.getCount());

        System.out.println("\nData Export Test");
        BTNode<Integer,Integer> [] data =         tree.getTreeData();

        for (int i=0; i < data.length; i++ ) {
            System.out.print(data[i].getKey() + ",");
        }


        System.out.println("\nRE-Balance Test:");
        System.out.println("isBalanced = " + tree.isBalanced());
        tree.rebalance();
        System.out.println("isBalanced = " + tree.isBalanced());
        tree.inOrderTraverse();

    }

}
