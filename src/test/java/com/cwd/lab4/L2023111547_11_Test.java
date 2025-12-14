package com.cwd.lab4;

import com.cwd.lab4.Solution;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 三数之和算法测试类
 *
 * 测试用例设计总体原则：
 * 1. 等价类划分原则：
 *    - 有效等价类：能产生符合要求的三元组的数组
 *    - 无效等价类：不能产生符合要求的三元组的数组
 * 2. 边界值分析原则：
 *    - 数组长度边界：空数组、少于3个元素、刚好3个元素、多个元素
 *    - 数值边界：全正数、全负数、包含0、重复元素
 * 3. 特殊值测试原则：
 *    - 包含重复三元组的情况
 *    - 多个解的情况
 *    - 无解的情况
 */
public class L2023111547_11_Test {

    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    /**
     * 测试目的：验证算法能正确处理标准示例，找到所有不重复的三元组
     * 测试用例：包含正数、负数、零的数组，有多个有效解
     * 等价类：有效等价类（有解且多个）
     */
    @Test
    public void testStandardCaseWithMultipleSolutions() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);

        assertEquals("应该找到2个不重复的三元组", 2, result.size());
        assertTrue("应该包含三元组 [-1, -1, 2]", containsTriplet(result, -1, -1, 2));
        assertTrue("应该包含三元组 [-1, 0, 1]", containsTriplet(result, -1, 0, 1));
    }

    /**
     * 测试目的：验证算法能正确处理无解的情况
     * 测试用例：全正数数组，不可能有三数之和为0
     * 等价类：无效等价类（无解）
     */
    @Test
    public void testAllPositiveNumbersNoSolution() {
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> result = solution.threeSum(nums);

        assertTrue("全正数数组应该返回空列表", result.isEmpty());
    }

    /**
     * 测试目的：验证算法能正确处理无解的情况
     * 测试用例：全负数数组，不可能有三数之和为0
     * 等价类：无效等价类（无解）
     */
    @Test
    public void testForceFailureToVerifyWorkflow() {
        // 这个测试会失败，用于验证 workflow 是否运行
        fail("Force failure to verify GitHub Actions is working");
    }

    /**
     * 测试目的：验证算法能正确处理三个零的情况
     * 测试用例：三个零的数组，唯一有效的三元组
     * 等价类：有效等价类（有解且唯一）
     * 边界值：数组长度刚好为3
     */
    @Test
    public void testThreeZeros() {
        int[] nums = {0, 0, 0};
        List<List<Integer>> result = solution.threeSum(nums);

        assertEquals("应该找到1个三元组", 1, result.size());
        assertTrue("应该包含三元组 [0, 0, 0]", containsTriplet(result, 0, 0, 0));
    }

    /**
     * 测试目的：验证算法能正确处理重复元素但无解的情况
     * 测试用例：包含重复元素但无法组成和为0的三元组
     * 等价类：无效等价类（无解）
     */
    @Test
    public void testDuplicateNumbersNoSolution() {
        int[] nums = {0, 1, 1};
        List<List<Integer>> result = solution.threeSum(nums);

        assertTrue("应该返回空列表", result.isEmpty());
    }

    /**
     * 测试目的：验证算法能正确处理边界情况 - 空数组
     * 测试用例：空数组
     * 边界值：数组长度最小边界
     * 等价类：无效等价类（无解）
     */
    @Test
    public void testEmptyArray() {
        int[] nums = {};
        List<List<Integer>> result = solution.threeSum(nums);

        assertTrue("空数组应该返回空列表", result.isEmpty());
    }

    /**
     * 测试目的：验证算法能正确处理边界情况 - 数组元素不足
     * 测试用例：少于3个元素的数组
     * 边界值：数组长度不足
     * 等价类：无效等价类（无解）
     */
    @Test
    public void testInsufficientElements() {
        int[] nums = {0, 1};
        List<List<Integer>> result = solution.threeSum(nums);

        assertTrue("少于3个元素的数组应该返回空列表", result.isEmpty());
    }

    /**
     * 测试目的：验证算法能正确处理包含重复可能解的情况
     * 测试用例：可能产生重复三元组的数组，测试去重功能
     * 等价类：有效等价类（有解但需要去重）
     */
    @Test
    public void testDuplicateSolutionsPrevention() {
        int[] nums = {-1, -1, 0, 1, 1};
        List<List<Integer>> result = solution.threeSum(nums);

        // 应该只有 [-1, 0, 1]，不能有重复
        assertEquals("应该只有1个不重复的三元组", 1, result.size());
        assertTrue("应该包含三元组 [-1, 0, 1]", containsTriplet(result, -1, 0, 1));
    }

    /**
     * 测试目的：验证算法能正确处理对称解的情况
     * 测试用例：可能产生对称三元组的数组
     * 等价类：有效等价类（有多个对称解）
     */
    @Test
    public void testSymmetricSolutions() {
        int[] nums = {-2, 0, 1, 1, 2};
        List<List<Integer>> result = solution.threeSum(nums);

        assertEquals("应该找到2个三元组", 2, result.size());
        assertTrue("应该包含三元组 [-2, 0, 2]", containsTriplet(result, -2, 0, 2));
        assertTrue("应该包含三元组 [-2, 1, 1]", containsTriplet(result, -2, 1, 1));
    }

    /**
     * 测试目的：验证算法能正确处理较大数组的情况
     * 测试用例：包含多个元素的较大数组
     * 等价类：有效等价类（有解且性能要求）
     */
    @Test
    public void testLargerArrayWithSolutions() {
        int[] nums = {-4, -2, -2, -1, 0, 1, 2, 2, 3, 4};
        List<List<Integer>> result = solution.threeSum(nums);

        // 预期解：[-4,0,4], [-4,1,3], [-4,2,2], [-2,-2,4], [-2,0,2], [-1,0,1]
        assertEquals("应该找到6个不重复的三元组", 7, result.size());
    }

    /**
     * 辅助方法：检查结果列表中是否包含指定的三元组（不考虑顺序）
     * @param result 算法返回的结果列表
     * @param a 第一个数
     * @param b 第二个数
     * @param c 第三个数
     * @return 如果包含指定三元组则返回true
     */
    private boolean containsTriplet(List<List<Integer>> result, int a, int b, int c) {
        for (List<Integer> triplet : result) {
            if (triplet.size() == 3 &&
                    triplet.contains(a) &&
                    triplet.contains(b) &&
                    triplet.contains(c)) {
                // 检查是否正好包含这三个数（避免部分匹配）
                int countA = 0, countB = 0, countC = 0;
                for (int num : triplet) {
                    if (num == a) countA++;
                    if (num == b) countB++;
                    if (num == c) countC++;
                }
                // 考虑重复数字的情况
                int expectedA = (a == b && a == c) ? 3 : (a == b || a == c) ? 2 : 1;
                int expectedB = (a == b && a == c) ? 3 : (b == a || b == c) ? 2 : 1;
                int expectedC = (a == b && a == c) ? 3 : (c == a || c == b) ? 2 : 1;

                if (countA == expectedA && countB == expectedB && countC == expectedC) {
                    return true;
                }
            }
        }
        return false;
    }
}