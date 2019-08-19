def mainMenu():
    for i in range(25):
        print('*',end='')
    print('\n\tMAIN MENU')
    for i in range(25):
        print('*',end='')

    print('\n1   Single Player\n'+\
          '2   Multi Player\n'+\
          '3   Quit')

def validateMenu(x):
    try:
        x = int(x)
        if x < 1 or x > 3:
            selectionMessage = ('\nNot a valid selection\n')
            return selectionMessage, x
        else:
            selectionMessage = ('')
            return selectionMessage, x
        
    except ValueError:
        selectionMessage = ('\nNot a valid selection\n')
        return selectionMessage, x

def singlePlayer():
    print('Enter your name: ')
    print('Select position number: ')
    gameBoard()
