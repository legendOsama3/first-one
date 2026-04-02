import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<? super T>> {
    protected BSTNode<T> root ;
    public BST() {
        this.root = null;
    }
    //ADDED METHOD FOR AVL TREES
    public BST(BSTNode<T> node) {
        root = node;
    }
    public void clear() {
        this.root = null;
    }
    public boolean isEmpty() {
        return this.root == null;
    }


    public void deleteByCopying(T key) {
        BSTNode<T> node, p = root, prev = null;

        while (p != null && !p.key.equals(key)) {  // find the node p
            prev = p;                           // with element el;
            if (key.compareTo(p.key) < 0)
                p = p.left;
            else p = p.right;
        }
        node = p;
        if (p != null && p.key.equals(key)) {
            if (node.right == null)             // node has no right child;
                node = node.left;
            else if (node.left == null)         // no left child for node;
                node = node.right;
            else {
                BSTNode<T> tmp = node.left;    // node has both children;
                BSTNode<T> previous = node;    // 1.
                while (tmp.right != null) {    // 2. find the rightmost
                    previous = tmp;            //    position in the
                    tmp = tmp.right;           //    left subtree of node;
                }
                node.key = tmp.key;              // 3. overwrite the reference
                //    to the element being deleted;
                if (previous == node)          // if node's left child's
                    previous.left  = tmp.left; // right subtree is null;
                else previous.right = tmp.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if (root != null)
            System.out.println("key " + key + " is not in the tree");
        else System.out.println("the tree is empty");
    }
    public void deleteByMerging(T el) {
        BSTNode<T> tmp, node, p = root, prev = null;
        while (p != null && !p.key.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.key) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.key.equals(el)) {
            if (node.right == null) // node has no right child: it's left
                node = node.left;  // child (if any) is attached to its parent;
            else if (node.left == null) // node has no left child: it's right
                node = node.right; // child is attached to its parent;
            else {                  // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right;      //    possible;
                tmp.right =        // 3. establish the link between
                        node.right;    //    the rightmost node of the left
                //    subtree and the right subtree;
                node = node.left;  // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node; // 5.
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }



    public void insert(T key) {
        if (this.root == null) {   // tree is empty;
            this.root = new BSTNode<>(key);
            return;
        }

        BSTNode<T> p = this.root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            if (key.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }

        if (key.compareTo(prev.key) < 0)
             prev.left  = new BSTNode<>(key);
        else if (key.compareTo(prev.key) > 0)
            prev.right = new BSTNode<>(key);
    }

    public void insertRecursive(T key) {
        root = insertRecursive(root,key);
    }

    private BSTNode<T> insertRecursive(BSTNode<T> p, T key) {
        if (p == null)
             p = new BSTNode<>(key);
        else if (key.compareTo(p.key) < 0)
             p.left  = insertRecursive(p.left,key);
        else if (key.compareTo(p.key) > 0)
            p.right = insertRecursive(p.right,key);
        return p;
    }

    public boolean find(T key) {
        BSTNode<T> p = this.root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp == 0)     //key.equals(p.key)
                return true;
            else if (cmp < 0 ) // key.compareTo(node.key) < 0
                p = p.left;
            else               // key.compareTo(node.key) > 0
                p = p.right;

        }
        return false;
    }

    public boolean findRecursive(T key) {
      return this.findRecursive(key, this.root);
    }

    private boolean findRecursive(T key, BSTNode<T> node) {
            if ( node == null) return false;
            int cmp =key.compareTo(node.key);
            if (cmp == 0 ) //key.equals(node.key)
                return true;
            else if (cmp < 0 ) // key.compareTo(node.key) < 0
                return findRecursive(key, node.left);
            else               // key.compareTo(node.key) > 0
                return findRecursive(key, node.right);

    }


    public void preorder() {
        preorder(root);
    }
    public void inorder() {
        inorder(root);
    }
    public void postorder() {
        postorder(root);
    }

    private void visit(BSTNode<T> p) {
        System.out.print(p.key + " ");
    }
    private void inorder(BSTNode<T> p) {
        if (p != null) {
             inorder(p.left);
             visit(p);
             inorder(p.right);
        }
    }
    private void preorder(BSTNode<T> p) {
        if (p != null) {
             visit(p);
             preorder(p.left);
             preorder(p.right);
        }
    }
    private void postorder(BSTNode<T> p) {
        if (p != null) {
             postorder(p.left);
             postorder(p.right);
             visit(p);
        }
    }

    public void levelOrderTraversal() {
        if(this.root==null)
            return;
        BSTNode<T> tmp ;
        Queue<BSTNode<T>> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            System.out.print(tmp.key + " ");

            if (tmp.left != null)
                queue.add(tmp.left);

            if (tmp.right != null)
                queue.add(tmp.right);
        }
        System.out.println();
    }


    // Task 1
    // Write your code her
    public int countEven() {
        return countEven(root);
    }

    private int countEven(BSTNode<T> node) {

        if (node == null)
            return 0;

        if (((Integer)node.key) % 2 == 0)
            return 1 + countEven(node.left) + countEven(node.right);

        else
            return countEven(node.left) + countEven(node.right);
    }

    public int countLeave() {
        return countLeave(root);
    }

    private int countLeave(BSTNode<T> node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        return countLeave(node.left) + countLeave(node.right);
    }









    // Tasks 2
    // Write your code here
    public boolean isFullTree(){
        return isFullTree(root);
    }

    private boolean isFullTree(BSTNode<T> node) {
        if (node == null)
            return true;

        if (node.left == null && node.right == null)
            return true;

        if (node.left != null && node.right != null)
            return isFullTree(node.left) && isFullTree(node.right);

        return false;
    }












}

