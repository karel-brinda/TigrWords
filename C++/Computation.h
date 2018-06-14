#pragma once

#include "Word.h"
#include "Substitution.h"

class Computation
{
private:
	unsigned long recurrenceConstant=0;
	Substitution sub;
	word_type initialWord; // initial letter

	int menu();

public:
	Computation(void);
	~Computation(void);
	unsigned getAbelianComplexity(unsigned n);
	void run();
};

class ParikhGraph
{
	struct pvFrame {
		int *entries;
		(pvFrame*) *neighbours; 

	};

	unsigned alphabetCardinality;
	pvFrame *current_pvFrame;

public:
	ParikhGraph(unsigned alphabetCardinality);
	void next_pv(letter_type oldLetter, letter_type newLetter);
	~ParikhGraph(void);
};

