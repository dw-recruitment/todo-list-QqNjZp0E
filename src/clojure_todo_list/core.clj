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

  (defn error [request]
  {:status 404
   :headers {"Content-Type" "text/html"}
   :body "<h1>404 Not Found</h1"})

  (defn handler [request]
  (case (request :uri)
    "/" (home request)
    (error request)))
