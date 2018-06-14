#pragma once

#define WORD_MAX_SUB_LEVEL 1000
#define MAX_SIZE_OF_ALPHABET 255

#include "datatypes.h"
#include "Substitution.h"

#include <cstdlib>
#include <iostream>
#include <string>
#include <map>
#include <new>
#include <stack>
#include <set>
#include <vector>

class Word {
private:
	unsigned int currentLevel;
	unsigned int levels;
	std::stack<std::string> stackLevels;
	Substitution substitution;
	void addLevel();
	void updateLevels();
	void delFreeLevels();
public:
	Word(Substitution substitution, std::string initialWord, unsigned int levels);
	Word(Substitution substitution, std::string initialWord);
	void printStack();
	//pvset_type getParikhVectors(unsigned int n,unsigned int prefixLenght);
	letter_type getNextLetter();
};