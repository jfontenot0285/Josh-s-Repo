# Joshua Fontenot 001123717

import datetime
import functions
from datetime import timedelta
from hash import Hash
from graph import Graph
from truck import Truck

# total worst case runtime complexity is O(N^5)
def main():
    # create hash object for accessing hash functions and the master package list
    # create graph object for accessing graph functions for calculating distances and closest neighbors
    # hash init is O(N), graph and truck init is O(1), assignments are all O(1)
    # total worst case for this block is O(N)
    ht = Hash()
    g = Graph()
    hub_address = "4001 South 700 East"
    # create truck object with starting address at the hub and initial miles driven of 0.0
    t2 = Truck(hub_address, 0.0)

    # set variables to be accessed later for start_time, current_time, and late arrival times
    # set the time delta to 15 minutes. times will be advanced 15 minutes every iteration
    # all_packages_delivered is a flag that determines when to exit the outermost while loop
    # initialize total miles driven to 0.0
    # assignments are O(1) making O(1) worst case
    start_time = datetime.time(8, 00, 00)
    current_time = datetime.time(8, 00, 00)
    late_arrival_905 = datetime.time(9, 15, 00)
    late_arrival_1020 = datetime.time(10, 30, 00)
    d = timedelta(minutes=15)
    all_packages_delivered = False
    total_miles_driven = 0.0

    # Keeping looping until all packages are delivered (both trucks are empty and hub is empty)
    # while loop iterates until condition is met - N times
    # total worst case time complexity is O(N)*O(N^4)=O(N^5)
    while not all_packages_delivered:
        total_miles_driven = t2.total_miles_driven
        print("CURRENT TIME IS: ", current_time)
        print("TRUCK 2 CARRYING: ", t2.current_packages)
        print(*ht.get_all_packages(), sep="\n")

        # all non-delayed packages are at the hub at 8am
        # 1 outer if comparison O(1) worst case
        # outer for iterates constant times O(1) worst case
        # inner if checks constant times with append O(1) each time, total O(1) worst case
        if current_time == start_time:
            for p in range(1, 41):
                # packages 6, 9, 25, 28, 32 do not arrive until later (spec notes)
                if p not in (6, 9, 25, 28, 32):
                    # append id and its corresponding address to package list
                    ht.hub_package_list.append([p, ht.get_value(p)[0]])

        # packages arriving at hub at 9:05am
        # 1 if comparison * constant number of for iterations containing constant number of assignments
        # worst case is O(1)
        if current_time == late_arrival_905:
            for p in (6, 25, 28, 32):
                ht.hub_package_list.append([p, ht.get_value(p)[0]])

        # package address updated at 10:20am treated as late arrival
        # 1 if comparison and 1 assignment O(1)*O(1) = O(1) worst case
        if current_time == late_arrival_1020:
            ht.hub_package_list.append([9, ht.get_value(9)[0]])

        # if truck is at hub, load packages
        # nest if contains load_initial packages which is O(1)*[O(1)*O(N^3)] = O(N^3) worst case
        # load_initial_packages O(N^3) shown in functions.py
        # total worst case complexity in block is O(1)*[O(1)*O(N^3)+O(N^3)] = O(N^3)
        if t2.current_address == hub_address:
            # if beginning of day load initial packages
            if current_time == start_time:
                functions.load_initial_packages(ht, t2, [15, 14, 13, 16, 19, 20, 3, 18, 36, 38])
                # IDs 13,14,15,16,19,20 must go together (spec notes)
                # IDs 3,18,36,38 must be on truck 2 (spec notes)
            # current time not necessarily start time; trucks returning to hub or loading until full
            # load more packages is O(N^4) as shown in functions.py
            functions.load_more_packages(g, ht, t2, hub_address)
            # functions.load_more_packages(g, ht, t2, hub_address)

        # trucks will advance 4.5 miles every 15 minutes at 18mph
        # drive is O(1) as shown in functions.py
        functions.drive(t2)
        # functions.drive(t2)

        # checks if remaining miles to destination are <= 0, drops off packages if true
        # drop_off_packages is O(N^3) as shown in functions.py
        functions.drop_off_packages(g, ht, t2, hub_address)
        # functions.drop_off_packages(g, ht, t2, hub_address)

        # checks if remaining miles to hub are <= 0 when trucks need to refill
        # 1 comparison * check_hub_distance is O(1), so O(1)*O(1) = O(1)
        if t2.target_address[0] == hub_address:
            functions.check_hub_distance(t2, hub_address)
        # functions.check_hub_distance(t2, hub_address)

        # if hub and trucks are all empty, all packages have been delivered
        # constant number of comparisons * assignment is O(1)*O(1) = O(1) worst case
        if len(ht.hub_package_list) + len(t2.current_packages) + len(t2.current_packages) == 0:
            all_packages_delivered = True
        input("Press enter to advance time by 15 minutes")
        print("\n")
        current_time = functions.increment_time(current_time, d)

    # after hub and both trucks empty
    print("All packages delivered!")
    print(current_time)
    print("Truck 2 drove ", t2.total_miles_driven)
    print("Total miles driven: ", t2.total_miles_driven)
    print("\nFinal package update:")
    print(*ht.get_all_packages(), sep="\n")

main()