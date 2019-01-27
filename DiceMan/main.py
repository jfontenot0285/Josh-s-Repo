#!/usr/bin/python3

from modules import *

def main():

    functions.set_stats()

    print("Player 1, select your character: \n\n")
    print(" \t1) Space Marine\n",\
          "\t2) Aeldari\n",\
          "\t3) Ork\n\n")

    p1 = int(input())

    print("Player 2, select your character: \n\n")
    print(" \t1) Space Marine\n",\
          "\t2) Aeldari\n",\
          "\t3) Ork\n\n")

    p2 = int(input())

    print("\nRoll for first turn!")
    print("\nPlayer 1, press any key to roll.")
    input()
    p1_roll = functions.roll_dice()

    print("\nPlayer 2, press any key to roll.")
    input()
    p2_roll = functions.roll_dice()

    print("Player 1's roll: ", p1_roll)
    print("Player 2's roll: ", p2_roll)

main()
