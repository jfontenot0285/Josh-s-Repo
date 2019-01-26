class Stats:
    def __init__(self, bs, ws, s, t, a, sv):
        self.__bs = bs
        self.__ws = ws
        self.__s = s
        self.__t = t
        self.__a = a
        self.__sv = sv

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
