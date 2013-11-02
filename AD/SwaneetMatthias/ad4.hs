f 0 = 2	
f n = 2 + 3 * f (n-1)

a n = 3 ^ n
b n = 3 ^ (n+1)
c n = 3 ^ (n+1) - 1


m n = ("F:", f n, ("A:", a n), ("B:", b n), ("C:", c n))


main = putStrLn $ show (map (m) [0..7])