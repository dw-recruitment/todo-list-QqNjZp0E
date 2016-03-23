# clojure-todo-list

A basic todo list, written in Clojure using Leiningin, Ring, Compojure, Hiccup, Sqlite3, and Bootstrap.

## Technologies implemented

**Leiningin** was used the build the project; the **lein-ring** plugin allowed me to see code updates with a page refresh.

**Ring** is the HTTP library.

**Compojure** was added to make routing easier.  Before adding compojure I was using a case statement to route; Compojure allowed me to route GET and POST commands on the same url to different views.

**Hiccup** outputs the HTML based on Clojure structures provided in the views.

**Sqlite3** powers the database where items and lists are stored.

**Bootstrap** makes it look pretty without too much front-end effort.



## Functionality

To run: `lein ring server` inside the project.

You can:
* add and delete todo lists
* add items to and delete todo items from a list
* read a delightful about page
