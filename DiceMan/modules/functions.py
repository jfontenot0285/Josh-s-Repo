from modules import statsclass, weaponstatsclass
import random, os

def set_stats():
    #characters
    Warrior = statsclass.Stats(4,3,4,4,5,1,3) #(Sh, Me, St, Vi, Hp, At, Ar)
    Ranger = statsclass.Stats(3,4,3,3,4,1,4)
    Tanker = statsclass.Stats(5,4,4,4,7,3,5)

    #ranged weapons
    rifle = weaponstatsclass.Weaponstats(4,1,1,0) #(St, At, Da, Ap)
    sniper = weaponstatsclass.Weaponstats(4,2,1,1)
    shotgun = weaponstatsclass.Weaponstats(4,2,1,0)

    #melee weapons
    bayonet = weaponstatsclass.Weaponstats(0,1,1,0)
    knife = weaponstatsclass.Weaponstats(1,0,1,1)
    hammer = weaponstatsclass.Weaponstats(0,2,1,0)

def roll_dice():
    x = random.randint(1,6)
    return x

def char_select(p):
    print("\n","*"*32, sep='')
    print("Player ",p," select your character:")
    print("*"*32)

    print(" \t1) Space Marine\n",\
          "\t2) Aeldari\n",\
          "\t3) Ork\n")

    sel = int(input())
    os.system('clear')

    return sel
 
def roll_off():
    print("\nPlayer 1, press enter to roll:")
    input()
    p1_roll = roll_dice()
    print("Player 1, you rolled a ", p1_roll, "!", sep='')
    print("="*25)

    print("\nPlayer 2, press enter to roll.")
    input()
    p2_roll = roll_dice()
    print("Player 2, you rolled a ", p2_roll, "!", sep='')
    print("="*25)

    print("\nPress enter to continue")
    input()
    os.system('clear')

    if(p1_roll == p2_roll):
        print("Rolls are tied! Roll again.")
        roll_off()

    return p1_roll, p2_roll

def seize_initiative(player):
    print("*"*37)
    print("\tSeize the initiative!")
    print("*"*37,'\n')
    print("Player", player, "will attempt to seize the initiative with a roll of 6!", \
            "Player", player, "press enter to roll: ")
    input()

    player_roll = roll_dice()

    print("Player", player, " you rolled a ", player_roll, "!\n",sep='')

    if (player_roll == 6):
        print("Player", player, "you have seized the initiative! Take the first turn.")
        turn = player

    else:
        print("Player", player, "you have failed to seize the initiative. Opponent takes",\
                "the first turn.")
        turn = "op"

    return turn
