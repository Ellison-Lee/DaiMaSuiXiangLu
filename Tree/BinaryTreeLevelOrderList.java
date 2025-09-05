import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class BinaryTreeLevelOrderList {

    // 遍历过程中直接打印每层节点（不存储结果）
    public void levelOrderPrint(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // 遍历当前层节点并打印
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                // 加入下一层节点
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println(); // 每层打印完换行
        }
    }

    public static void main(String[] args) {
        // 构建测试二叉树:
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BinaryTreeLevelOrderList traversal = new BinaryTreeLevelOrderList();

        // 直接打印每层
        System.out.println("分层打印：");
        traversal.levelOrderPrint(root);
    }
}
