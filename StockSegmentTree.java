public class StockSegmentTree {

    int[] tree;
    int n;

    // Constructor
    public StockSegmentTree(int[] prices) {
        n = prices.length;
        tree = new int[4 * n];
        build(prices, 0, 0, n - 1);
    }

    // Build Segment Tree
    private void build(int[] prices, int node, int start, int end) {

        if (start == end) {
            tree[node] = prices[start];
        } else {

            int mid = (start + end) / 2;

            build(prices, 2 * node + 1, start, mid);
            build(prices, 2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] +
                         tree[2 * node + 2];
        }
    }

    // Range Sum Query
    public int query(int node, int start, int end,
                     int left, int right) {

        // No overlap
        if (right < start || end < left)
            return 0;

        // Complete overlap
        if (left <= start && end <= right)
            return tree[node];

        // Partial overlap
        int mid = (start + end) / 2;

        return query(2 * node + 1,
                     start,
                     mid,
                     left,
                     right)
                +
               query(2 * node + 2,
                     mid + 1,
                     end,
                     left,
                     right);
    }

    // Update stock price
    public void update(int node, int start, int end,
                       int index, int value) {

        if (start == end) {
            tree[node] = value;
        } else {

            int mid = (start + end) / 2;

            if (index <= mid)
                update(2 * node + 1,
                       start,
                       mid,
                       index,
                       value);
            else
                update(2 * node + 2,
                       mid + 1,
                       end,
                       index,
                       value);

            tree[node] = tree[2 * node + 1]
                       + tree[2 * node + 2];
        }
    }

    public static void main(String[] args) {

        int stockPrices[] = {
                100, 120, 130, 150, 110
        };

        StockSegmentTree st =
                new StockSegmentTree(stockPrices);

        System.out.println("=== Stock Prices ===");

        for (int i = 0; i < stockPrices.length; i++) {
            System.out.println(
                    "Day " + (i + 1)
                    + " : ₹"
                    + stockPrices[i]);
        }

        int sum = st.query(
                0,
                0,
                stockPrices.length - 1,
                1,
                3);

        System.out.println(
                "\nTotal Price from Day 2 to Day 4 = ₹"
                        + sum);

        st.update(
                0,
                0,
                stockPrices.length - 1,
                2,
                200);

        System.out.println(
                "\nUpdated Day 3 Price = ₹200");

        int updatedSum = st.query(
                0,
                0,
                stockPrices.length - 1,
                1,
                3);

        System.out.println(
                "Updated Total Price from Day 2 to Day 4 = ₹"
                        + updatedSum);
    }
}