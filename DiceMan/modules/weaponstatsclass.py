from modules import statsclass

class Weaponstats(statsclass.Stats):
    def __init__(self, St, At, Da, Ap):
        Sh = 0
        Me = 0
        Vi = 0
        Hp = 0
        Ar = 0
        statsclass.Stats.__init__(self, Sh, Me, St, Vi, Hp, At, Ar)
        self.__St = St
        self.__At = At
        self.__Da = Da
        self.__Ap = Ap

    def get_St(self):
        return self.__St

    def get_At(self):
        return self.__At

    def get_Da(self):
        return self.__Da

    def get_Ap(self):
        return self.__Ap

    def set_St(self, i):
        self.__St = i

    def set_At(self, i):
        self.__At = i

    def set_Da(self, i):
        self.__Da = i

    def set_Ap(self, i):
        self.__Ap = i
