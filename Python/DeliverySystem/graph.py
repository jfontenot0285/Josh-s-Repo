# graph class contains the master list of vertices and edges
class Graph:
    # initialize graph object with empty dictionary and populate it
    # 1 assignment * populate_graph is O(1)*O(1) = O(1) worst case runtime complexity
    def __init__(self):
        self.vertex_dict = {}
        self.populate_graph()

    # add vertex to the graph as a nested dictionary
    # assign empty dictionary to list index is 1 assignment, O(1) worst case runtime complexity
    def add_vertex(self, vertex):
        self.vertex_dict[vertex] = {}

    # take source and destination vertices then add the weighted edge between them into sub-dictionary
    # 2 assignments are O(1)*O(1) = O(1) worst case time complexity
    def add_edge(self, src, dst, distance):
        self.vertex_dict[src][dst] = distance
        self.vertex_dict[dst][src] = distance

    # take source and destination vertices as input, output weighted edge between them
    # dictionary lookup is O(1) worst case runtime complexity
    def get_edge(self, src, dst):
        return self.vertex_dict.get(src, {}).get(dst)

    # get all vertices in graph
    # returning number of items in dictionary depends on dictionary size - O(N) worst case time complexity
    def get_vertices(self):
        return list(self.vertex_dict.keys())

    # return list of neighbors and distances
    def get_neighbor_list(self, current_address):
        # create list of all addresses connected to current_address - 1 assignment = O(1) worst case time complexity
        neighbor_list = []
        # single for loop depends on current_address with 1 assignment - O(N)*O(1) = O(N) worst case time complexity
        for a in self.vertex_dict[current_address].items():
            neighbor_list.append(a)
        return neighbor_list

    # check package address that is on the truck closest to current address
    # total worst case runtime complexity from all blocks is:
    # O(N) + O(N^2) + O(1) + O(N) + O(N * logN) + O(N) = O(N^2)
    def get_closest_address(self, current_address, address_list):
        # get master neighbor list for start_address; create empty list to store filtered neighbor list
        # 3 constant assignments + worst case from get_neighbor_list is O(1)*O(1)*O(1)*O(N) = O(N)
        all_neighbor_list = self.get_neighbor_list(current_address)
        truck_neighbor_list = []
        distance_list = []

        # filter neighbor list to neighbors only on truck
        # outer loop iterates len(address_list) times, inner loop iterates len(all_neighbor_list) times for each outer
        # O(N)*O(N) = O(N^2)
        # 1 constant comparison and 1 assignment = O(1)*O(1) = O(1)
        # total worst case runtime complexity is O(N^2)*O(1) = O(N^2)
        for i in range(len(address_list)):
            for j in range(len(all_neighbor_list)):
                if address_list[i][1] == all_neighbor_list[j][0]:
                    truck_neighbor_list.append(all_neighbor_list[j])

        # check for empty truck neighbor list and return any received address if true
        # this happens if address_list has entries all with same address, so all of them are closest
        # 1 comparison, 2 assignment is O(1)*O(1)*O(1) = O(1) worst case runtime complexity
        if len(truck_neighbor_list) == 0:
            d = self.get_edge(current_address, address_list[0][1])
            a = [address_list[0][1], d]
            return a
        # for loop iterates truck_neighbor_list number of times with 1 comparison and 1 assignment
        # O(N)*O(1)*O(1) = O(N) worst case runtime complexity
        for n in truck_neighbor_list:
            if n[1] != 0.0:
                distance_list.append(n[1])

        # sort distances in ascending order - python sort function is O(N*logN) worst case runtime complexity
        distance_list.sort()
        # traverse neighbor_list and match lowest distance to its corresponding address, return the pair
        # for loop iterates truck_neighbor_list number of times with 1 comparison
        # O(N)*O(1) = O(N) worst case runtime complexity
        for n in truck_neighbor_list:
            if n[1] == distance_list[0]:
                return n

    # create the master graph of addresses and distances
    # 1 function call of O(1)*number of assignments is O(1)*constant = O(1) worst case runtime complexity
    def populate_graph(self):
        # hard code each address as a vertex into a Vertex reference variable
        self.add_vertex("4001 South 700 East")
        self.add_vertex("1060 Dalton Ave S")
        self.add_vertex("1330 2100 S")
        self.add_vertex("1488 4800 S")
        self.add_vertex("177 W Price Ave")
        self.add_vertex("195 W Oakland Ave")
        self.add_vertex("2010 W 500 S")
        self.add_vertex("2300 Parkway Blvd")
        self.add_vertex("233 Canyon Rd")
        self.add_vertex("2530 S 500 E")
        self.add_vertex("2600 Taylorsville Blvd")
        self.add_vertex("2835 Main St")
        self.add_vertex("300 State St")
        self.add_vertex("3060 Lester St")
        self.add_vertex("3148 S 1100 W")
        self.add_vertex("3365 S 900 W")
        self.add_vertex("3575 W Valley Central Station bus Loop")
        self.add_vertex("3595 Main St")
        self.add_vertex("380 W 2880 S")
        self.add_vertex("410 S State St")
        self.add_vertex("4300 S 1300 E")
        self.add_vertex("4580 S 2300 E")
        self.add_vertex("5025 State St")
        self.add_vertex("5100 South 2700 West")
        self.add_vertex("5383 S 900 East #104")
        self.add_vertex("600 E 900 South")
        self.add_vertex("6351 South 900 East")

        # hard code the edge values (distances) for each address combination
        self.add_edge("4001 South 700 East", "4001 South 700 East", 0.0)
        self.add_edge("1060 Dalton Ave S", "4001 South 700 East", 7.2)
        self.add_edge("1330 2100 S", "4001 South 700 East", 3.8)
        self.add_edge("1488 4800 S", "4001 South 700 East", 11.0)
        self.add_edge("177 W Price Ave", "4001 South 700 East", 2.2)
        self.add_edge("195 W Oakland Ave", "4001 South 700 East", 3.5)
        self.add_edge("2010 W 500 S", "4001 South 700 East", 10.9)
        self.add_edge("2300 Parkway Blvd", "4001 South 700 East", 8.6)
        self.add_edge("233 Canyon Rd", "4001 South 700 East", 7.6)
        self.add_edge("2530 S 500 E", "4001 South 700 East", 2.8)
        self.add_edge("2600 Taylorsville Blvd", "4001 South 700 East", 6.4)
        self.add_edge("2835 Main St", "4001 South 700 East", 3.2)
        self.add_edge("300 State St", "4001 South 700 East", 7.6)
        self.add_edge("3060 Lester St", "4001 South 700 East", 5.2)
        self.add_edge("3148 S 1100 W", "4001 South 700 East", 4.4)
        self.add_edge("3365 S 900 W", "4001 South 700 East", 3.7)
        self.add_edge("3575 W Valley Central Station bus Loop", "4001 South 700 East", 7.6)
        self.add_edge("3595 Main St", "4001 South 700 East", 2.0)
        self.add_edge("380 W 2880 S", "4001 South 700 East", 3.6)
        self.add_edge("410 S State St", "4001 South 700 East", 6.5)
        self.add_edge("4300 S 1300 E", "4001 South 700 East", 1.9)
        self.add_edge("4580 S 2300 E", "4001 South 700 East", 3.4)
        self.add_edge("5025 State St", "4001 South 700 East", 2.4)
        self.add_edge("5100 South 2700 West", "4001 South 700 East", 6.4)
        self.add_edge("5383 S 900 East #104", "4001 South 700 East", 2.4)
        self.add_edge("600 E 900 South", "4001 South 700 East", 5.0)
        self.add_edge("6351 South 900 East", "4001 South 700 East", 3.6)

        self.add_edge("1060 Dalton Ave S", "1060 Dalton Ave S", 0.0)
        self.add_edge("1330 2100 S", "1060 Dalton Ave S", 7.1)
        self.add_edge("1488 4800 S", "1060 Dalton Ave S", 6.4)
        self.add_edge("177 W Price Ave", "1060 Dalton Ave S", 6.0)
        self.add_edge("195 W Oakland Ave", "1060 Dalton Ave S", 4.8)
        self.add_edge("2010 W 500 S", "1060 Dalton Ave S", 1.6)
        self.add_edge("2300 Parkway Blvd", "1060 Dalton Ave S", 2.8)
        self.add_edge("233 Canyon Rd", "1060 Dalton Ave S", 4.8)
        self.add_edge("2530 S 500 E", "1060 Dalton Ave S", 6.3)
        self.add_edge("2600 Taylorsville Blvd", "1060 Dalton Ave S", 7.3)
        self.add_edge("2835 Main St", "1060 Dalton Ave S", 5.3)
        self.add_edge("300 State St", "1060 Dalton Ave S", 4.8)
        self.add_edge("3060 Lester St", "1060 Dalton Ave S", 3.0)
        self.add_edge("3148 S 1100 W", "1060 Dalton Ave S", 4.6)
        self.add_edge("3365 S 900 W", "1060 Dalton Ave S", 4.5)
        self.add_edge("3575 W Valley Central Station bus Loop", "1060 Dalton Ave S", 7.4)
        self.add_edge("3595 Main St", "1060 Dalton Ave S", 6.0)
        self.add_edge("380 W 2880 S", "1060 Dalton Ave S", 5.0)
        self.add_edge("410 S State St", "1060 Dalton Ave S", 4.8)
        self.add_edge("4300 S 1300 E", "1060 Dalton Ave S", 9.5)
        self.add_edge("4580 S 2300 E", "1060 Dalton Ave S", 10.9)
        self.add_edge("5025 State St", "1060 Dalton Ave S", 8.3)
        self.add_edge("5100 South 2700 West", "1060 Dalton Ave S", 6.9)
        self.add_edge("5383 S 900 East #104", "1060 Dalton Ave S", 10.0)
        self.add_edge("600 E 900 South", "1060 Dalton Ave S", 4.4)
        self.add_edge("6351 South 900 East", "1060 Dalton Ave S", 13.0)

        self.add_edge("1330 2100 S", "1330 2100 S", 0.0)
        self.add_edge("1488 4800 S", "1330 2100 S", 9.2)
        self.add_edge("177 W Price Ave", "1330 2100 S", 4.4)
        self.add_edge("195 W Oakland Ave", "1330 2100 S", 2.8)
        self.add_edge("2010 W 500 S", "1330 2100 S", 8.6)
        self.add_edge("2300 Parkway Blvd", "1330 2100 S", 6.3)
        self.add_edge("233 Canyon Rd", "1330 2100 S", 5.3)
        self.add_edge("2530 S 500 E", "1330 2100 S", 1.6)
        self.add_edge("2600 Taylorsville Blvd", "1330 2100 S", 10.4)
        self.add_edge("2835 Main St", "1330 2100 S", 3.0)
        self.add_edge("300 State St", "1330 2100 S", 5.3)
        self.add_edge("3060 Lester St", "1330 2100 S", 6.5)
        self.add_edge("3148 S 1100 W", "1330 2100 S", 5.6)
        self.add_edge("3365 S 900 W", "1330 2100 S", 5.8)
        self.add_edge("3575 W Valley Central Station bus Loop", "1330 2100 S", 5.7)
        self.add_edge("3595 Main St", "1330 2100 S", 4.1)
        self.add_edge("380 W 2880 S", "1330 2100 S", 3.6)
        self.add_edge("410 S State St", "1330 2100 S", 4.3)
        self.add_edge("4300 S 1300 E", "1330 2100 S", 3.3)
        self.add_edge("4580 S 2300 E", "1330 2100 S", 5.0)
        self.add_edge("5025 State St", "1330 2100 S", 6.1)
        self.add_edge("5100 South 2700 West", "1330 2100 S", 9.7)
        self.add_edge("5383 S 900 East #104", "1330 2100 S", 6.1)
        self.add_edge("600 E 900 South", "1330 2100 S", 2.8)
        self.add_edge("6351 South 900 East", "1330 2100 S", 7.4)

        self.add_edge("1488 4800 S", "1488 4800 S", 0.0)
        self.add_edge("177 W Price Ave", "1488 4800 S", 5.6)
        self.add_edge("195 W Oakland Ave", "1488 4800 S", 6.9)
        self.add_edge("2010 W 500 S", "1488 4800 S", 8.6)
        self.add_edge("2300 Parkway Blvd", "1488 4800 S", 4.0)
        self.add_edge("233 Canyon Rd", "1488 4800 S", 11.1)
        self.add_edge("2530 S 500 E", "1488 4800 S", 7.3)
        self.add_edge("2600 Taylorsville Blvd", "1488 4800 S", 1.0)
        self.add_edge("2835 Main St", "1488 4800 S", 6.4)
        self.add_edge("300 State St", "1488 4800 S", 11.1)
        self.add_edge("3060 Lester St", "1488 4800 S", 3.9)
        self.add_edge("3148 S 1100 W", "1488 4800 S", 4.3)
        self.add_edge("3365 S 900 W", "1488 4800 S", 4.4)
        self.add_edge("3575 W Valley Central Station bus Loop", "1488 4800 S", 7.2)
        self.add_edge("3595 Main St", "1488 4800 S", 5.3)
        self.add_edge("380 W 2880 S", "1488 4800 S", 6.0)
        self.add_edge("410 S State St", "1488 4800 S", 10.6)
        self.add_edge("4300 S 1300 E", "1488 4800 S", 5.9)
        self.add_edge("4580 S 2300 E", "1488 4800 S", 7.4)
        self.add_edge("5025 State St", "1488 4800 S", 4.7)
        self.add_edge("5100 South 2700 West", "1488 4800 S", 0.6)
        self.add_edge("5383 S 900 East #104", "1488 4800 S", 6.4)
        self.add_edge("600 E 900 South", "1488 4800 S", 10.1)
        self.add_edge("6351 South 900 East", "1488 4800 S", 10.1)

        self.add_edge("177 W Price Ave", "177 W Price Ave", 0.0)
        self.add_edge("195 W Oakland Ave", "177 W Price Ave", 1.9)
        self.add_edge("2010 W 500 S", "177 W Price Ave", 7.9)
        self.add_edge("2300 Parkway Blvd", "177 W Price Ave", 5.1)
        self.add_edge("233 Canyon Rd", "177 W Price Ave", 7.5)
        self.add_edge("2530 S 500 E", "177 W Price Ave", 2.6)
        self.add_edge("2600 Taylorsville Blvd", "177 W Price Ave", 6.5)
        self.add_edge("2835 Main St", "177 W Price Ave", 1.5)
        self.add_edge("300 State St", "177 W Price Ave", 7.5)
        self.add_edge("3060 Lester St", "177 W Price Ave", 3.2)
        self.add_edge("3148 S 1100 W", "177 W Price Ave", 2.4)
        self.add_edge("3365 S 900 W", "177 W Price Ave", 2.7)
        self.add_edge("3575 W Valley Central Station bus Loop", "177 W Price Ave", 1.4)
        self.add_edge("3595 Main St", "177 W Price Ave", 0.5)
        self.add_edge("380 W 2880 S", "177 W Price Ave", 1.7)
        self.add_edge("410 S State St", "177 W Price Ave", 6.5)
        self.add_edge("4300 S 1300 E", "177 W Price Ave", 3.2)
        self.add_edge("4580 S 2300 E", "177 W Price Ave", 5.2)
        self.add_edge("5025 State St", "177 W Price Ave", 2.5)
        self.add_edge("5100 South 2700 West", "177 W Price Ave", 6.0)
        self.add_edge("5383 S 900 East #104", "177 W Price Ave", 4.2)
        self.add_edge("600 E 900 South", "177 W Price Ave", 5.4)
        self.add_edge("6351 South 900 East", "177 W Price Ave", 5.5)

        self.add_edge("195 W Oakland Ave", "195 W Oakland Ave", 0.0)
        self.add_edge("2010 W 500 S", "195 W Oakland Ave", 6.3)
        self.add_edge("2300 Parkway Blvd", "195 W Oakland Ave", 4.3)
        self.add_edge("233 Canyon Rd", "195 W Oakland Ave", 4.5)
        self.add_edge("2530 S 500 E", "195 W Oakland Ave", 1.5)
        self.add_edge("2600 Taylorsville Blvd", "195 W Oakland Ave", 8.7)
        self.add_edge("2835 Main St", "195 W Oakland Ave", 0.8)
        self.add_edge("300 State St", "195 W Oakland Ave", 4.5)
        self.add_edge("3060 Lester St", "195 W Oakland Ave", 3.9)
        self.add_edge("3148 S 1100 W", "195 W Oakland Ave", 3.0)
        self.add_edge("3365 S 900 W", "195 W Oakland Ave", 3.8)
        self.add_edge("3575 W Valley Central Station bus Loop", "195 W Oakland Ave", 5.7)
        self.add_edge("3595 Main St", "195 W Oakland Ave", 1.9)
        self.add_edge("380 W 2880 S", "195 W Oakland Ave", 1.1)
        self.add_edge("410 S State St", "195 W Oakland Ave", 3.5)
        self.add_edge("4300 S 1300 E", "195 W Oakland Ave", 4.9)
        self.add_edge("4580 S 2300 E", "195 W Oakland Ave", 6.9)
        self.add_edge("5025 State St", "195 W Oakland Ave", 4.2)
        self.add_edge("5100 South 2700 West", "195 W Oakland Ave", 9.0)
        self.add_edge("5383 S 900 East #104", "195 W Oakland Ave", 5.9)
        self.add_edge("600 E 900 South", "195 W Oakland Ave", 3.5)
        self.add_edge("6351 South 900 East", "195 W Oakland Ave", 7.2)

        self.add_edge("2010 W 500 S", "2010 W 500 S", 0.0)
        self.add_edge("2300 Parkway Blvd", "2010 W 500 S", 4.0)
        self.add_edge("233 Canyon Rd", "2010 W 500 S", 4.2)
        self.add_edge("2530 S 500 E", "2010 W 500 S", 8.0)
        self.add_edge("2600 Taylorsville Blvd", "2010 W 500 S", 8.6)
        self.add_edge("2835 Main St", "2010 W 500 S", 6.9)
        self.add_edge("300 State St", "2010 W 500 S", 4.2)
        self.add_edge("3060 Lester St", "2010 W 500 S", 4.2)
        self.add_edge("3148 S 1100 W", "2010 W 500 S", 8.0)
        self.add_edge("3365 S 900 W", "2010 W 500 S", 5.8)
        self.add_edge("3575 W Valley Central Station bus Loop", "2010 W 500 S", 7.2)
        self.add_edge("3595 Main St", "2010 W 500 S", 7.7)
        self.add_edge("380 W 2880 S", "2010 W 500 S", 6.6)
        self.add_edge("410 S State St", "2010 W 500 S", 3.2)
        self.add_edge("4300 S 1300 E", "2010 W 500 S", 11.2)
        self.add_edge("4580 S 2300 E", "2010 W 500 S", 12.7)
        self.add_edge("5025 State St", "2010 W 500 S", 10.0)
        self.add_edge("5100 South 2700 West", "2010 W 500 S", 8.2)
        self.add_edge("5383 S 900 East #104", "2010 W 500 S", 11.7)
        self.add_edge("600 E 900 South", "2010 W 500 S", 5.1)
        self.add_edge("6351 South 900 East", "2010 W 500 S", 14.2)

        self.add_edge("2300 Parkway Blvd", "2300 Parkway Blvd", 0.0)
        self.add_edge("233 Canyon Rd", "2300 Parkway Blvd", 7.7)
        self.add_edge("2530 S 500 E", "2300 Parkway Blvd", 9.3)
        self.add_edge("2600 Taylorsville Blvd", "2300 Parkway Blvd", 4.6)
        self.add_edge("2835 Main St", "2300 Parkway Blvd", 4.8)
        self.add_edge("300 State St", "2300 Parkway Blvd", 7.7)
        self.add_edge("3060 Lester St", "2300 Parkway Blvd", 1.6)
        self.add_edge("3148 S 1100 W", "2300 Parkway Blvd", 3.3)
        self.add_edge("3365 S 900 W", "2300 Parkway Blvd", 3.4)
        self.add_edge("3575 W Valley Central Station bus Loop", "2300 Parkway Blvd", 3.1)
        self.add_edge("3595 Main St", "2300 Parkway Blvd", 5.1)
        self.add_edge("380 W 2880 S", "2300 Parkway Blvd", 4.6)
        self.add_edge("410 S State St", "2300 Parkway Blvd", 6.7)
        self.add_edge("4300 S 1300 E", "2300 Parkway Blvd", 8.1)
        self.add_edge("4580 S 2300 E", "2300 Parkway Blvd", 10.4)
        self.add_edge("5025 State St", "2300 Parkway Blvd", 7.8)
        self.add_edge("5100 South 2700 West", "2300 Parkway Blvd", 4.2)
        self.add_edge("5383 S 900 East #104", "2300 Parkway Blvd", 9.5)
        self.add_edge("600 E 900 South", "2300 Parkway Blvd", 6.2)
        self.add_edge("6351 South 900 East", "2300 Parkway Blvd", 10.7)

        self.add_edge("233 Canyon Rd", "233 Canyon Rd", 0.0)
        self.add_edge("2530 S 500 E", "233 Canyon Rd", 4.8)
        self.add_edge("2600 Taylorsville Blvd", "233 Canyon Rd", 11.9)
        self.add_edge("2835 Main St", "233 Canyon Rd", 4.7)
        self.add_edge("300 State St", "233 Canyon Rd", 0.6)
        self.add_edge("3060 Lester St", "233 Canyon Rd", 7.6)
        self.add_edge("3148 S 1100 W", "233 Canyon Rd", 7.8)
        self.add_edge("3365 S 900 W", "233 Canyon Rd", 6.6)
        self.add_edge("3575 W Valley Central Station bus Loop", "233 Canyon Rd", 7.2)
        self.add_edge("3595 Main St", "233 Canyon Rd", 5.9)
        self.add_edge("380 W 2880 S", "233 Canyon Rd", 5.4)
        self.add_edge("410 S State St", "233 Canyon Rd", 1.0)
        self.add_edge("4300 S 1300 E", "233 Canyon Rd", 8.5)
        self.add_edge("4580 S 2300 E", "233 Canyon Rd", 10.3)
        self.add_edge("5025 State St", "233 Canyon Rd", 7.8)
        self.add_edge("5100 South 2700 West", "233 Canyon Rd", 11.5)
        self.add_edge("5383 S 900 East #104", "233 Canyon Rd", 9.5)
        self.add_edge("600 E 900 South", "233 Canyon Rd", 2.8)
        self.add_edge("6351 South 900 East", "233 Canyon Rd", 14.1)

        self.add_edge("2530 S 500 E", "2530 S 500 E", 0.0)
        self.add_edge("2600 Taylorsville Blvd", "2530 S 500 E", 9.4)
        self.add_edge("2835 Main St", "2530 S 500 E", 1.1)
        self.add_edge("300 State St", "2530 S 500 E", 5.1)
        self.add_edge("3060 Lester St", "2530 S 500 E", 4.6)
        self.add_edge("3148 S 1100 W", "2530 S 500 E", 3.7)
        self.add_edge("3365 S 900 W", "2530 S 500 E", 4.0)
        self.add_edge("3575 W Valley Central Station bus Loop", "2530 S 500 E", 6.7)
        self.add_edge("3595 Main St", "2530 S 500 E", 2.3)
        self.add_edge("380 W 2880 S", "2530 S 500 E", 1.8)
        self.add_edge("410 S State St", "2530 S 500 E", 4.1)
        self.add_edge("4300 S 1300 E", "2530 S 500 E", 3.8)
        self.add_edge("4580 S 2300 E", "2530 S 500 E", 5.8)
        self.add_edge("5025 State St", "2530 S 500 E", 4.3)
        self.add_edge("5100 South 2700 West", "2530 S 500 E", 7.8)
        self.add_edge("5383 S 900 East #104", "2530 S 500 E", 4.8)
        self.add_edge("600 E 900 South", "2530 S 500 E", 3.2)
        self.add_edge("6351 South 900 East", "2530 S 500 E", 6.0)

        self.add_edge("2600 Taylorsville Blvd", "2600 Taylorsville Blvd", 0.0)
        self.add_edge("2835 Main St", "2600 Taylorsville Blvd", 7.3)
        self.add_edge("300 State St", "2600 Taylorsville Blvd", 12.0)
        self.add_edge("3060 Lester St", "2600 Taylorsville Blvd", 4.9)
        self.add_edge("3148 S 1100 W", "2600 Taylorsville Blvd", 5.2)
        self.add_edge("3365 S 900 W", "2600 Taylorsville Blvd", 5.4)
        self.add_edge("3575 W Valley Central Station bus Loop", "2600 Taylorsville Blvd", 8.1)
        self.add_edge("3595 Main St", "2600 Taylorsville Blvd", 6.2)
        self.add_edge("380 W 2880 S", "2600 Taylorsville Blvd", 6.9)
        self.add_edge("410 S State St", "2600 Taylorsville Blvd", 11.5)
        self.add_edge("4300 S 1300 E", "2600 Taylorsville Blvd", 6.9)
        self.add_edge("4580 S 2300 E", "2600 Taylorsville Blvd", 8.3)
        self.add_edge("5025 State St", "2600 Taylorsville Blvd", 4.1)
        self.add_edge("5100 South 2700 West", "2600 Taylorsville Blvd", 0.4)
        self.add_edge("5383 S 900 East #104", "2600 Taylorsville Blvd", 4.9)
        self.add_edge("600 E 900 South", "2600 Taylorsville Blvd", 11.0)
        self.add_edge("6351 South 900 East", "2600 Taylorsville Blvd", 6.8)

        self.add_edge("2835 Main St", "2835 Main St", 0.0)
        self.add_edge("300 State St", "2835 Main St", 4.7)
        self.add_edge("3060 Lester St", "2835 Main St", 3.5)
        self.add_edge("3148 S 1100 W", "2835 Main St", 2.6)
        self.add_edge("3365 S 900 W", "2835 Main St", 2.9)
        self.add_edge("3575 W Valley Central Station bus Loop", "2835 Main St", 6.3)
        self.add_edge("3595 Main St", "2835 Main St", 1.2)
        self.add_edge("380 W 2880 S", "2835 Main St", 1.0)
        self.add_edge("410 S State St", "2835 Main St", 3.7)
        self.add_edge("4300 S 1300 E", "2835 Main St", 4.1)
        self.add_edge("4580 S 2300 E", "2835 Main St", 6.2)
        self.add_edge("5025 State St", "2835 Main St", 3.4)
        self.add_edge("5100 South 2700 West", "2835 Main St", 6.9)
        self.add_edge("5383 S 900 East #104", "2835 Main St", 5.2)
        self.add_edge("600 E 900 South", "2835 Main St", 3.7)
        self.add_edge("6351 South 900 East", "2835 Main St", 6.4)

        self.add_edge("300 State St", "300 State St", 0.0)
        self.add_edge("3060 Lester St", "300 State St", 7.3)
        self.add_edge("3148 S 1100 W", "300 State St", 7.8)
        self.add_edge("3365 S 900 W", "300 State St", 6.6)
        self.add_edge("3575 W Valley Central Station bus Loop", "300 State St", 7.2)
        self.add_edge("3595 Main St", "300 State St", 5.9)
        self.add_edge("380 W 2880 S", "300 State St", 5.4)
        self.add_edge("410 S State St", "300 State St", 1.0)
        self.add_edge("4300 S 1300 E", "300 State St", 8.5)
        self.add_edge("4580 S 2300 E", "300 State St", 10.3)
        self.add_edge("5025 State St", "300 State St", 7.8)
        self.add_edge("5100 South 2700 West", "300 State St", 11.5)
        self.add_edge("5383 S 900 East #104", "300 State St", 9.5)
        self.add_edge("600 E 900 South", "300 State St", 2.8)
        self.add_edge("6351 South 900 East", "300 State St", 14.1)

        self.add_edge("3060 Lester St", "3060 Lester St", 0.0)
        self.add_edge("3148 S 1100 W", "3060 Lester St", 1.3)
        self.add_edge("3365 S 900 W", "3060 Lester St", 1.5)
        self.add_edge("3575 W Valley Central Station bus Loop", "3060 Lester St", 4.0)
        self.add_edge("3595 Main St", "3060 Lester St", 3.2)
        self.add_edge("380 W 2880 S", "3060 Lester St", 3.0)
        self.add_edge("410 S State St", "3060 Lester St", 6.9)
        self.add_edge("4300 S 1300 E", "3060 Lester St", 6.2)
        self.add_edge("4580 S 2300 E", "3060 Lester St", 8.2)
        self.add_edge("5025 State St", "3060 Lester St", 5.5)
        self.add_edge("5100 South 2700 West", "3060 Lester St", 4.4)
        self.add_edge("5383 S 900 East #104", "3060 Lester St", 7.2)
        self.add_edge("600 E 900 South", "3060 Lester St", 6.4)
        self.add_edge("6351 South 900 East", "3060 Lester St", 10.5)

        self.add_edge("3148 S 1100 W", "3148 S 1100 W", 0.0)
        self.add_edge("3365 S 900 W", "3148 S 1100 W", 0.6)
        self.add_edge("3575 W Valley Central Station bus Loop", "3148 S 1100 W", 6.4)
        self.add_edge("3595 Main St", "3148 S 1100 W", 2.4)
        self.add_edge("380 W 2880 S", "3148 S 1100 W", 2.2)
        self.add_edge("410 S State St", "3148 S 1100 W", 6.8)
        self.add_edge("4300 S 1300 E", "3148 S 1100 W", 5.3)
        self.add_edge("4580 S 2300 E", "3148 S 1100 W", 7.4)
        self.add_edge("5025 State St", "3148 S 1100 W", 4.6)
        self.add_edge("5100 South 2700 West", "3148 S 1100 W", 4.8)
        self.add_edge("5383 S 900 East #104", "3148 S 1100 W", 6.3)
        self.add_edge("600 E 900 South", "3148 S 1100 W", 6.5)
        self.add_edge("6351 South 900 East", "3148 S 1100 W", 8.8)

        self.add_edge("3365 S 900 W", "3365 S 900 W", 0.0)
        self.add_edge("3575 W Valley Central Station bus Loop", "3365 S 900 W", 5.6)
        self.add_edge("3595 Main St", "3365 S 900 W", 1.6)
        self.add_edge("380 W 2880 S", "3365 S 900 W", 1.7)
        self.add_edge("410 S State St", "3365 S 900 W", 6.4)
        self.add_edge("4300 S 1300 E", "3365 S 900 W", 4.9)
        self.add_edge("4580 S 2300 E", "3365 S 900 W", 6.9)
        self.add_edge("5025 State St", "3365 S 900 W", 4.2)
        self.add_edge("5100 South 2700 West", "3365 S 900 W", 5.6)
        self.add_edge("5383 S 900 East #104", "3365 S 900 W", 5.9)
        self.add_edge("600 E 900 South", "3365 S 900 W", 5.7)
        self.add_edge("6351 South 900 East", "3365 S 900 W", 8.4)

        self.add_edge("3575 W Valley Central Station bus Loop", "3575 W Valley Central Station bus Loop", 0.0)
        self.add_edge("3595 Main St", "3575 W Valley Central Station bus Loop", 7.1)
        self.add_edge("380 W 2880 S", "3575 W Valley Central Station bus Loop", 6.1)
        self.add_edge("410 S State St", "3575 W Valley Central Station bus Loop", 7.2)
        self.add_edge("4300 S 1300 E", "3575 W Valley Central Station bus Loop", 10.6)
        self.add_edge("4580 S 2300 E", "3575 W Valley Central Station bus Loop", 12.0)
        self.add_edge("5025 State St", "3575 W Valley Central Station bus Loop", 9.4)
        self.add_edge("5100 South 2700 West", "3575 W Valley Central Station bus Loop", 7.5)
        self.add_edge("5383 S 900 East #104", "3575 W Valley Central Station bus Loop", 11.1)
        self.add_edge("600 E 900 South", "3575 W Valley Central Station bus Loop", 6.2)
        self.add_edge("6351 South 900 East", "3575 W Valley Central Station bus Loop", 13.6)

        self.add_edge("3595 Main St", "3595 Main St", 0.0)
        self.add_edge("380 W 2880 S", "3595 Main St", 1.6)
        self.add_edge("410 S State St", "3595 Main St", 4.9)
        self.add_edge("4300 S 1300 E", "3595 Main St", 3.0)
        self.add_edge("4580 S 2300 E", "3595 Main St", 5.0)
        self.add_edge("5025 State St", "3595 Main St", 2.3)
        self.add_edge("5100 South 2700 West", "3595 Main St", 5.5)
        self.add_edge("5383 S 900 East #104", "3595 Main St", 4.0)
        self.add_edge("600 E 900 South", "3595 Main St", 5.1)
        self.add_edge("6351 South 900 East", "3595 Main St", 5.2)

        self.add_edge("380 W 2880 S", "380 W 2880 S", 0.0)
        self.add_edge("410 S State St", "380 W 2880 S", 4.4)
        self.add_edge("4300 S 1300 E", "380 W 2880 S", 4.6)
        self.add_edge("4580 S 2300 E", "380 W 2880 S", 6.6)
        self.add_edge("5025 State St", "380 W 2880 S", 3.9)
        self.add_edge("5100 South 2700 West", "380 W 2880 S", 6.5)
        self.add_edge("5383 S 900 East #104", "380 W 2880 S", 5.6)
        self.add_edge("600 E 900 South", "380 W 2880 S", 4.3)
        self.add_edge("6351 South 900 East", "380 W 2880 S", 6.9)

        self.add_edge("410 S State St", "410 S State St", 0.0)
        self.add_edge("4300 S 1300 E", "410 S State St", 7.5)
        self.add_edge("4580 S 2300 E", "410 S State St", 9.3)
        self.add_edge("5025 State St", "410 S State St", 6.8)
        self.add_edge("5100 South 2700 West", "410 S State St", 11.4)
        self.add_edge("5383 S 900 East #104", "410 S State St", 8.5)
        self.add_edge("600 E 900 South", "410 S State St", 1.8)
        self.add_edge("6351 South 900 East", "410 S State St", 13.1)

        self.add_edge("4300 S 1300 E", "4300 S 1300 E", 0.0)
        self.add_edge("4580 S 2300 E", "4300 S 1300 E", 2.0)
        self.add_edge("5025 State St", "4300 S 1300 E", 2.9)
        self.add_edge("5100 South 2700 West", "4300 S 1300 E", 6.4)
        self.add_edge("5383 S 900 East #104", "4300 S 1300 E", 2.8)
        self.add_edge("600 E 900 South", "4300 S 1300 E", 6.0)
        self.add_edge("6351 South 900 East", "4300 S 1300 E", 4.1)

        self.add_edge("4580 S 2300 E", "4580 S 2300 E", 0.0)
        self.add_edge("5025 State St", "4580 S 2300 E", 4.4)
        self.add_edge("5100 South 2700 West", "4580 S 2300 E", 7.9)
        self.add_edge("5383 S 900 East #104", "4580 S 2300 E", 3.4)
        self.add_edge("600 E 900 South", "4580 S 2300 E", 7.9)
        self.add_edge("6351 South 900 East", "4580 S 2300 E", 4.7)

        self.add_edge("5025 State St", "5025 State St", 0.0)
        self.add_edge("5100 South 2700 West", "5025 State St", 4.5)
        self.add_edge("5383 S 900 East #104", "5025 State St", 1.7)
        self.add_edge("600 E 900 South", "5025 State St", 6.8)
        self.add_edge("6351 South 900 East", "5025 State St", 3.1)

        self.add_edge("5100 South 2700 West", "5100 South 2700 West", 0.0)
        self.add_edge("5383 S 900 East #104", "5100 South 2700 West", 5.4)
        self.add_edge("600 E 900 South", "5100 South 2700 West", 10.6)
        self.add_edge("6351 South 900 East", "5100 South 2700 West", 7.8)

        self.add_edge("5383 S 900 East #104", "5383 S 900 East #104", 0.0)
        self.add_edge("600 E 900 South", "5383 S 900 East #104", 7.0)
        self.add_edge("6351 South 900 East", "5383 S 900 East #104", 1.3)

        self.add_edge("600 E 900 South", "600 E 900 South", 0.0)
        self.add_edge("6351 South 900 East", "600 E 900 South", 8.3)

        self.add_edge("6351 South 900 East", "6351 South 900 East", 0.0)







