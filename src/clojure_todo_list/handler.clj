(ns clojure-todo-list.handler
  (:require [clojure-todo-list.views :as views]
            [compojure.core :as cc]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(cc/defroutes app-routes
 (cc/GET "/"
    []
    (views/all-lists-page))
  (cc/GET "/list/:id"
    [id]
    (views/get-one-list-page id))
  (cc/GET "/about"
    []
    (views/about-page))
  (cc/POST "/list"
    {params :params}
    (views/add-list-page params))
  (cc/POST "/list/:id"
    {params :params}
    (views/add-item-to-list-page params))
  (cc/POST "/done"
    {params :params}
    (views/set-item-done-page params))
  (cc/POST "/delete"
    {params :params}
    (views/delete-item-done-page params))
  (cc/POST "/list/:id/delete"
    [id]
    (views/delete-list-page id))
  (route/resources "/")
  (route/not-found "Not found"))

(def app
  (handler/site app-routes))
