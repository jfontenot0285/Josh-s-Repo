#!/usr/bin/python3

from modules import *

def main():

    p1 = functions.set_stats(1)
    p2 = functions.set_stats(2)

    print("*"*24)
    print("* Roll for first turn! *")
    print("*"*24)

    rolls = functions.roll_off()

    if (rolls[0] > rolls[1]):
        turn = 1
    else:
        turn = 2

    functions.shoot(p1[0])
main()
