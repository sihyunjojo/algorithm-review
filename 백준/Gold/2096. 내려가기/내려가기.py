n = int(input())
board = list(list(map(int,input().split())) for _ in range(n))


min_dp = [board[0]] + [[0,0,0]]
max_dp = [board[0]] + [[0,0,0]]

for t in range(1,n):
    i = t % 2
    min_dp[i] = [0,0,0]
    max_dp[i] = [0,0,0]

    min_dp[i][0] = board[t][0] + min(min_dp[i-1][1],min_dp[i-1][0])
    min_dp[i][1] = board[t][1] + min(min_dp[i-1])
    min_dp[i][2] = board[t][2] + min(min_dp[i-1][1],min_dp[i-1][2])

    max_dp[i][0] = board[t][0] + max(max_dp[i-1][1],max_dp[i-1][0])
    max_dp[i][1] = board[t][1] + max(max_dp[i-1])
    max_dp[i][2] = board[t][2] + max(max_dp[i-1][1],max_dp[i-1][2])

print(max(max_dp[(n-1)%2]), min(min_dp[(n-1)%2]))
