from statsclass import statsclass

class Weaponstats(statsclass):
	def __init__(self, BS, WS, S, T, A, Sv, D, Ap):
		statsclass.__init__(self, BS, WS, S, T, A, Sv)
		self.__D = D
		self.__Ap = Ap
