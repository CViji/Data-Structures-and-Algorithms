/* You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

Example 1:

Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
Example 2:

Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.
 
Constraints:

1 <= s.length <= 105
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length */

// Sliding Window Approach - O(N), O(1)
class Solution {
    public int takeCharacters(String s, int k) {
        int len = s.length();
        if (k == 0) return 0;
        int[] total = new int[3];
        for (char ch : s.toCharArray()) {
            total[ch - 'a']++;
        }

        if (total[0] < k || total[1] < k || total[2] < k) {
            return -1;
        }

        int[] count = new int[3];
        int maxMiddle = 0, left = 0;

        for (int right = 0; right < len; right++) {
            count[s.charAt(right) - 'a']++;

            while (count[0] > total[0] - k || count[1] > total[1] - k || count[2] > total[2] - k) {
                count[s.charAt(left) - 'a']--;
                left++;
            }
            maxMiddle = Math.max(maxMiddle, right - left + 1);
        }

        return len - maxMiddle;
    }
}
