class BankAccount:
    def __init__(self, bal, int_rate):
        self.__balance=bal
        self.__interestRate=int_rate

    def deposit(self, dep):
        self.__balance+=dep

    def withdraw(self, wit):
        self.__balance-=wit

    def interest(self):
        self.__balance=(self.__balance*self.__interestRate)+self.__balance

    def __str__(self):
        return 'Your balance is now $' + format(self.__balance, ',.2f')

def main():
    start_bal= float(input('Enter starting balance: '))
    int_rate = float(input('Enter interest rate: '))

    savings = BankAccount(start_bal, int_rate)

    flag = 1
    while flag==1:
        add = int(input('Would you like to deposit or withdraw? 1 for deposit, '\
                    '2 for withdraw: '))

        if add == 1:
            dep = float(input('Enter the amount you would like to deposit: '))
            savings.deposit(dep)
            savings.interest()
            print (savings)            
            flag2=int(input('Would you like to make another transaction? '\
                           '1 for yes, any other key for no: '))
            if flag2!=1:
                flag = 0      

        elif add == 2:
            withdraw= float(input('Enter the amount to be withdrawn: '))
            savings.withdraw(withdraw)
            print(savings)

            flag3=int(input('Would you like to make another transaction?'\
                           '1 for yes, any other key for no: '))

            if flag3!=1:
                flag = 0 

        else:
            print('Enter 1 or 2')

main()
    
        
        
    
        
