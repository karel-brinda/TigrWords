#! /usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import libwords

# we seek the AC(n) for n from {1,2,..,50}
ACN_MAX_N = 100

# let us suppose that k=100
k = 10

# word
wrd = libwords.WordDBonacci(3)

# Parikh vectors will be stored in this dictionary
parikh = {}

# set of all attained values of AC(n)
abelianComplexityFunctionValues = set()

for n in range(1, ACN_MAX_N + 1):
	#wrd.generatePrefix(length=k*n)
	
	print(n, end=" ")
	acn, parikh[n] = wrd.getAbelianComplexityC(n,enoughPrefixLength=k*n,program="tribonacci.exe")

	abelianComplexityFunctionValues.add(acn)


for key in parikh:
	print ("AC(", key, ") = ", len (parikh[key]), "   ...Parikh vectors: ", [str(x) for x in parikh[key]], sep="")

print ("Values of the Abelian complexity:", abelianComplexityFunctionValues)
