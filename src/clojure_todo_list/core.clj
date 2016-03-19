(ns clojure-todo-list.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn home [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>Super cool awesome headline</h1>
                     <img src='http://www.cs.utah.edu/~gk/atwork/img/hevequip2.gif'
                     alt='who doesn't love a good construction gif'>"})

(defn about [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<h1>About this project</h1>
          <p>This is a basic webapp to:</p>
          <ul>
            <li>help me learn ~*clojure*~</li>
            <li>keep track of #TODO items</li>
            <li>reinforce that learning new languages is fun!</li>
          </ul>"})

  (defn error [request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "<h1>404 Not Found</h1"})

  (defn handler [request]
  (case (request :uri)
    "/" (home request)
    "/about" (about request)
    (error request)))
