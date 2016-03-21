(ns clojure-todo-list.handler
  (:require [clojure-todo-list.views :as views]
            [compojure.core :as cc]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(cc/defroutes app-routes
  (cc/GET "/"
       []
       (views/home-page))
  (cc/GET "/about"
       []
       (views/about-page))
  (cc/POST "/"
      {params :params}
      (views/add-todo-item-page params))
  (cc/POST "/done"
      {params :params}
      (views/set-item-done-page params))
  (cc/POST "/delete"
      {params :params}
      (views/delete-item-done-page params))
  (route/resources "/")
  (route/not-found "Not found"))

(def app
  (handler/site app-routes))
