# Sample Output – JDBC Student Management

**Prerequisites:** Create the database and table using `schema.sql`, configure the MySQL JDBC driver, and set `UPS_DB_URL`, `UPS_DB_USER`, and `UPS_DB_PASSWORD`.

Example environment configuration (use your own local credentials):

```text
UPS_DB_URL=jdbc:mysql://localhost:3306/ups_training
UPS_DB_USER=your_mysql_username
UPS_DB_PASSWORD=your_mysql_password
```

Illustrative session assuming the database is empty and the insert succeeds:

```text
1. Add student  2. List students  3. Exit
Choose: 1
Name: Arun Kumar
Email: arun@example.com
Department: CSE
Student added.

1. Add student  2. List students  3. Exit
Choose: 2
1 | Arun Kumar | arun@example.com | CSE

1. Add student  2. List students  3. Exit
Choose: 3
```

If the database settings are missing, the current program prints:

```text
Set UPS_DB_URL, UPS_DB_USER and UPS_DB_PASSWORD first.
```

*Note: The successful database session is illustrative; actual results depend on your local MySQL setup and database contents.*
