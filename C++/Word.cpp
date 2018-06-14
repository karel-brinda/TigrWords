#include "Word.h"

Word::Word(Substitution substitution, std::string initialWord, unsigned int levels) {
	this->substitution = substitution;
	this->levels = levels;

	stackLevels.push(initialWord);
	updateLevels();
}

Word::Word(Substitution substitution, std::string initialWord) {
	this->substitution = substitution;
	this->levels = WORD_MAX_SUB_LEVEL;

	stackLevels.push(initialWord);
	updateLevels();
}

void Word::printStack() {
	std::stack<std::string> stackLevels2 = stackLevels;

	while(!stackLevels2.empty()) {
		std::cout << stackLevels2.size() << ": ";
		printWord(stackLevels2.top());
		std::cout << std::endl;
		stackLevels2.pop();
	}
}

void Word::updateLevels() {
	delFreeLevels();
	while (stackLevels.size()!=levels) {
		addLevel();
	}

}

inline void Word::addLevel() {
	while (stackLevels.top()==std::string("") && !stackLevels.empty()) {
		stackLevels.pop();
	}
	//if(stackLevels.empty()) {
	//	return;
	//}
	//if(stackLevels.top()!=std::string("")) {
	std::string letter(stackLevels.top().substr(0,1));
	stackLevels.top().erase(0,1);
	//std::cout << " Letter: " << letter << std::endl;
	stackLevels.push(substitution.applySubstitution(&letter));
	//}
}

letter_type Word::getNextLetter() {
	letter_type a = stackLevels.top()[0];
	stackLevels.top().erase(0,1);
	updateLevels();
	return a;
}

void Word::delFreeLevels()
{
	while (stackLevels.top().empty())
		stackLevels.pop();
}

//parikhVectors Word::getParikhVectors(unsigned int n,unsigned int prefixLenght)
//{
//	parikhVectors pVs;
//	parikhVector pV;
//	pVs.alphabetCardinality=substitution.getAlphabetSize();
//
//	//first pV
//	for(unsigned int i=0;i<1;i++)
//		1; //pV[getNextLetter()]++;
//
//	//pVs.
//		//other pV
//
//
//	return pVs;
//}
