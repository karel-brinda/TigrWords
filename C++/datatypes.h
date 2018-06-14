#pragma once

//#include "Word.h"
#include <vector>
#include <set>
#include <map>
#include <string>

//for a letter
//typedef unsigned char letter_type;
typedef unsigned char letter_type;

//vector of letters
//typedef std::vector<letter_type> word_type;
//typedef std::basic_string<letter_type> word_type;
typedef std::string word_type;

//rules of substitution
typedef std::map<letter_type,word_type> rules_type;

//Parikh vector
typedef std::vector<long> pv_type;

//set of Parikh vectors
typedef std::set<pv_type> pvset_type;

#define printWord(xxx_word) { for (unsigned xxx_i=0;xxx_i<(xxx_word).size();xxx_i++) { std::cout << (unsigned) ((xxx_word)[xxx_i]-1) << " "; }}
#define printLetter(xxx_letter) {{ std::cout << (unsigned) (xxx_letter-1); }}