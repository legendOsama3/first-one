public class AVLTree<T extends Comparable<? super T>> extends BST<T> {

    protected int height;

    public AVLTree() {
        super();
        height = -1;
    }

    public AVLTree(BSTNode<T> root) {
        super(root);
        height = -1;
    }



    private AVLTree<T> getLeftAVL() {
        AVLTree<T> leftsubtree = new AVLTree<T>(root.left);
        return leftsubtree;
    }

    private AVLTree<T> getRightAVL() {
        AVLTree<T> rightsubtree = new AVLTree<T>(root.right);
        return rightsubtree;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BSTNode<T> node) {
        if(node == null)
            return -1;
        else
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    protected int getBalanceFactor() {
        if(isEmpty())
            return 0;
        else
            return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }

    public void insertAVL(T key)  {
        super.insert(key);
        this.balance();
    }

    public void deleteAVL(T key) {
        if(find(key)){
            // Delete the chosen element
            deleteByCopying(key);
            // Balance the tree after deletion
            balance();
        }
        // If it is not in the tree then print the proper error message
        else
            System.out.println(key + " is not in the tree");
    }

    protected void balance()
    {
        if(!isEmpty())
        {
            getLeftAVL().balance();
            getRightAVL().balance();

            adjustHeight();

            int balanceFactor = getBalanceFactor();

            if(balanceFactor == -2) {
                System.out.println("Balancing node with key: "+root.key);
                if(getLeftAVL().getBalanceFactor() < 0)
                    rotateRight();
                else
                    rotateLeftRight();
            }

            else if(balanceFactor == 2) {
                System.out.println("Balancing node with key: "+root.key);
                if(getRightAVL().getBalanceFactor() > 0)
                    rotateLeft();
                else
                    rotateRightLeft();
            }
        }
    }

    protected void adjustHeight()
    {
        if(isEmpty())
            height = -1;
        else
            height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());
    }

    protected void rotateRight() {
        System.out.println("RIGHT ROTATION");

        // Set up a node called tempNode and let it equal to the element right to the root
        BSTNode<T> tempNode = root.right;
        // Let the node to the right of the root be equal to the node to the left of the root
        root.right = root.left;
        // Then let the node to the left of the root be equal to the left child of the node to the right of the root
        root.left = root.right.left;
        // Let the left child of the node to the right of the root be equal to the right child of the node to the right of the root
        root.right.left = root.right.right;
        // Finally let the right child of the node to the right of the root be equal to the temp node
        root.right.right = tempNode;

        // Define a variable called val and let it be equal to the root's element
        T val = (T) root.key;
        // Let the element of the root be equal to the element of its right child
        root.key = root.right.key;
        // Let the element of the root's right child be equal to the variable val
        root.right.key = val;

        // Adjust the height of the right AVL
        getRightAVL().adjustHeight();
        // Adjust the height of the whole tree
        adjustHeight();
    }

    protected void rotateRightLeft() {
        System.out.println("Double Rotation...");
        getRightAVL().rotateRight();
        getRightAVL().adjustHeight();
        this.rotateLeft();
        this.adjustHeight();
    }

    // Task 3
    // Write your code here
    // Task 3
    protected void rotateLeft() {
        System.out.println("LEFT ROTATION");

        BSTNode<T> tempNode = root.left;
        root.left = root.right;
        root.right = root.left.right;
        root.left.right = root.left.left;
        root.left.left = tempNode;

        T val = root.key;
        root.key = root.left.key;
        root.left.key = val;

        getLeftAVL().adjustHeight();
        adjustHeight();
    }

    protected void rotateLeftRight()  {
        System.out.println("Double Rotation...");
        getLeftAVL().rotateLeft();
        getLeftAVL().adjustHeight();
        this.rotateRight();
        this.adjustHeight();
    }



}