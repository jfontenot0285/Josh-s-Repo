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
        player_turn = 1
    else:
        player_turn = 2
    
    end_game = 0
    while (end_game == 0):
        print ('Round begin!',\
                'Player',player_turn,', take your shot!')
        print('Press enter to shoot:')
        input()
        functions.clear_screen()

        if(player_turn == 1):
            functions.shoot(p1,p2)
            player_turn = 2
        else:
            functions.shoot(p2,p1)
            player_turn = 1
main()
