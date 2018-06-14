#pragma once

#include "datatypes.h"
#include <cstdlib>
#include <iostream>
#include <string>
#include <map>
#include <new>
#include <stack>
#include <set>
#include <algorithm>

class Substitution {
private:
	rules_type rules;
	unsigned long int alphabetCardinality;
public:
	Substitution(void);
	void addRule(letter_type source, word_type image);
	word_type applySubstitution(word_type *source);
	void print();
	//void printAlphabet();
	unsigned int getAlphabetCardinality();
};
