/*
Kevin Nguyen
A small naive program to output primes up to a specific value
*/

//imports
import java.io.*;
import java.util.*;

//class
class TestSubstrings {
	//Global Declarations


	//main driver
	public static void main(String[] args) {
		//local declarations --can modifiy these--
		String a = "acd"; 
		String b = "cdabcdab";

		//apply function
		int result = getNumOfNeededStringsNaive(a, b);
		int result2 = getNumOfNeededStringsBetter(a, b); 
		System.out.println("Result (Naive): " + result);
		System.out.println("Result (Better): " + result);

	}

	//THIS APPROACH IS NAIVE
	//function to get the number times a string must be duplicated in sequence in order to get the substring
	public static int getNumOfNeededStringsNaive(String a, String b) {
		//Local declarations
		int i = 1;
		String tmp = a;
		Boolean doesNotWork = false;
		//b is the substring so length of b has to be smaller than a
		while (a.length() < b.length()) {
			a += tmp;
			i++;
		}
		//loop on bigger string a and on substring b; there must always be atleast a match
		while (!a.contains(b)) { //is substring b inside a? if not then loop and build string a until substring occurs (or fail after k tries)
			a += tmp;
			i++;
			//after 10 tries just exit loop and return -1...this limit is a soft limit
			if(i > 10) {
				doesNotWork = true;
				break;
			}
		}
		//return -1 since a match cannot be determined
		if(doesNotWork) {
			return -1; //return failure
		}
		return i; //return i
	}



	//THIS APPROACH IS BETTER
	//function to get the number times a string must be duplicated in sequence in order to get the substring
	public static int getNumOfNeededStringsBetter(String a, String b) {
		//Local declarations
		int i = 0;
		String tmp = b;
		Boolean doesNotWork = false;
		
		//a replaces all characters in b, if difference is greater than 0 then it is not possible for substring to exist
		while(i < a.length()) {
			tmp = tmp.replaceAll(Character.toString(a.charAt(i)), "");
			i++;
		}
		i = 1; //set ith value; first is already given
		//conditional from previous check; return -1 or continue if good
		if(tmp.length() != 0) {
			return -1; //return failure
		}
		else {
			tmp = a; //set tmp to be original a string
			//b is the substring so length of b has to be smaller than a
			while (a.length() < b.length()) {
				a += tmp; //concat strings
				i++;
			}
			//loop on bigger string a and on substring b; there must always be atleast a match
			while (!a.contains(b)) { //is substring b inside a? if not then loop and build string a until substring occurs (or fail after k tries)
				a += tmp; //concat strings
				i++;
			}
		}
		return i; //return ith value
	}
}
