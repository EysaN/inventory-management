# Inventory management system

### A project for the course of Database System / GEIK / ME 2021

#### Supervisor: Boucetta Sara Imene
#### Author: Eysa Nemeh

## Some prerequisites and assumptions:

--- 

 - IntelliJ IDEA should be installed and used to open the project (preferably)
 - A mongo server should be running at `localhost:27017`
 - A mongodb database should exist under the name `inventory`
 - No standalone LDAP server required
 - Port `8399` should be available to run the embedded LDAP server
 - The application's configuration (ports, hosts, ...) can also be modified in the `application.properties` file.


**_In case of any problems, please refer to the author_**

**_This project is only intended for educational purposes, and not to be used commercially or production-ready_**

---

## The use case

---

In the world of online inventory managements systems, costumers of a system may browser of multiple items over the course of their sessions, and add them to a cart. Overtime, they will either checkout, and cart content is emptied, or the cart will expire.
This interferes with the management of any inventory and make is hard to maintain a correct counts of available items.

The solution provided here offers a modern approach to build an inventory management system with helpful operations on items and carts.

---

## Technologies used

---

* Mongodb Server: community edition
* Embedded LDAP server
* Spring Boot framework
* Spring Data for ldap and mongo drivers
* Spring Web for http requests
* Spring Security for authentication and authorization



This project operates on the [Project Lomnok](https://projectlombok.org/) for clean code.

Using the `@Data` annotation, we don't need to create any constructors, setters or getters, hash and equals, or toString functions unless customization is required  



