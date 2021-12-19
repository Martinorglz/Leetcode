public class TwoSum {
    /**
     * point1: how to jump out of double loop?
     * point2: how to jump a loop?
     * point3: how to skip a loop?
     * function: return the index the numbers in array that the sum of them is equal to provided target and
     * the sum by doubling same number at same index is not allowed.
     */
    public static int[] twoSum(int[] nums, int target){
        int indexLeft, indexRight=0;
        boolean get = false;
        for(indexLeft = 0; indexLeft < nums.length; indexLeft++){
            int num1 = nums[indexLeft];
            for(indexRight = 0; indexRight < nums.length; indexRight++){
                int num2 = nums[indexRight];
                if (indexRight == indexLeft){
                    continue; // skip the loop
                }
                if (num1 + num2 == target) {
                    System.out.println("Yes!");
                    get = true;
                    break; // jump out the loop
                }
            }
            if (get){
                break;
            }
        }
        return new int[]{indexLeft,indexRight};
    }

    public static void main(String[] args) {
        int[] nums = {3,3};
        int[] result = twoSum(nums,6);
        System.out.println(result[0]+","+result[1]);
    }
}
