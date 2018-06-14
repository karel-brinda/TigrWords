/*

Karel Brinda (karel.brinda@fjfi.cvut.cz)

Department of Mathematics
Faculty of Nuclear Sciences and Physical Engineering
Czech Technical University in Prague 


TEMPLATE (change configurational part)
Program prints out the Parikh vectors of fixed point of a primitive substitution.

*/

#include <cstdlib>
#include <iostream>
#include <string>
#include <map>
#include <new>
#include <stack>
#include <vector>
#include "Substitution.h"
#include "Word.h"

#define COMMENT_PREFIX "# "

/*
	for DEBUG MODE uncomment the following line
*/

//#define DEBUG 0



int main(int argc, char *argv[]) {

	pvset_type pvektory;
	pv_type current_pv(sub.getAlphabetCardinality()+1,0);


	for (unsigned i=0;i<n;i++) {
		letter_type current_letter = wrd1.getNextLetter();
		current_pv[current_letter]+=1;
	}

	pvektory.insert(current_pv);

	//std::cout << std::endl;

	for(unsigned i=0;i<max_n-n;i++) {
		letter_type current_letter1 = wrd1.getNextLetter();
		letter_type current_letter2 = wrd2.getNextLetter();
		current_pv[current_letter1]+=1;
		current_pv[current_letter2]-=1;
		pvektory.insert(current_pv);

/*
#ifdef DEBUG
		if(i%10000 == 0){
			std::cout << i << " ";
		}
#endif
*/
	}

	//std::cout << std::endl;
	pv_type *pvvv = new pv_type;
	pvset_type::iterator it;
#ifdef DEBUG
	std::cout  << COMMENT_PREFIX  << std::endl;
	std::cout  << COMMENT_PREFIX << " Parikh vectors"  << std::endl;
#endif

	for ( it=pvektory.begin() ; it != pvektory.end(); it++ ) {
		*pvvv = (*it);
		for (unsigned i =0;i<sub.getAlphabetCardinality();i++){
			std::cout << " " << (*pvvv)[i+1];
		}
		std::cout << std::endl;
	}		

#ifdef DEBUG
	std::cout << COMMENT_PREFIX;
	system("pause");
#endif

	system("pause");

}
