class Stats:
    def __init__(self, Sh, Me, St, Vi, Hp, At, Sv):
        self.__Sh = Sh
        self.__Me = Me
        self.__St = St
        self.__Vi = Vi
        self.__Hp = Hp
        self.__At = At
        self.__Sv = Sv

    def get_Sh(self):
        return self.__Sh

    def get_Me(self):
        return self.__Me

    def get_St(self):
        return self.__St

    def get_Vi(self):
        return self.__Vi

    def get_Hp(self):
        return self.__Hp

    def get_At(self):
        return self.__At

    def get_Sv(self):
        return self.__Sv

    def set_Sh(self, i):
        self.__Sh = i

    def set_Me(self, i):
        self.__Me = i

    def set_St(self, i):
        self.__St = i

    def set_Vi(self, i):
        self.__Vi = i

    def set_Hp(self, i):
        self.__Hp = i

    def set_At(self, i):
        self.__At = i

    def set_Sv(self, i):
        self.__Sv = i
