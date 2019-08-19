import random

flag = 1

while flag == 1:
    indFirst = random.randint(0,9)
    indLast = random.randint(0,9)

    firstList = ('Philomon', 'Hezekia', 'Myles', 'Samuel', 'Jasper', 'Moses', \
             'Oceanus', 'Rufus', 'Eliab', 'Hercules')

    lastList = ('Dixon', 'Mears', 'Curley', 'Smith', 'Watt', 'Roland', \
            'Templeton', 'McCoy', 'Bond', 'Aigar')

    firstGenerated = firstList[indFirst]
    lastGenerated = lastList[indLast]

    flag = int(input(firstGenerated + ' ' + lastGenerated +\
          '\n\nGenerate another name? 1 for yes, 0 to quit: '))
