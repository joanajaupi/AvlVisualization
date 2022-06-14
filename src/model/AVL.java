package model;

public class AVL<T extends Comparable<T>> {
    private AvlNode<T> root;

    public AvlNode<T> getRoot() {
        return this.root;
    }

    public AvlNode<T> search(AvlNode<T> root, T target)
    {
        // root is null or value present at root
        if (root==null || root.getValue().compareTo(target) == 0)
            return root;

        // target is greater than root's value
        else if (root.getValue().compareTo(target) < 0)
            return search(root.getRight(), target);

        // target is smaller than root's value
        else
            return search(root.getLeft(), target);
    }

    private int getHeight(AvlNode<T> node) {
        if (node == null) return -1;
        return node.getHeight();
    }

    public int findDepth(AvlNode<T> root, T target)
    {
        if (root == null)
            return -1;

        int distance = -1;

        // Check if x is current node=
        if (root.getValue().compareTo(target) == 0 ||
                (distance = findDepth(root.getLeft(), target)) >= 0 ||  //check if present in left subtree
                (distance = findDepth(root.getRight(), target)) >= 0)  //check if present in right subtree
            return distance + 1;  //return depth

        return distance;
    }

    private int getBalance(AvlNode<T> node) {
        if (node == null) return -1;
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private AvlNode<T> rightRotate(AvlNode<T> node) {
        // right rotation of a node
        AvlNode<T> parent = node.getParent();
        AvlNode<T> leftChild = node.getLeft();
        AvlNode<T> rightChildOfLeftChild = leftChild.getRight();
        leftChild.setRight(node);
        node.setParent(leftChild);
        node.setLeft(rightChildOfLeftChild);
        if (rightChildOfLeftChild != null) rightChildOfLeftChild.setParent(node);
        if (parent == null) {
            this.root = leftChild;
            leftChild.setParent(null);
        } // root node
        else {
            if (parent.getLeft() == node)
                parent.setLeft(leftChild);
            else
                parent.setRight(leftChild);
            leftChild.setParent(parent);
        }
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        leftChild.setHeight(1 + Math.max(getHeight(leftChild.getLeft()), getHeight(leftChild.getRight())));
        return leftChild;
    }

    private AvlNode<T> leftRotate(AvlNode<T> node) {
        // left rotation of a node
        AvlNode<T> parent = node.getParent();
        AvlNode<T> rightChild = node.getRight();
        AvlNode<T> leftChildOfRightChild = rightChild.getLeft();
        rightChild.setLeft(node);
        node.setParent(rightChild);
        node.setRight(leftChildOfRightChild);
        if (leftChildOfRightChild != null) leftChildOfRightChild.setParent(node);
        if (parent == null) {
            this.root = rightChild;

        } else {
            if (parent.getLeft() == node)
                parent.setLeft(rightChild);
            else
                parent.setRight(rightChild);
        }
        node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        rightChild.setHeight(1 + Math.max(getHeight(rightChild.getLeft()), getHeight(rightChild.getRight())));
        return rightChild;
    }

    public void insert(T key) {
        this.root = insert(key, root);
    }

    private AvlNode<T> insert(T key, AvlNode<T> subRoot) {
        if (subRoot == null)
            return new AvlNode<>(key);
        else if (subRoot.getValue().compareTo(key) > 0)
            subRoot.setLeft(insert(key, subRoot.getLeft()));
        else if (subRoot.getValue().compareTo(key) < 0)
            subRoot.setRight(insert(key, subRoot.getRight()));
        subRoot.setHeight(1 + Math.max(getHeight(subRoot.getLeft()), getHeight(subRoot.getRight())));
        int balanceFactor = getBalance(subRoot);
        if (balanceFactor > 1) {
            int res = subRoot.getLeft().getValue().compareTo(key);
            if (res > 0) return rightRotate(subRoot); // simple right rotation
            else if (res < 0) {
                // left right rotation
                subRoot.setLeft(leftRotate(subRoot.getLeft()));
                return rightRotate(subRoot);
            }
        } // left heavy
        else if (balanceFactor < -1) {
            int res = subRoot.getRight().getValue().compareTo(key);
            if (res > 0) {
                subRoot.setRight(rightRotate(subRoot.getRight()));
                return leftRotate(subRoot);
            } else return leftRotate(subRoot);
        } // right heavy
        return subRoot;
    }

    public void delete(T key) {
        this.root = delete(key, root);
    }

    private AvlNode<T> minValueNode(AvlNode<T> node) {
        AvlNode<T> temp;
        for (temp = node; temp.getLeft() != null; temp = temp.getLeft()) ;

        return temp;
    }

    private AvlNode<T> delete(T key, AvlNode<T> subRoot) {
        if (subRoot == null)
            return subRoot;

        else if (subRoot.getValue().compareTo(key) > 0)
            subRoot.setLeft(delete(key, subRoot.getLeft()));

        else if (subRoot.getValue().compareTo(key) < 0)
            subRoot.setRight(delete(key, subRoot.getRight()));

        else {
            if ((subRoot.getLeft() == null) || (subRoot.getRight() == null)) {
                AvlNode<T> temp = null;
                if (temp == subRoot.getLeft())
                    temp = subRoot.getRight();
                else
                    temp = subRoot.getLeft();

                if (temp == null) {
                    temp = subRoot;
                    subRoot = null;
                } else
                    subRoot = temp;
            } else {

                AvlNode<T> temp = minValueNode(subRoot.getRight());

                subRoot.setValue(temp.getValue());

                subRoot.setRight(delete(temp.getValue(), subRoot.getRight()));
            }
        }

        if (subRoot == null)
            return subRoot;

        subRoot.setHeight(Math.max(getHeight(subRoot.getLeft()), getHeight(subRoot.getRight())) + 1);
        int balance = getBalance(subRoot);

        if (balance > 1 && getBalance(subRoot.getLeft()) >= 0)
            return rightRotate(subRoot);

        if (balance > 1 && getBalance(subRoot.getLeft()) < 0) {
            subRoot.setLeft(leftRotate(subRoot.getLeft()));
            return rightRotate(subRoot);
        }

        if (balance < -1 && getBalance(subRoot.getRight()) <= 0)
            return leftRotate(subRoot);

        if (balance < -1 && getBalance(subRoot.getRight()) > 0) {
            subRoot.setRight(rightRotate(subRoot.getRight()));
            return leftRotate(subRoot);
        }

        return subRoot;
    }

    public void printTree() {
        inorder(this.root);
    }


    public String inorderText() {
        return inorder(this.root);
    }

    private String inorder(AvlNode<T> treeNode) {
        if (treeNode == null) return "";
        String t1 = inorder(treeNode.getLeft());
        String t2 = inorder(treeNode.getRight());
        if(treeNode.getLeft() == null && treeNode.getRight() ==null)
            return treeNode.value.toString();
        else if(treeNode.getLeft() == null)
            return treeNode.value.toString() +", "+t2;
        else if(treeNode.getRight() == null)
            return t1 + ", "+ treeNode.value.toString();
        else
            return t1 + ", "+ treeNode.value.toString() + ", " + t2;
    }

    public String preorderText() {
        return preorder(this.root);
    }

    private String preorder(AvlNode<T> treeNode) {
        if (treeNode == null) return "";
        String t1 = preorder(treeNode.getLeft());
        String t2 = preorder(treeNode.getRight());
        if(treeNode.getLeft() == null && treeNode.getRight() ==null)
            return treeNode.value.toString();
        else if(treeNode.getLeft() == null)
            return treeNode.value.toString() +", "+t2;
        else if(treeNode.getRight() == null)
            return treeNode.value.toString() + ", "+ t1 ;
        else
            return treeNode.value.toString() + ", "+ t1 + ", " + t2;
    }

    public String postorderText() {
        return postorder(this.root);
    }

    private String postorder(AvlNode<T> treeNode) {
        if (treeNode == null) return "";
        String t1 = postorder(treeNode.getLeft());
        String t2 = postorder(treeNode.getRight());
        if(treeNode.getLeft() == null && treeNode.getRight() ==null)
            return treeNode.value.toString();
        else if(treeNode.getLeft() == null)
            return t2 +", "+treeNode.value.toString();
        else if(treeNode.getRight() == null)
            return t1 + ", "+ treeNode.value.toString() ;
        else
            return   t1 + ", " + t2 + ", " + treeNode.value.toString();
    }


    /*toString() of the avl tree*/

    private void traverseNodes(StringBuilder sb, String padding, String pointer, AvlNode<T> node,
                               boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("   ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "R>";
            String pointerLeft = (node.getRight() != null) ? ">" : ">";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "R>";
        String pointerLeft = (root.getRight() != null) ? "  " : "  ";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }
}
