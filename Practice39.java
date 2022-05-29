class Solution {
    int m;
    int n;
    char region = 'a';
    HashSet<Character> set = new HashSet<>();
    public void solve(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }
        
        m = board.length;
        n = board[0].length;
        
        for(int i = 0 ; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = region;
                    dfs(board,i,j);
                    region++;
                }
            }
        }
        // System.out.println(set);
        // System.out.println(Arrays.deepToString(board));
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != 'X') {
                    if(!set.contains(board[i][j])) {
                        board[i][j] = 'X';
                    } else {
                        board[i][j] = 'O';
                    }
                }
            }
        }
    }
    int[][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
    private void dfs(char[][] board, int row, int col) {
        int r = row;
        int c = col;
        boolean temp = isTouchingEdge(r, c);
        //System.out.println(r +", "+ c +", "+ temp);
        if(temp) {
            set.add(region);
        }
        for(int[] dir: dirs) {
            int nR = dir[0] + r;
            int nC = dir[1] + c;
            
            if(nR >= 0 && nC >= 0 && nR < m && nC < n && board[nR][nC] == 'O') {
                board[nR][nC] = region;
                
                dfs(board, nR, nC);
            }
        }
    }
    
    private boolean isTouchingEdge(int row, int col) {
        if(row == 0 || col == 0 || row == m - 1 || col == n-1) {
            return true;
        } else {
            return false;
        }
        
    }
}

class Solution {
    public int findLength(int[] nums1, int[] nums2) {
//         if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
//             return 0;
//         }
//         HashMap<Integer,List<Integer>> map = new HashMap<>();
        
//         for(int i = 0; i < nums2.length; i++) {
//             if(!map.containsKey(nums2[i])) {
//                 List<Integer> list = new ArrayList<>();
//                 list.add(i);
//                 map.put(nums2[i], list);
//             } else {
//                 List<Integer> list = map.get(nums2[i]);
//                 list.add(i);
//                 map.put(nums2[i],list);
//             }   
//         }
//         //System.out.println(map);
//         int result = 0;
//         for(int i = 0 ; i < nums1.length; i++) {
//             int current = nums1[i];
//             if(map.containsKey(current)) {
//                 List<Integer> list = map.get(current);
//                 for(int j = 0; j < list.size(); j++) {
//                     int temp = list.get(j);
//                     int streak = 0;
//                     while(i < nums1.length && temp < nums2.length && nums1[i] == nums2[temp]) {
//                         i++;
//                         temp++;
//                         streak++;
//                     }
//                     result = Math.max(result, streak);
//                     i = i - streak;
//                     streak = 0;
//                 }
//             }
//         }
//         return result;
        
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return 0;
        }
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= nums1.length; i++) {
            dp[0][i] = nums1[i - 1];
        }
        for(int i = 1; i <= nums2.length; i++) {
            dp[i][0] = nums2[i - 1];
        }
        int result = 0;
        //System.out.println(Arrays.deepToString(dp));
        for(int i = 1; i <= nums2.length; i++) {
            for(int j = 1; j <= nums1.length; j++) {
                if(dp[0][j] == dp[i][0]) {
                    if(i == 1 || j == 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        
        return result;
    }
}