# /usr/bin/env python3
# -*- coding: utf-8 -*-

#
# Karel Brinda (brinda@fjfi.cvut.cz)
#
# Department of Mathematics
# Faculty of Nuclear Sciences and Physical Engineering
# Czech Technical University in Prague
#

import copy

class Matrix(object):
	def __init__(self, matrix=[[]], m=0, n=0):
		if m != 0 and n != 0:
			self.m = m
			self.n = n
			self.matrix = [[0 for _ in range(self.n)] for _ in range(self.m)]
		else:
			self.m = len(matrix)
			self.n = max( map(len,matrix)  )
			self.matrix = [[0 for _ in range(self.n)] for _ in range(self.m)]

			for i in range(len(matrix)):
				for j in range(len(matrix[i])):
					self.matrix[i][j] = matrix[i][j]
			#TODO: check parameters

	def __getitem__(self, key):
		return self.matrix[key]

	def __str__(self):
		rows = ''.join([str(row) + "\n" for row in self.matrix])
		return "" + rows.rstrip("\n")

	def __add__(self, other):
		radky = zip(self.matrix, other.matrix)
		soucet = [[a + b for (a, b) in zip(radA, radB)] for (radA, radB) in radky]
		return Matrix(soucet)

	def __sub__(self, other):
		radky = zip(self.matrix, other.matrix)
		rozdil = [[a - b for (a, b) in zip(radA, radB)] for (radA, radB) in radky]
		return Matrix(rozdil)

	def __mul__(self, other):
		if self.n != other.m:
			raise ValueError("Matrices have bad sizes ([" + str(self.m) +
							 "," + str(self.n) + "],[" + str(other.m) + ","
							 + str(other.n) + "]")

		newMatrix = Matrix (m=self.m, n=other.n)
		for x in range(newMatrix.m):
			for y in range(newMatrix.n):
				newMatrix[x][y] = sum([self.matrix[x][i]*other[i][y] for i in range (self.n)])

		return newMatrix

	def __pow__(self, power):
		if not isinstance(power, int):
			raise TypeError("power must be an integer")

		if self.m != self.n:
			raise ValueError("only square matrices can be raised to a power")

		if power < 0:
			raise ArgumentError("power must be greater than or equal to 0")

		if power == 0:
			matrix = Matrix(m = self.m, n = self.n)
			for i in range (self.m):
				matrix[i][i] = 1
		elif power > 0:
			matrix = copy.deepcopy(self)
			for i in range(power-1):
				matrix *= self

		return matrix

	def __eq__(self, other):
		if self.__str__() == other.__str__():
			return True
		else:
			return False

	def __hash__(self):
		return hash(str(self))

	def __len__(self):
		return len(self.matrix)
	