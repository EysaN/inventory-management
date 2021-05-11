# Inventory management system

### A project for the course of Database System / GEIK / ME 2021

#### Supervisor: Boucetta Sara Imene
#### Author: Eysa Nemeh


---

## The use case

---

In the world of online inventory managements systems, costumers of a system may browser of multiple items over the course of their sessions, and add them to a cart. Overtime, they will either checkout, and cart content is emptied, or the cart will expire.
This interferes with the management of any inventory and make is hard to maintain a correct counts of available items.

The solution provided here offers a modern approach to build an inventory management system with helpful operations on items and carts.

---

## Some prerequisites and assumptions:

--- 

 - IntelliJ IDEA should be installed and used to open the project (preferably)
 - A mongo server should be running at `localhost:27017`
 - A mongodb database should exist under the name `inventory`
 - No standalone LDAP server required
 - Port `8399` should be available to run the embedded LDAP server
 - Port `8080` should be available to start the spring boot tomcat
 - The application's configuration (ports, hosts, ...) can also be modified in the `application.properties` file.


**_In case of any problems, please refer to the author_**

**_This project is only intended for educational purposes, and not to be used commercially or production-ready_**

---

## Get started

---

To start the program either clone the repository somewhere into your local file system

```bash
git clone https://github.com/EysaN/inventory-management.git
```

Or download the zip and extract it somewhere on local file system

Open the project from IntelliJ

Then create new connection configuration and click run or open the class `InvmngApplication` and hit run

---

## Operations

---

To authenticate use according to API authentication:

Admin (level1): this username `dbu` and password `databases`

Sub admin (level2): this username `subuser` and password `subuser123`

Normal customer (level3): this username `ben` and password `normal123`

Note: use the following API before requesting an API for a higher level of authentication:
```http request
http://localhost:8080/logout
```

Test your level of authentication:
```
http://localhost:8080/level1
http://localhost:8080/level2
http://localhost:8080/level3
```

+ find all items (no authentication)
```http request
http://localhost:8080/items
```
+ find an item by id (no authentication)
```http request
http://localhost:8080/item/{id}
```

+ find all items by name (no authentication)
```http request
http://localhost:8080/itemsByName/{name}
```

+ find all items by cart id (no authentication)
```http request
http://localhost:8080/itemsByCart/{cartId}
```

+ Add new item (sub-admins and admins)
```http request
http://localhost:8080/item/add/{query_parameters}
```

+ delete an item by id (admins only)
```http request
http://localhost:8080/item/delete/{id}
```

+ delete all items by name (admins only)
```http request
http://localhost:8080/itemsDeleteByName/{name}
```

+ delete all items by cart id (admins only)
```http request
http://localhost:8080/itemsDeleteByCart/{cartId}
```

Start adding data into the mongodb database using this emaple API:
```
http://localhost:8080/item/add?name=item0&cartId=1&price=1000&qty=3
http://localhost:8080/cart/add?amount=10000&description=this is a test cart
```

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



