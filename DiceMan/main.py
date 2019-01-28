#!/usr/bin/python3

from modules import *
import os

def main():

    functions.set_stats()    
    p1 = functions.char_select(1)
    p2 = functions.char_select(2)

    print("*"*24)
    print("* Roll for first turn! *")
    print("*"*24)

    rolls = functions.roll_off()

    if (rolls[0] > rolls[1]):
        print("Player 2, would you like to seize the initiative?", \
                "1 for yes; 2 for no: ")
        seize_it = int(input())
        os.system('clear')

        if(seize_it == 2):
            turn = "p1"

        else:
            turn = functions.seize_initiative(2)

    else:
        print("Player 1, would you like to seize the initiative?", \
                "1 for yes; 2 for no: ")

        seize_it = int(input())
        os.system('clear')

        if(seize_it == 2):
            turn = "p2"

        else:
            turn = functions.seize_initiative(1)

main()
