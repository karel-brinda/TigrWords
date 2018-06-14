#! /usr/bin/env python3
# -*- coding: utf-8 -*-

#
# Karel Brinda (brinda@fjfi.cvut.cz)
#
# Department of Mathematics
# Faculty of Nuclear Sciences and Physical Engineering
# Czech Technical University in Prague
#


import unittest
import sys
sys.path.append("./../")
import libwords
import libmatrix

class WordTest(unittest.TestCase):
	def testInitialPrefix(self):
		# bad type
		self.assertRaises(TypeError, libwords.Word, 1 )


class WordSubstitutionTest(unittest.TestCase):
	def testSubstitution(self):
		# not dict
		self.assertRaises(TypeError, libwords.WordSubstitution, 4 )

		# not empty
		self.assertRaises(ValueError, libwords.WordSubstitution, { } )

		# to not tuple
		self.assertRaises(TypeError, libwords.WordSubstitution, { 'A': 'A' } )


	def testAlphabet(self):
		# letters are strings
		word = libwords.WordSubstitution({'A':('A','B'),'B':('A',)})
		self.assertEqual(word.alphabet, ('A','B') )

		# letters are integers
		word = libwords.WordSubstitution({1:(1,2),2:(1,)})
		self.assertEqual(word.alphabet, (1,2) )


	def testSubstitutionMatrix(self):
		# matrix of the substitution
		word = libwords.WordSubstitution({0:(0,1),1:(0,2),2:(0,)})
		self.assertEqual(word.substitutionMatrix, libmatrix.Matrix(
			[	[1,1,0],
				[1,0,1],
				[1,0,0]	]))
		
		self.assertNotEqual(word.substitutionMatrix, libmatrix.Matrix(
			[	[0,0,0],
				[0,0,0],
				[0,0,0]	]))
# ?co jsem to tu kdysi vyvádìl?
#	def testAbelianComplexity(self):
#		word = libwords.WordKBonacci(2)
#		word.getFactors(10**3)
#
#		for i in word.factors:
#			print (i,len(word.factors[i]),word.factors[i])

class KnownPrefixes(unittest.TestCase):
	knownPrefixes = (
		# TODO: BinaryNonPisot word
		(libwords.WordBinaryNonPisot(),[
				0, 1, 0, 0, 0, 0, 1, 0, 1, 0,
				1, 0, 1, 0, 0, 0, 0, 1, 0, 0,
				0, 0, 1, 0, 0, 0, 0, 1, 0, 0,
			]),

		# TODO: Chacon word
		# TODO: Chacon2 word
		# TODO: Chacon3 word
		# TODO: Chacon4 word

		# Fibonacci word
		(libwords.WordKBonacci(2),[
				0, 1, 0, 0, 1, 0, 1, 0, 0, 1,
				0, 0, 1, 0, 1, 0, 0, 1, 0, 1,
				0, 0, 1, 0, 0, 1, 0, 1, 0, 0,
			]),

		# Tribonacci word
		(libwords.WordKBonacci(3),[
				0, 1, 0, 2, 0, 1, 0, 0, 1, 0,
				2, 0, 1, 0, 1, 0, 2, 0, 1, 0,
				0, 1, 0, 2, 0, 1, 0, 2, 0, 1,
			]),

		# TODO: NonSimpleParry word

		# PeriodDoubling word
		(libwords.WordPeriodDoubling(),[
				0, 1, 0, 0, 0, 1, 0, 1, 0, 1,
				0, 0, 0, 1, 0, 0, 0, 1, 0, 0,
				0, 1, 0, 1, 0, 1, 0, 0, 0, 1,
			]),

		# TODO: RudinShapiro word
		# TODO: SimpleParry word

		# ThueMorse word
		(libwords.WordThueMorse(),[
				0, 1, 1, 0, 1, 0, 0, 1, 1, 0,
				0, 1, 0, 1, 1, 0, 1, 0, 0, 1,
				0, 1, 1, 0, 0, 1, 1, 0, 1, 0,
			]),

		# TODO: ThueMorse2 word
		# TODO: ThueMorse3 word
		# TODO: ThueMorse4 word
	)

	def testToKnownPrefixes(self):
		for (word,prefix) in self.knownPrefixes:
			word.generatePrefix(30);
			self.assertEqual(word.prefix,prefix)

if __name__ == '__main__':
	unittest.main()
