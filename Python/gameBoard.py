import random

board = ['1','2','3',
         '4','5','6',
         '7','8','9']

def printBoard():
    print ('',board[0],'\t', board[1],'\t', board[2],'\n\n', \
              board[3],'\t', board[4],'\t', board[5],'\n\n', \
              board[6],'\t', board[7],'\t', board[8])
    
def computerPlayer():
    rand=str(random.randint(1,9))
    if rand in board:
        j = board.index(rand)
        board[j] = 'O'
    else:
        for count in range(len(board)):
            if board[count].isdigit():
                computerPlayer()

def singlePlayer():
    #name = input('Enter your name, you depressing hermit: ')
    flag = 1
    turn = 1
    hCount = 0
    vCount = 0
    dlCount = 0
    drCount = 2
    
    while flag == 1:
        print('Turn ',turn,'. GO!')
        printBoard()
   
        selection = input('Select a position number: ')

        if selection in board:
            i = board.index(selection)
            board[i] = 'X'        
        else:
            print('That position has been used, idiot.')

        computerPlayer()

        for count in range(3): #check horizontal win
            if board[hCount] == board[hCount+1] == board[hCount+2]:   
                if board[hCount] == 'X':
                    print('YOU WIN... ALONE.')
                    flag = 0
                    win = 1
                elif board[hCount] == 'O':
                    print('YOU LOSE...'+\
                        'ALONE... AND TO A '+\
                        'RANDOM NUMBER GENERATOR.')
                    flag = 0
                else:
                    hCount=+3
 
        for count in range(3):
            if board[vCount] == board[vCount+3] == board[vCount+6]:#check vertical win
                if board[vCount] == 'X':
                    print('YOU WIN... ALONE.')
                    flag = 0
                elif board[vCount] == 'O':
                    print('YOU LOSE...'+\
                    'ALONE... AND TO A '+\
                    'RANDOM NUMBER GENERATOR.')
                    flag = 0
                    win = 1
            else:
                vCount=+1

        if board[dlCount] == board[dlCount+4] == board[dlCount+8]:#check left diag win
            if board[dlCount] == 'X':
                print('YOU WIN... ALONE.')
                flag = 0
            elif board[dlCount] == 'O':
                print('YOU LOSE...'+\
                    'ALONE... AND TO A '+\
                    'RANDOM NUMBER GENERATOR.')
                flag = 0

        elif board[drCount] == board[drCount+2] == board[drCount+4]:#check right diag win
            if board[drCount] == 'X':
                print('YOU WIN... ALONE.')
                flag = 0
            elif board[drCount] == 'O':
                print('YOU LOSE...'+\
                'ALONE... AND TO A '+\
                'RANDOM NUMBER GENERATOR.')
                flag = 0
        turn+=1
            
singlePlayer()
