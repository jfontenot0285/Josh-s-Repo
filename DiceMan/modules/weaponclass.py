from modules import statsclass

class Weaponstats(statsclass.Stats):
    def __init__(self, S, A, D, Ap):
        BS = 0
        WS = 0
        T = 0
        Sv = 0
        statsclass.Stats.__init__(self, BS, WS, S, T, A, Sv)
        self.__S = S
        self.__A = A
        self.__D = D
        self.__Ap = Ap

    def get_S():
        return self.__S

    def get_A():
        return self.__A

    def get_D():
        return self.__D

    def get_Ap():
        return self.__Ap
