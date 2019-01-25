class Infantry:
    def __init__(self, bs, ws, s, t, a, sv):
        self.__bs = bs
        self.__ws = ws
        self.__s = s
        self.__t = t
        self.__a = a
        self.__sv = sv

    def setbs(self, i):
        self.__bs = i

    def setws(self, i):
        self.__ws = i

    def sets(self, i):
        self.__s = i

    def sett(self, i):
        self.__t = i

    def seta(self, i):
        self.__a = i

    def setsv(self, i):
        self.__sv = i

    def getbs(self):
        return self.__bs

    def getws(self):
        return self.__ws

    def gets(self):
        return self.__s

    def gett(self):
        return self.__t

    def geta(self):
        return self.__a

    def getsv(self):
        return self.__sv
