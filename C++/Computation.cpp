#include "Computation.h"


Computation::Computation(void){
	sub.addRule('\1', "\1\2");		//substitution
	sub.addRule('\2', "\1\3");
	sub.addRule('\3', "\1");

	initialWord = "\1";

	recurrenceConstant = 10;
}

int Computation::menu() {
	std::cout << "s - Change the substitution" << std::endl;
	std::cout << "i - Change the initial letter" << std::endl;
	std::cout << "r - Change the constant of linear reccurrence" << std::endl;
}

unsigned Computation::getAbelianComplexity(unsigned n) {
	Word wrd1 = Word(sub,initialWord);
	Word wrd2 = Word(sub,initialWord);

	pv_type initial_pv(sub.getAlphabetCardinality()+1,0);

	for (unsigned i=0;i<n;i++) {
		letter_type current_letter = wrd1.getNextLetter();
		initial_pv[current_letter]+=1;
	}



	for(unsigned i=0;i<n*recurrenceConstant;i++) {
		
	}

}
Computation::~Computation(void) {
}

/////////////////////////////////////////////
/////////////////////////////////////////////
/////////////////////////////////////////////

ParikhGraph::ParikhGraph(unsigned alphabetCardinality) {
	this->alphabetCardinality = alphabetCardinality;
}

void ParikhGraph::next_pv(letter_type oldLetter, letter_type newLetter) {
	if( (current_pvFrame->neighbours)[oldLetter*alphabetCardinality + newLetter] != 0) {
		current_pvFrame = (current_pvFrame->neighbours)[oldLetter*alphabetCardinality + newLetter];
	} else {
		pvFrame *new_pvFrame = new pvFrame;
		new_pvFrame->entries = new int(alphabetCardinality);
		new_pvFrame->neighbours = new (pvFrame*[alphabetCardinality*alphabetCardinality]);

		for(unsigned i=0;i<alphabetCardinality;i++) {
			new_pvFrame->entries[i] = new_pvFrame->entries[i];
		}

		(current_pvFrame->neighbours)[oldLetter*alphabetCardinality + newLetter] = new_pvFrame;
		//todo: doplnit dalsi hrany
		//todo: pridat do seznamu hran
		current_pvFrame = (current_pvFrame->neighbours)[oldLetter*alphabetCardinality + newLetter];
	}

}
