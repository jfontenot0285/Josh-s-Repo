class Stats:
    def __init__(self, BS, WS, S, T, W, A, Sv):
        self.__BS = BS
        self.__WS = WS
        self.__S = S
        self.__T = T
        self.__W = W
        self.__A = A
        self.__Sv = Sv

    def get_BS(self):
        return self.__BS

    def get_WS(self):
        return self.__WS

    def get_S(self):
        return self.__S

    def get_T(self):
        return self.__T

    def get_W(self):
        return self.__W

    def get_A(self):
        return self.__A

    def get_Sv(self):
        return self.__Sv

    def set_BS(self, i):
        self.__BS = i

    def set_WS(self, i):
        self.__WS = i

    def set_S(self, i):
        self.__S = i

    def set_T(self, i):
        self.__T = i

    def set_W(self, i):
        self.__W = i

    def set_A(self, i):
        self.__A = i

    def set_Sv(self, i):
        self.__Sv = i
