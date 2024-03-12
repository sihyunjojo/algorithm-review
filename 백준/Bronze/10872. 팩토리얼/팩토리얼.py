def fac(num):
    global a
    if (0 == num): return;
    a *= num
    fac(num-1)

n = int(input());
a = 1
fac(n)
print(a)