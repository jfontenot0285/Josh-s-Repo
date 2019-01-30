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
    
    valid = 0
    while(valid == 0):
        print("\n","*"*32, sep='')
        print("Player ",p," select your character:")
        print("*"*32)
        
        print(" \t1) Warrior\n",\
              "\t2) Ranger\n",\
                "\t3) Tanker\n")

        flag = 0
        while(flag == 0):
            try:
                sel = int(input())
                flag = 1
            except ValueError:
                print('Enter a valid selection 1, 2, or 3:')

        if (sel == 1):
            sel = p, Warrior, rifle, bayonet
            valid = 1

        elif(sel == 2):
            sel = p, Ranger, sniper, knife
            valid = 1

        elif(sel == 3):
            sel = p, Tanker, shotgun, hammer
            valid = 1

        else:
            print('Enter a valid selection 1, 2, or 3:')

    clear_screen()
    return sel 

def roll_dice():
    x = random.randint(1,6)
    return x

def roll_off():
    print("Player 1, press enter to roll:")
    input()
    p1_roll = roll_dice()
    print("You rolled a ", p1_roll, "!", sep='')
    print('Press enter to continue...')
    input()
    clear_screen()

    print("Player 2, press enter to roll.")
    input()
    p2_roll = roll_dice()
    print("You rolled a ", p2_roll, "!", sep='')
    print("Press enter to continue...")
    input()
    clear_screen()

    if(p1_roll == p2_roll):
        print("Rolls are tied! Roll again.")
        roll_off()

    return p1_roll, p2_roll

def shoot(p,op):
    print('Player ',p[0],' fires upon player ',op[0],'. Roll to see if you hit!',sep='')
    print('Press enter to roll:')
    input()
    clear_screen()
    shoot_roll = roll_dice()
    if (shoot_roll >= p[1].get_Sh()):
        print('Player ',p[0],' successfully hits player ',op[0],'!',sep='')
        player_hit(p,op)
    else:
        print('Player missed!')
    print('Press enter to continue:')
    input()
    clear_screen()

def player_hit(p,op):
    print('Player ',p[0],', roll to see if your hit injures player',op[0],':',sep='')
    input()
    clear_screen()
    hit_roll = roll_dice()
    req_roll = injury_matrix(p,op)
    print('Player ',p[0],', your weapon\'s strength value is ',p[2].get_St(),\
            ', and player ',op[0],'\'s vitality value is ',op[1].get_Vi(),'.',sep='')
    print('You rolled a ',hit_roll,'.',sep='')

    if (hit_roll >= req_roll):
    	print('Player ',p[0],', you successfully injured player ',op[0],'!',sep='')
    	print('Press enter to continue:')
    	input()
    	clear_screen()
    	player_injured(p,op)

    else:
    	print('Player',p[0],', your shot did not inflict an injury.')
    	print('Press enter to continue:')
    	input()
    	clear_screen()

def player_injured(p,op):
    print('Player',op[0],', roll for an armor save!')
    print('Press enter to roll:')
    input()
    clear_screen()
    op_roll = roll_dice()
    print('Player',op[0],', you rolled a',op_roll,'!')
    print('Press enter to coninue:')
    input()
    clear_screen()
    print('Player ',p[0],'\'s weapon\'s armor piercing value is ',p[2].get_Ap(),\
            ' and your armor value is ',op[1].get_Ar(),'.',sep='')

    if (op_roll >= op[1].get_Ar()-p[2].get_Ap()):
        print('Player ',op[0],', your armor prevented your injury from inflicting a loss of\
                hit points!',sep='')

    else:
        print('Player ',op[0],', your injury inflicted a loss of ',p[2].get_Da(),' hit point')
        
        if(p[2].get_Da() > 1):
            print('s.')
        else:
            print('.')

        op[1].set_Hp(op[1].get_Hp()-p[2].get_Da())
        print('Your hit point total is now at',op[1].get_Hp())
        print('Press enter to continue:')
        input()

def injury_matrix(p,op):
    if (p[2].get_St() == op[1].get_Vi()):
        req_roll = 4

    elif (p[2].get_St() < op[1].get_Vi()):
        req_roll = 5

    elif (p[2].get_St() > op[1].get_Vi()):
        req_roll = 3

    elif (p[2].get_St() >= op[1].get_vi()*2):
        req_roll = 2

    return req_roll

def clear_screen():
    if (platform.system() == 'Windows'):
        os.system('cls')

    else:
        os.system('clear')
