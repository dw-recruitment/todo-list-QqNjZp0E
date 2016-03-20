(ns clojure-todo-list.views
  (:require [clojure-todo-list.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn home-page
  []
  (let [all-todos (db/get-all-todos)]
            (hic-p/html5
              [:h1 "Super cool awesome headline"]
              [:table
                [:tr [:th "item"] [:th "done"]]
                (for [todo all-todos]
                  [:tr [:td (:item todo)[:td (:done todo)]]])]
              [:h2 "Add Another TODO"]
              [:form {:action "/" :method "POST"}
                [:p "new item" [:input {:type "text" :name "item"}]]
                [:p [:input {:type "submit" :value "add new item"}]]])))

(defn about-page
  []
  (hic-p/html5
    [:h1 "About this project"]
    [:p "This is a basic webapp to:"]
    [:ul [:li "help me learn ~*clojure*~"]
         [:li "keep track of #TODO items"]
         [:li "reinforce that learning new languages is fun"]]))
