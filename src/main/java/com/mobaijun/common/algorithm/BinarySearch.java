package com.mobaijun.common.algorithm;

/**
 * software：IntelliJ IDEA 2022.2.3<br>
 * class name: BinarySearch<br>
 * class description: 二分查找算法
 * <p/>
 * 其基本思想是：每次取序列的中间值与目标值进行比较，如果中间值大于目标值，那么目标值一定在序列的左半部分，反之则在右半部分。
 * 然后不断缩小查找范围，直到找到目标值或者确定目标值不存在。
 * <p/>
 *
 * @author MoBaiJun 2023/2/20 0:21
 */
public class BinarySearch {

    /**
     * 二分查找算法
     * 该算法的时间复杂度为 $O(\log n)$，是一种非常高效的查找算法。
     *
     * @param arr 有序的数组
     * @param key 目标值
     * @return 目标值在数组中的索引，如果不存在则返回 -1
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            // 计算中间位置
            int mid = (low + high) / 2;
            if (arr[mid] == key) {
                // 如果中间值等于目标值，直接返回中间位置
                return mid;
            } else if (arr[mid] > key) {
                // 如果中间值大于目标值，则在左半部分继续查找
                high = mid - 1;
            } else {
                // 如果中间值小于目标值，则在右半部分继续查找
                low = mid + 1;
            }
        }
        // 没有找到目标值，返回 -1
        return -1;
    }
}
