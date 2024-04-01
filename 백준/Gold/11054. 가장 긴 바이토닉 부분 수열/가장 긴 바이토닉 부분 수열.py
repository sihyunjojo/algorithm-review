n = int(input())
arr = list(map(int,input().split()))
dp1 = [1] * n
dp2 = [1] * n

# 배열이 커질수록 값이 작아질 순 없음.
# 배열상 가장 큰 값은 항상 고정임.
# 가장 큰 값이 2개 이상이라면, 양쪽의 숫자들이 모두 중요함.

for i in range(n):
    for j in range(i):
        if arr[i] > arr[j]:
            dp1[i] = max(dp1[j] + 1, dp1[i])

arr = arr[::-1]

for i in range(n):
    for j in range(i):
        if arr[i] > arr[j]:
            dp2[i] = max(dp2[j] + 1 , dp2[i])


result = 0
for i in range(n):
    result = max(dp1[i]+dp2[-i-1]-1,result)

print(result)