import java.util.*;

/**
 * LeetCode 102. 二叉树的层序遍历
 * 
 * 描述：给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 示例：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 
 * 输入：root = [1]
 * 输出：[[1]]
 */

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
