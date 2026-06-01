class Node {
    String stock;
    int price;
    Node left, right;

    Node(String stock, int price) {
        this.stock = stock;
        this.price = price;
    }
}

public class AVLTree {

    Node root;

    Node insert(Node node, String stock, int price) {

        if (node == null)
            return new Node(stock, price);

        if (stock.compareTo(node.stock) < 0)
            node.left = insert(node.left, stock, price);

        else if (stock.compareTo(node.stock) > 0)
            node.right = insert(node.right, stock, price);

        return node;
    }

    Node search(Node node, String stock) {

        if (node == null || node.stock.equals(stock))
            return node;

        if (stock.compareTo(node.stock) < 0)
            return search(node.left, stock);

        return search(node.right, stock);
    }

    void inorder(Node node) {

        if (node != null) {
            inorder(node.left);
            System.out.println(node.stock + " : ₹" + node.price);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, "AAPL", 150);
        tree.root = tree.insert(tree.root, "TSLA", 220);
        tree.root = tree.insert(tree.root, "GOOG", 180);
        tree.root = tree.insert(tree.root, "MSFT", 300);
        tree.root = tree.insert(tree.root, "AMZN", 250);

        System.out.println("=== Stock Records ===");
        tree.inorder(tree.root);

        Node result = tree.search(tree.root, "TSLA");

        System.out.println("\n=== Search Result ===");

        if (result != null)
            System.out.println("Stock Found: " +
                    result.stock + " Price = ₹" +
                    result.price);
        else
            System.out.println("Stock Not Found");
    }
}