from modules import statsclass, weaponstatsclass
import random, os, platform

def set_stats(p):
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
    
    valid = 'n'
    while(valid == 'n'):
        print("\n","*"*32, sep='')
        print("Player ",p," select your character:")
        print("*"*32)
        
        print(" \t1) Space Marine\n",\
              "\t2) Aeldari\n",\
                "\t3) Ork\n")

        sel = int(input())

        if (sel == 1):
            sel = Warrior, rifle, bayonet
            valid = 'y'

        elif(sel == 2):
            sel = Ranger, sniper, knife
            valid = 'y'

        elif(sel == 3):
            sel = Tanker, shotgun, hammer
            valid = 'y'

        else:
            print('Enter a valid selection 1, 2, or 3:')

    clear_screen()
    return sel 

def roll_dice():
    x = random.randint(1,6)
    return x

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
    clear_screen()

    if(p1_roll == p2_roll):
        print("Rolls are tied! Roll again.")
        roll_off()

    return p1_roll, p2_roll

def shoot(player):
    print('Player fires upon opponent. Roll to see if you hit!')
    print('Press enter to roll:')
    input()
    clear_screen()
    shoot_roll = roll_dice()
    if (shoot_roll >= player.get_Sh()):
        print('Player successfully hits opponent!')
    else:
        print('Player missed!')
    print('Press enter to continue:')
    input()
    clear_screen()

def clear_screen():
    if (platform.system() == 'Windows'):
        os.system('cls')

    else:
        os.system('clear')
