#include "Substitution.h"

Substitution::Substitution() {
	alphabetCardinality=0;
	return;
}	

void Substitution::addRule(letter_type source, word_type image) {
	rules[source]=image;
	alphabetCardinality = std::max(
		std::max ((int)source,(int)alphabetCardinality),
		(int) *(std::max_element(image.begin(),image.end()))
		);
}

word_type Substitution::applySubstitution(word_type *source) {
	word_type image;

	image.reserve( source->size() * 2 );

	for (unsigned i = 0;i<source->size();i++) {
		//std::cout <<"  aa:";
		//printLetter((*source)[i]);
		image.append( rules[(*source)[i]] );
		//std::cout <<"  bb:";
		//printWord( rules[(*source)[i]] );
	}

	//std::cout<<" vysledek ";
	//printWord(image);
	return image;
} 

void Substitution::print() {
	for (rules_type::iterator it=rules.begin() ; it != rules.end(); it++) {
		std::cout << "# ";
		printLetter (it->first);
		std::cout << " => ";
		printWord(it->second);
		std::cout << std::endl;
	}
}

unsigned int Substitution::getAlphabetCardinality()
{
	return alphabetCardinality;
}
