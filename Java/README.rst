#####################################################
./Java contains java projects separated by subfolders
#####################################################

****
JDBC
****

- JDBC is a front end gui-driven application that allows for a user to log in locally and interact with a band-end sql database.
- You can get this to work with your own database by modifying the appropriate data members located in src/Model/DBConnection.java.
- The application focuses heavily on querying the database, then populating observable lists to allow for local presentation and manipulation of that data.
- The user can create, modify, and delete customer appointments
- A filter exists to view appointments by month and week
- A user can view the number of types of appointments by month
- Time zones are automatically changed from UTC at the back end to the user's local time at the front end.
- The login form is capable of displaying messages and dialog in both English and German, and will do so based on the system locale.
- A log file captures attempted logins and local timestamps.
- **To login, simply use test for the username and password.**


**************
LocalInventory
**************

- LocalInventory is a gui-driven application which allows a user to manipulate an inventory database. 
- Parts may be added, modified, removed, and associated with products.
