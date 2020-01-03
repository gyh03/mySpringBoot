package test;

import java.util.Arrays;

/**
 * @author guoyanhong
 * @date 2019/12/7 22:49
 */
public class test {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 6, 2, 10, 12, 1};
        int length = arr.length;
        // minIndex = 4
        int minIndex = length / 2;
        int index = 3;
        if (index < 0 || index >= length) {
            return;
        }
        int rightIndex;
        int left, right;
        // 交换次数（取index两边的数的最大【个数】）
        int forLen = index < minIndex ? length - index - 1 : index;
        for (int i = 0; i < forLen; i++) {
            // index 两边的最小【个数】
            int minChange = index < minIndex ? index : length - index - 1;
            // 先替换最小个数
            if (i < minChange) {
                rightIndex = index + i + 1;
                left = arr[i];
                right = arr[rightIndex];
                arr[i] = right;
                arr[rightIndex] = left;
            } else {
                // 再往 index方向 移动剩下的数字（或者说 index 往对称位置移动）
                if (index < minIndex) {
                    for (int j = length - 1; j > index; j--) {
                        left = arr[j - 1];
                        right = arr[j];
                        arr[j - 1] = right;
                        arr[j] = left;
                    }
                } else {
                    for (int j = minChange; j < length - 1; j++) {
                        left = arr[j];
                        right = arr[j + 1];
                        arr[j] = right;
                        arr[j + 1] = left;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
