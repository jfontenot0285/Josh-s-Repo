# truck class creates the trucks used to deliver the packages and load functions for loading packages
class Truck:
    # initialize truck object with speed, capacity, and payload properties
    # assignments are all O(1), O(1) total worst case runtime complexity
    def __init__(self, current_address, miles_driven):
        self.speed = 18
        self.capacity = 16
        self.current_packages = []
        self.target_payload_id = 0
        self.total_miles_driven = miles_driven
        self.miles_to_target_address = 0.0
        self.target_address = []
        self.miles_from_last_address = 0.0
        self.extra_miles = 0.0
        self.current_address = current_address

    # used like a setter function for payload, populates payload list with package IDs
    # for loop iterates package_list number of times with 1 assignment for each iteration - O(N)*O(1) = O(N) worst case
    def load_packages(self, package_list):
        for i in package_list:
            self.current_packages.append(i)

    # once a package is delivered, remove from payload list
    # for loop iterates package_list number of times with 1 assignment for each iteration - O(N)*O(1) = O(N) worst case
    def deliver_packages(self, package_list):
        for i in package_list:
            self.current_packages.remove(i)
