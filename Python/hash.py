# hash class creates the hash table for package id and associated data
class Hash:
    # total worst case runtime complexity for __init__ is O(1)+O(N)+O(1) = O(N)
    def __init__(self):
        # create empty list of lists for hash table of length 40
        # loop iterates constant number of times, O(1) worst case
        self.table = [[] for i in range(41)]
        # populate the hash table with values, populate_table is O(N) worst case
        self.populate_table()
        # 1 assignment is O(1) worst case
        self.hub_package_list = []

    # total worst case time complexity is O(1)
    def hashing_function(self, key):
        # use modulus to hash the key into the table
        # modulus worst case is O(1) and len worst case is O(1), O(1)+O(1) = O(1) worst case
        return key % len(self.table)

    # insert new key/value pair into hash table
    # total worst case time complexity is O(1)+O(N)+O(1)+O(1) = O(N)
    def insert(self, key, value):
        # 2 assignments and 1 dictionary access is O(1)*O(1)*O(1) = O(1) worst case runtime complexity
        hash_key = self.hashing_function(key)
        key_exists = False
        bucket = self.table[hash_key]
        # enumerate each bucket and check if key is already in table
        # for loop iterates N number of times with 2 nested assignments, O(N)*O(1)*O(1) = O(N) worst case complexity
        for i, kv in enumerate(bucket):
            k = kv
            if key == k:
                key_exists = True
                break
        # 1 assignment is O(1) worst case runtime complexity
        if key_exists:
            # update the key with new value
            bucket[i] = (key, value)
        else:
            # if collision occurs, append the new item to a nested list in the same bucket
            # 1 assignment is O(1) worst case runtime complexity
            bucket.append((key, value))

    # set_status has one assignment, O(1) worst case
    def set_status(self, key, status):
        self.table[key][0][1][5] = status

    # input a key to look up its corresponding value
    # hash function is O(1), table access is O(1), for loop iterates N times with one assignment O(1)
    # total worst time complexity is O(1)+O(1)*O(N) = O(N)
    def get_value(self, key):
        # the input key will need to be hashed to link it to the appropriate bucket
        hash_key = self.hashing_function(key)
        bucket = self.table[hash_key]
        for i, kv in enumerate(bucket):
            k, v = kv
            if key == k:
                return v

    # get all keys for specified address. some addresses are the value of multiple keys
    # key_list assignment is O(1), for loop iterates N times with 1 if comparison and assignment = O(N)*O(1)*O(1) = O(N)
    # total worst case runtime complexity is O(1)+O(N) = O(N)
    def get_keys(self, address):
        key_list = []
        for i in range(1, len(self.get_all_packages()) + 1):
            if address == self.get_value(i)[0]:
                key_list.append(i)
        return key_list

    # show all packages
    # 1 assignment O(1) for package_list, constant for loop iterations O(1)
    # 1 append per iteration with get value is O(1)*O(1)*(1) = O(1)
    # total worst case runtime complexity is O(1)
    def get_all_packages(self):
        package_list = []
        for i in range(1, 41):
            package_list.append([i, self.get_value(i)])
        return package_list

    # 1 dictionary lookup is O(1) with total worst case runtime complexity of O(1)
    def get_status(self, key):
        return self.table[key][0][1][5]

    # hard code the provided data into the hash table
    # insert function is O(N), assignments are O(N)*constant number of assignments = O(N) worst case
    def populate_table(self):
        self.insert(1, ["195 W Oakland Ave", "Salt Lake City", "UT", "84115", "21 kilos", "AT WGUPS HUB"])
        self.insert(2, ["2530 S 500 E", "Salt Lake City", "UT", "84106", "44 kilos", "AT WGUPS HUB"])
        self.insert(3, ["233 Canyon Rd", "Salt Lake City", "UT", "84103", "2 kilos", "AT WGUPS HUB"])
        self.insert(4, ["380 W 2880 S", "Salt Lake City", "UT", "84115", "4 kilos", "AT WGUPS HUB"])
        self.insert(5, ["410 S State St", "Salt Lake City", "UT", "84111", "5 kilos", "AT WGUPS HUB"])
        self.insert(6, ["3060 Lester St", "West Valley City", "UT", "84119", "88 kilos", "AT WGUPS HUB"])
        self.insert(7, ["1330 2100 S", "Salt Lake City", "UT", "84106", "8 kilos", "AT WGUPS HUB"])
        self.insert(8, ["300 State St", "Salt Lake City", "UT", '84103', "9 kilos", "AT WGUPS HUB"])
        self.insert(9, ["410 S State St", "Salt Lake City", "UT", "84111", "2 kilos", "AT WGUPS HUB"])
        self.insert(10, ["600 E 900 South", "Salt Lake City", "UT", "84105", "1 kilo", "AT WGUPS HUB"])
        self.insert(11, ["2600 Taylorsville Blvd", "Salt Lake City", "UT", "84118", "1 kilo", "AT WGUPS HUB"])
        self.insert(12, ["3575 W Valley Central Station bus Loop", "West Valley City", "UT", "84119", "1 kilo", "AT WGUPS HUB"])
        self.insert(13, ["2010 W 500 S", "Salt Lake City", "UT", "84119", "2 kilos", "AT WGUPS HUB"])
        self.insert(14, ["4300 S 1300 E", "Millcreek", "UT", "84117", "88 kilos", "AT WGUPS HUB"])
        self.insert(15, ["4580 S 2300 E", "Holladay", "UT", "84117", "4 kilos", "AT WGUPS HUB"])
        self.insert(16, ["4580 S 2300 E", "Holladay", "UT", "84117", "88 kilos", "AT WGUPS HUB"])
        self.insert(17, ["3148 S 1100 W", "Salt Lake City", "UT", "84119", "2 kilos", "AT WGUPS HUB"])
        self.insert(18, ["1488 4800 S", "Salt Lake City", "UT", "84123", "6 kilos", "AT WGUPS HUB"])
        self.insert(19, ["177 W Price Ave", "Salt Lake City", "UT", "84115", "37 kilos", "AT WGUPS HUB"])
        self.insert(20, ["3595 Main St", "Salt Lake City", "UT", "84115", "37 kilos", "AT WGUPS HUB"])
        self.insert(21, ["3595 Main St", "Salt Lake City", "UT", "84115", "3 kilos", "AT WGUPS HUB"])
        self.insert(22, ["6351 South 900 East", "Murray", "UT", "84121", "2 kilos", "AT WGUPS HUB"])
        self.insert(23, ["5100 South 2700 West", "Salt Lake City", "UT", "84118", "5 kilos", "AT WGUPS HUB"])
        self.insert(24, ["5025 State St", "Murray", "UT", "84107", "7 kilos", "AT WGUPS HUB"])
        self.insert(25, ["5383 S 900 East #104", "Salt Lake City", "UT", "84117", "7 kilos", "AT WGUPS HUB"])
        self.insert(26, ["5383 S 900 East #104", "Salt Lake City", "UT", "84117", "25 kilos", "AT WGUPS HUB"])
        self.insert(27, ["1060 Dalton Ave S", "Salt Lake City", "UT", "84104", "25 kilos", "AT WGUPS HUB"])
        self.insert(28, ["2835 Main St", "Salt Lake City", "UT", "84115", "27 kilos", "AT WGUPS HUB"])
        self.insert(29, ["1330 2100 S", "Salt Lake City", "UT", "84106", "2 kilos", "AT WGUPS HUB"])
        self.insert(30, ["300 State St", "Salt Lake City", "UT", "84103", "1 kilo", "AT WGUPS HUB"])
        self.insert(31, ["3365 S 900 W", "Salt Lake City", "UT", "84119", "1 kilo", "AT WGUPS HUB"])
        self.insert(32, ["3365 S 900 W", "Salt Lake City", "UT", "84119", "1 kilo", "AT WGUPS HUB"])
        self.insert(33, ["2530 S 500 E", "Salt Lake City", "UT", "84106", "1 kilo", "AT WGUPS HUB"])
        self.insert(34, ["4580 S 2300 E", "Holladay", "UT", "84117", "2 kilos", "AT WGUPS HUB"])
        self.insert(35, ["1060 Dalton Ave S", "Salt Lake City", "UT", "84104", "88 kilos", "AT WGUPS HUB"])
        self.insert(36, ["2300 Parkway Blvd", "West Valley City", "UT", "84119", "88 kilos", "AT WGUPS HUB"])
        self.insert(37, ["410 S State St", "Salt Lake City", "UT", "84111", "2 kilos", "AT WGUPS HUB"])
        self.insert(38, ["410 S State St", "Salt Lake City", "UT", "84111", "9 kilos", "AT WGUPS HUB"])
        self.insert(39, ["2010 W 500 S", "Salt Lake City", "UT", "84104", "9 kilos", "AT WGUPS HUB"])
        self.insert(40, ["380 W 2880 S", "Salt Lake City", "UT", "84115", "45 kilos", "AT WGUPS HUB"])


