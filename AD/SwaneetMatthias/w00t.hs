


average :: [Int] -> Float
average xs = sumVal / lengthVal
	where
		sumVal = fromIntegral (sum xs) :: Float
		lengthVal = fromIntegral (length xs) :: Float


variance :: [Int] -> Float
variance xs = 1.0/(length xs) * (sum([(x - avg)*(x-avg)|x <- xs]))
	where
		avg = average xs 