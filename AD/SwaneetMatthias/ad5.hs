

f 0 = 1
f 1 = 1
f 2 = 1
f n = f (n-1) + 2 * f (n-2) + 3 * f (n-3)


main = putStrLn $ show (map (\x -> (x, f x)) [0..15])