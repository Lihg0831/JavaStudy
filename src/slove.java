import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class slove {
}
public int maxArea(int[] height) {
    int left=0;
    int right=height.length-1;
    int maxArea=0;
    while(left<right){
        maxArea=Math.max(maxArea,Math.min(height[left],height[right])*(right-left));
        if(height[left]<height[right]){
            left++;
        }else{
            right--;
        }
    }
    return maxArea;
}

public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    // 边界处理：数组长度不足3，直接返回空列表
    if (nums == null || nums.length < 3) {
        return result;
    }
    // 1. 排序数组
    Arrays.sort(nums);
    int n = nums.length;
    // 2. 遍历第一个数
    for (int i = 0; i < n - 2; i++) {
        // 去重：和前一个数相同，跳过
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }
        // 若第一个数已经大于0，三数之和必然大于0，直接终止循环
        if (nums[i] > 0) {
            break;
        }
        // 3. 双指针找另外两个数
        int left = i + 1;
        int right = n - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum == 0) {
                // 找到一组解，加入结果集
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                // 跳过重复的左指针元素
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                // 跳过重复的右指针元素
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                // 移动指针，继续寻找下一组
                left++;
                right--;
            } else if (sum < 0) {
                // 和太小，左指针右移
                left++;
            } else {
                // 和太大，右指针左移
                right--;
            }
        }
    }
    return result;
}

public int maxDepth(TreeNode root) {
    if(root==null){
        return 0;
    }
    return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
}
public TreeNode invertTree(TreeNode root) {
    if(root==null){
        return null;
    }
    TreeNode temp=root.left;
    root.left=root.right;
    root.right=temp;
    invertTree(root.left);
    invertTree(root.right);
    return root;
}

public int climbStairs(int n) {
    if(n<=2){
        return n;
    }
    return climbStairs(n-1)+climbStairs(n-2);
}

public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    if (numRows <= 0) {
        return result;
    }
    // 1. 初始化结果列表
    result.add(Arrays.asList(1));
    // 2. 递归生成其他行
    for (int i = 1; i < numRows; i++) {
        List<Integer> newRow = new ArrayList<>();
        newRow.add(1);
        for (int j = 0; j < i; j++) {
            newRow.add(result.get(i - 1).get(j) + result.get(i - 1).get(j + 1));
        }
        newRow.add(1);
        result.add(newRow);
    }
    return result;
}

public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int res = 0;
    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= leftMax) {
                leftMax = height[left];
            } else {
                res += leftMax - height[left];
            }
            left++;
        } else {
            if (height[right] >= rightMax) {
                rightMax = height[right];
            } else {
                res += rightMax - height[right];
            }
            right--;
        }
    }
    return res;
}

public boolean isSymmetric(TreeNode root) {
    if(root == null) return true;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root.left);
    queue.offer(root.right);

    while(!queue.isEmpty()){
        TreeNode n1 = queue.poll();
        TreeNode n2 = queue.poll();
        if(n1 == null && n2 == null) continue;
        if(n1 == null || n2 == null) return false;
        if(n1.val != n2.val) return false;
        // 成对入队：左左 & 右右；左右 & 右左
        queue.offer(n1.left);
        queue.offer(n2.right);
        queue.offer(n1.right);
        queue.offer(n2.left);
    }
    return true;
}

public int diameterOfBinaryTree(TreeNode root) {
    dfs(root);
    return max;
}
private int max=0;
// 返回当前节点的最大深度
private int  dfs(TreeNode node) {
    if(node == null) return 0;
    int left = dfs(node.left);
    int right = dfs(node.right);
    // 更新经过当前节点的路径长度
    max = Math.max(max, left + right);
    // 返回当前节点高度
    return Math.max(left, right) + 1;
}


public TreeNode sortedArrayToBST(int[] nums) {
    if(nums.length==0){
        return null;
    }
    int mid=nums.length/2;
    TreeNode root=new TreeNode(nums[mid]);
    root.left=sortedArrayToBST(Arrays.copyOfRange(nums,0,mid));
    root.right=sortedArrayToBST(Arrays.copyOfRange(nums,mid+1,nums.length));
    return root;
}


    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums,0);
        return res;
    }
    private void backtrack(int[] nums,int start){
        if(start == nums.length){
            List<Integer> list = new ArrayList<>();
            for(int num:nums) list.add(num);
            res.add(list);
            return;
        }
        for(int i=start;i<nums.length;i++){
            swap(nums,start,i);
            backtrack(nums,start+1);
            swap(nums,start,i); // 回溯换回
        }
    }
    private void swap(int[] arr,int a,int b){
        int t = arr[a];arr[a]=arr[b];arr[b]=t;
    }

public int rob(int[] nums) {
    if(nums.length==0){
        return 0;
    }
    if(nums.length==1){
        return nums[0];
    }
    if(nums.length==2){
        return Math.max(nums[0],nums[1]);
    }
    int[] dp=new int[nums.length];
    dp[0]=nums[0];
    dp[1]=Math.max(nums[0],nums[1]);
    for(int i=2;i<nums.length;i++){
        dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
    }
    return dp[nums.length-1];
}

public int numSquares(int n) {
    if(n<=0){
        return 0;
    }
    int[] dp=new int[n+1];
    for(int i=1;i<=n;i++){
        dp[i]=i;
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j*j<=i;j++){
            dp[i]=Math.min(dp[i],dp[i-j*j]+1);
        }
    }
    return dp[n];
}

    public void sortColors(int[] nums) {
        if(nums.length==0){
            return;
        }
        int[] count=new int[3];
        for(int i=0;i<nums.length;i++){
            count[nums[i]]++;
        }
        int index=0;
        for(int i=0;i<count[0];i++){
            nums[index++]=0;
        }
        for(int i=0;i<count[1];i++){
            nums[index++]=1;
        }
        for(int i=0;i<count[2];i++){
            nums[index++]=2;
        }
    }

List<List<Integer>> res2 = new ArrayList<>();
List<Integer> path2 = new ArrayList<>();
public List<List<Integer>> subsets(int[] nums) {
    backtrack2(nums, 0);
    return res2;
}
// index：当前处理下标
void backtrack2(int[] nums, int index) {
    // 递归终止：下标越界，保存当前子集
    if(index == nums.length){
        res2.add(new ArrayList<>(path2));
        return;
    }
    // 分支1：不选当前数，直接下一位
    backtrack2(nums, index+1);

    // 分支2：选当前数，递归后回溯撤销
    path2.add(nums[index]);
    backtrack2(nums, index+1);
    path2.remove(path2.size()-1);
}



public boolean canJump(int[] nums) {
    int maxReach = 0;
    int len = nums.length;
    for(int i = 0; i < len; i++){
        // 当前下标超出最远可达，走不到这里
        if(i > maxReach) return false;
        // 更新最远能跳到的位置
        maxReach = Math.max(maxReach, i + nums[i]);
        // 提前抵达终点
        if(maxReach >= len - 1) return true;
    }
    return true;
}

public int jump(int[] nums) {
    int[] dp=new int[nums.length];
    for(int i=0;i<nums.length;i++){
        dp[i]=i;
    }
    dp[0]=0;
    for(int i=1;i<nums.length;i++){
        for(int j=0;j<i;j++){
            if(j+nums[j]>=i){
                dp[i]=Math.min(dp[i],dp[j]+1);
            }
        }
    }
    return dp[nums.length-1];
}

public int coinChange(int[] coins, int amount) {
    if(amount<=0){
        return 0;
    }
    int[] dp=new int[amount+1];
    for(int i=1;i<=amount;i++){
        dp[i]=amount+1;
    }
    dp[0]=0;
    for(int i=1;i<=amount;i++){
        for(int j=0;j<coins.length;j++){
            if(coins[j]<=i){
                dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }
    }
    return dp[amount]>amount? -1 : dp[amount];
}

public int lengthOfLIS(int[] nums) {
    if(nums.length==0){
        return 0;
    }
    int[] dp=new int[nums.length];
    for(int i=0;i<nums.length;i++){
        dp[i]=1;
    }
    for(int i=1;i<nums.length;i++){
        for(int j=0;j<i;j++){
            if(nums[i]>nums[j]){
                dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
    }
    int max=0;
    for(int i=0;i<nums.length;i++){
        max=Math.max(max,dp[i]);
    }
    return max;
}

public boolean wordBreak(String s, List<String> wordDict) {
    if(s.isEmpty()){
        return true;
    }
    boolean[] dp=new boolean[s.length()+1];
    dp[0]=true;
    for(int i=1;i<=s.length();i++){
        for(int j=0;j<i;j++){
            if(dp[j] && wordDict.contains(s.substring(j,i))){
                dp[i]=true;
                break;
            }
        }
    }
    return dp[s.length()];
}

public int maxProduct(int[] nums) {
    int[] dp=new int[nums.length];
    int max=-11;
    for(int i=0;i<nums.length;i++){
        dp[i]=nums[i];
    }
    for(int i=1;i<nums.length;i++){
        max=Math.max(max,Math.max(dp[i],dp[i-1]*nums[i]));
    }

    return max;
}

public boolean searchMatrix(int[][] matrix, int target) {
    int row=matrix.length;
    int col=matrix[0].length;
    int i=row-1,j=0;
    while(i>=0 && j<col){
        if(matrix[i][j]==target){
            return true;
        }
        if(matrix[i][j]>target){
            i--;
        }
        else{
            j++;
        }
    }
    return false;
}

public int maxSubArray(int[] nums) {
    int max=nums[0];
    int sum=nums[0];
    for(int i=1;i<nums.length;i++){
        sum=Math.max(sum+nums[i],nums[i]);
        max=Math.max(max,sum);
    }
    return max;
}

public void setZeroes(int[][] matrix) {
    int row=matrix.length;
    int col=matrix[0].length;
    boolean[] rowZero=new boolean[row];
    boolean[] colZero=new boolean[col];
    for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            if(matrix[i][j]==0){
                rowZero[i]=true;
                colZero[j]=true;
            }
        }
    }
    for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            if(rowZero[i] || colZero[j]){
                matrix[i][j]=0;
            }
        }
    }
}

public int findDuplicate(int[] nums) {
    int[] flag = new int[nums.length];
    for (int num : nums) {
        flag[num]++;
        if (flag[num] > 1) {
            return num;
        }
    }
    return 0;
}

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public ListNode detectCycle(ListNode head) {
    if(head==null){
        return null;
    }
    if(head.next==null){
        return null;
    }
    ListNode slow=head;
    ListNode fast=head;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
        if(slow==fast){
            break;
        }
    }
    if(slow!=fast){
        return null;
    }
    slow=head;
    while(slow!=fast){
        slow=slow.next;
        fast=fast.next;
    }
    return slow;
}

public int search(int[] nums, int target) {
    int left=0;
    int right=nums.length-1;
    while(left<=right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }

    }
    return -1;
}
//345012 345678012    4560123
public int findKthLargest(int[] nums, int k) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < nums.length; i++) {
        stack.push(nums[i]);
    }
    for (int i = 0; i < k - 1; i++) {
        stack.pop();
    }
    return stack.pop();

}


void main() {
}



