f 0 = 2
f n = 2 + 3 * f (n-1)

a n = 3 ^ n
b n = 3 ^ (n+1)
c n = 3 ^ (n+1) - 1


comps n = (f n, c n)


main = print $ map comps [0..7]