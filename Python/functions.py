# functions.py contains functions called from main
import datetime

# use time increment function to advance time forward every time user presses enter
# assignments all O(1), total worst case time complexity O(1)
def increment_time(time, timedelta):
    start = datetime.datetime(
        2020, 1, 1,
        hour=time.hour, minute=time.minute, second=time.second)
    end = start + timedelta
    return end.time()

# load_initial_packages takes hash object, truck object, and list of IDs then loads the IDs into the respective truck
# total worst case runtime complexity is O(1)+O(N^3)+O(N)+O(N^3)+O(1) = O(N^3)
def load_initial_packages(h, t, id_list):
    # 2 initial assignment are O(1)+O(1) = O(1)
    # outer for loop iterates N times, inner for loop iterates N times for each outer, O(N^2)
    # 1 if comparison contains 1 append and 1 remove, O(1)*[O(1)+O(N)] = O(1)*O(N) = O(N)
    # this is done inside inner for loop, so occurs N times for each inner loop iteration at worst case - O(N^2)
    # this creates O(N^2) for each iteration of outer loop making the whole for section O(N^3)
    # load_packages is O(N) worst case as shown in truck.py
    initial_packages = []
    initial_package_ids = id_list  # must travel together per special notes
    for pid in initial_package_ids:
        for item in h.hub_package_list:
            # if item id matches truck's initial package id
            if item[0] == pid:
                initial_packages.append(item)
                h.hub_package_list.remove(item)
    # set the status of loaded packages to "in route"
    # for iterates N number of times with 1 assignment each iteration, O(N) worst case
    for i in initial_packages:
        h.set_status(i[0], "IN ROUTE")
    t.load_packages(initial_packages)

    # find packages with same address as currently loaded, store in a list
    # 1 assignment is O(1)
    # outer for loop iterates N times, inner loop iterates N times for each outer iteration, O(N^2) worst case
    # 1 if comparison contains 1 append and 1 remove, O(1)*[O(1)+O(N)] = O(1)*O(N) = O(N)
    # this is done inside inner for loop, so occurs N times for each inner loop iteration at worst case - O(N^2)
    # this creates O(N^2) for each iteration of outer loop making the whole for section O(N^3)
    packages_same_address = []
    for i in t.current_packages:
        for j in h.hub_package_list:
            # if address in payload item matches address in hub package list
            if i[1] == j[1]:
                # append that item to same_address_list, remove from hub
                packages_same_address.append(j)
                h.hub_package_list.remove(j)
    # set the status of loaded packages to "in route"
    # for iterates N number of times with 1 assignment each iteration, O(N) worst case
    for p in packages_same_address:
        h.set_status(p[0], "IN ROUTE")
    # load the packages from list
    # load_packages is O(1) as shown in truck.py
    t.load_packages(packages_same_address)

# total worst case runtime complexity is O(N^4)
def load_more_packages(g, h, t, hub_address):
    # trucks returning for more packages or loading until full
    # load packages as long as hub is not empty and truck is not full
    # while iterates N times * O(1)+O(N)+O(N^2)+O(N)+O(N^3)+O(N^3)+O(1)+O(N) = O(N^4) worst case
    while not len(h.hub_package_list) == 0 and not len(t.current_packages) == t.capacity:
        # 2 assignments = O(1)+O(1) = O(1) worst case
        # for loop iterates N times with 1 assignment per iteration = O(N)*O(1) = O(N) worst case
        hub_tmp_address_list = []  # stores lists containing package ID and its corresponding address
        truck_load_list = []  # stores lists of IDs and corresponding addresses to be added to truck
        # create list of addresses remaining at hub
        # for loop iterats N times with 1 append per iteration, O(N)*O(1) = O(N)
        for k in h.hub_package_list:
            hub_tmp_address_list.append(k)

        # get next closest address from hub that is in hub_tmp_addr_list
        # closest_address is a list [address, distance]
        # assignment and get_closest_address are O(1)+O(N^2)
        closest_address = g.get_closest_address(hub_address, hub_tmp_address_list)
        # get all keys associated with the closest address
        # get_keys is O(N)
        closest_address_key_list = h.get_keys(closest_address[0])
        # check if those keys are still at hub
        # outer for loop iterates N times, inner loop iterates N times for each outer iteration, O(N^2) worst case
        # 1 if comparison nested in 1 if comparison  with 1 append and 1 remove
        # O(1)*{O(1)*[O(1)+O(N)]} = O(1)[O(1)*O(N)] = O(1)*O(N) = O(N) worst case
        # this is done inside inner for loop, so occurs N times for each inner loop iteration at worst case - O(N^2)
        # this creates O(N^2) for each iteration of outer loop making the whole for section O(N^3)
        for i in range(len(closest_address_key_list)):
            for j in hub_tmp_address_list:
                if j[0] == closest_address_key_list[i]:
                    # check if adding the new address will exceed truck capacity when multiple truck_load items
                    # append a new item only if adding it to current load list does not exceed truck capacity
                    if len(t.current_packages) + len(truck_load_list) < t.capacity:
                        truck_load_list.append(j)
                        h.hub_package_list.remove(j)
        # set the status of loaded packages to "in route"
        # for iterates N number of times with 1 assignment each iteration, O(N) worst case
        for l in truck_load_list:
            h.set_status(l[0], "IN ROUTE")
        # load packages is O(1) as shown in truck.py
        t.load_packages(truck_load_list)

        # if truck is full or hub is empty (before breaking from while), set target address
        # 1 comparison with get closest address and 1 assignment is O(1)*[O(N^2)+O(1)] = O(1)*O(N) = O(N) worst case
        if len(h.hub_package_list) == 0 or len(t.current_packages) == t.capacity:
            t.target_address = g.get_closest_address(t.current_address, t.current_packages)
            t.miles_to_target_address = round(t.target_address[1] + t.extra_miles, 2)

# total worst case time complexity is O(N^3)
def drop_off_packages(g, h, t, hub_address):
    # while remaining distance is <= 0, truck has traveled far enough to deliver package(s)
    # 1 assignment is O(1)
    t_is_done = False
    # while loop iterates N times * [O(1)+O(N)+O(N)+O(N)+O(N^2)] = O(N^3)
    while t.miles_to_target_address <= 0 and not t.target_address[0] == hub_address and not t_is_done:
        # extra miles are added to next target. if negative, truck is already that much closer
        # 1 comparison and assignment is O(1)+O(1) = O(1)
        t.extra_miles = t.miles_to_target_address
        t.current_address = t.target_address[0]

        # offload all packages destined for this address
        # 1 assignment is O(1), for loop iterates N times with 1 comparison containing 1 assignment each iteration
        # for loop is O(N)*O(1)*O(1) = O(N) worst case
        # deliver packages is O(N) worst case as shown in truck.py
        # total worst case runtime complexity for this block is O(1)+O(N)+O(N) = O(N)
        temp_delivery_list = []
        for a in t.current_packages:
            if a[1] == t.current_address:
                temp_delivery_list.append(a)
        t.deliver_packages(temp_delivery_list)

        # set the delivered packages statuses to DELIVERED in hash table
        # for loop iterates N times with 1 assignment each iteration, O(N)*O(1) = O(N) worst case
        for k in temp_delivery_list:
            # IDs 18 and 40 are not getting marked
            h.set_status(k[0], "DELIVERED")

        # if truck is empty, check if more packages are at hub
        # outer if is O(1), inner if is O(1)* 4 assignments * get_edge, which is O(1) as shown in graph.py
        # inner else is O(1) with 1 assignment, O(1)*O(1)
        # outer else is O(1) with 2 assignment and get_closest_address which is O(N^2), so O(N^2) worst case
        # out else is O(1)*[O(1)+O(1)+O(N^2)] = O(1)*O(N^2) = O(N^2) worst case
        # whole block is O(N^2) worst case
        if len(t.current_packages) == 0:
            if not len(h.hub_package_list) == 0:
                # go back to hub to get more package
                distance_to_hub = g.get_edge(t.current_address, hub_address)
                t.target_address = (hub_address, distance_to_hub)
                t.miles_to_target_address = round(t.target_address[1] + t.extra_miles, 2)
                # set extra miles back to 0 after applying to miles_to_target_formula
                t.extra_miles = 0.0
            # hub and truck are both empty - this truck is done
            else:
                t_is_done = True
        # truck is not empty, set target to next closest address
        else:
            # set new target address to closest address from current address
            t.target_address = g.get_closest_address(t.current_address, t.current_packages)
            t.miles_to_target_address = round(t.target_address[1] + t.extra_miles, 2)

def drive(t):
    # truck drives 4.5 miles in 15 minutes at 18mph
    # negative extra miles are "added" to reduce distance since truck has traveled that much farther
    # 1 assignment O(1) + 1 if comparison O(1)*inner assignment O(1) = O(1) worst case
    t.total_miles_driven = t.total_miles_driven + 4.5
    if t.miles_to_target_address > 0:
        t.miles_to_target_address = round(t.miles_to_target_address - 4.5, 2)

def check_hub_distance(t, hub_address):
    # if miles to hub are <= 0, truck has traveled enough to be at hub
    # 1 if comparison * 2 inner assignments = O(1)*[O(1)+O(1)] = O(1)*O(1) = O(1) worst case
    if t.miles_to_target_address <= 0:
        t.current_address = hub_address
        t.extra_miles = t.miles_to_target_address