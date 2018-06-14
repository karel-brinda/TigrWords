#! /usr/bin/env python3
# -*- coding: utf-8 -*-

#
# Karel Brinda (brinda@fjfi.cvut.cz)
#
# Department of Mathematics
# Faculty of Nuclear Sciences and Physical Engineering
# Czech Technical University in Prague
#


import sys

import unittest
sys.path.append("./../")
import libmatrix

class MatrixTest(unittest.TestCase):
	def testMatrixCreation(self):
		#TODO: self.assertRaises(TypeError, libmatrix.Matrix, [])
		#TODO: self.assertRaises(TypeError, libmatrix.Matrix, [[2, "k"]])
		#TODO: self.assertRaises(ValueError, libmatrix.Matrix, m=0, n=1)
		#TODO: self.assertRaises(ValueError, libmatrix.Matrix, m=1, n=0)
		#TODO: self.assertRaises(ValueError, libmatrix.Matrix, m=-1, n=4)
		#TODO: self.assertRaises(ValueError, libmatrix.Matrix, m=4, n=-1)

		matrix1 = libmatrix.Matrix(m=4, n=3)
		self.assertRaises(ValueError, matrix1.__pow__,3)

	def testMatrixComparing(self):
		matrix1 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		matrix2 = libmatrix.Matrix  ([[3, 1], [2, 5]])
		matrix3 = libmatrix.Matrix  ([[3, 1], [2, 5]])
		self.assertEqual(True, matrix2 == matrix3)
		self.assertEqual(False, matrix1 == matrix2)

		otherMatrix1 = libmatrix.Matrix  ([[1, 2], [3, 1]])
		otherMatrix2 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		self.assertNotEqual(True, otherMatrix1 == otherMatrix2, "Comparison error (different matrices)")

		otherMatrix1 = libmatrix.Matrix  ([[1, 2], [3, 4], [0, 0]])
		otherMatrix2 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		self.assertNotEqual(True, otherMatrix1 == otherMatrix2, "Comparison error (different sizes of matrices)")

	def testMatrixAddition(self):
		matrix1 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		matrix2 = libmatrix.Matrix  ([[3, 1], [2, 5]])
		matrixSum = libmatrix.Matrix([[4, 3], [5, 9]])

		self.assertEqual(matrixSum, matrix1 + matrix2, "Addition error")

	def testMatrixMultiplication(self):
		matrix1 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		matrix2 = libmatrix.Matrix  ([[5, 6], [7, 8]])
		matrixMul = libmatrix.Matrix([[19, 22], [43, 50]])
		self.assertEqual(matrixMul, matrix1 * matrix2, "Multiplication error (square matrices)")

		matrix1 = libmatrix.Matrix  ([[1, 2, 3],[4, 5, 6]])
		matrix2 = libmatrix.Matrix  ([[1, 2], [1, 0],[1,1]])
		matrixMul = libmatrix.Matrix([[6, 5], [15, 14]])
		self.assertEqual(matrixMul, matrix1 * matrix2, "Multiplication error (rectangular matrices)")

	def testMatrixPowers(self):
		matrix1 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		matrixPow = libmatrix.Matrix([[1, 0], [0, 1]])
		self.assertEqual(matrixPow, matrix1 ** 0, "Exponentiation error (power of 0)")

		matrix1 = libmatrix.Matrix  ([[1, 2], [3, 4]])
		matrixPow = libmatrix.Matrix([[1, 2], [3, 4]])
		self.assertEqual(matrixPow, matrix1 ** 1, "Exponentiation error (power of 1)")

		matrixPow = libmatrix.Matrix([[7, 10], [15, 22]])
		self.assertEqual(matrixPow, matrix1 ** 2, "Exponentiation error (power of 2)")

if __name__ == '__main__':
	unittest.main()

