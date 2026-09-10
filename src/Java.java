import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java {
    public int search(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]<target){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
            int leftIndex = findleftIndex(nums,target);
            int rightIndex = findrightIndex(nums,target);
            if(leftIndex== -1 || rightIndex== -1){
                return new int[]{-1,-1};
            }
            return new int[]{leftIndex,rightIndex};
    }
    private int findleftIndex(int[] nums, int target){
        int left =0;
        int right = nums.length-1;
        int res =-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                res = mid;
                right = mid-1;
            }
            else if(nums[mid]<target){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return res;
    }
    private int findrightIndex(int[] nums, int target){
        int left =0;
        int right = nums.length-1;
        int res =-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                res = mid;
                left = mid+1;
            }
            else if(nums[mid]<target){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return res;
    }

    public int searchInsert(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;
        int index = -1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid]==target){
                index = mid;
                break;
            }
            else if(nums[mid]<target){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        if(index==-1){
            index = left;
        }
        return index;
    }

    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i] = nums[i]*nums[i];
        }
        Arrays.sort(res);
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums); // 排序

        for (int i = 0; i < nums.length; i++) {
            // 第一个数大于0，后续无满足条件组合
            if (nums[i] > 0) break;
            // 跳过重复i，去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    // 记录合法三元组
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 跳过左侧重复
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    // 跳过右侧重复
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
