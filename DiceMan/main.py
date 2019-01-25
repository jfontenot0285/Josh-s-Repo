#!/usr/bin/python3

from modules import infantry

def main():
    space_marine = infantry.Infantry(3,3,4,4,1,3)

    print("Space Marine stats:\n")
    print("BS  WS  S  T  A  Sv")

    print(space_marine.getbs(), space_marine.getws(), space_marine.gets(), \
            space_marine.gett(), space_marine.geta(), space_marine.getsv(), \
            sep="  ")

main()
