from modules import statsclass, weaponclass

def set_stats():
	spacemarine = statsclass.Stats(3,3,4,4,1,3)
	craftworld = statsclass.Stats(3,3,3,3,1,4)
	ork = statsclass.Stats(5,4,4,4,3,5)

	boltgun = weaponclass.Weaponstats(4,1,1,0)
	shuriken = weaponclass.Weaponstats(4,2,1,1)
	shoota = weaponclass.Weaponstats(4,2,1,0)

	chainsword = weaponclass.Weaponstats(0,1,1,0)
	aeldarimelee = weaponclass.Weaponstats(1,0,1,1)
	orkmelee = weaponclass.Weaponstats(0,2,1,0)
