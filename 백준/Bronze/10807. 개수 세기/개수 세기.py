n = int(input())
arr = map(int,input().split())
k = int(input())
cnt = 0;

for i in arr:
    if i == k:
        cnt += 1;
        
print(cnt)