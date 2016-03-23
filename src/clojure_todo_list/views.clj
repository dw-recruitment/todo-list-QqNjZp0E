(ns clojure-todo-list.views
  (:require [clojure-todo-list.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn get-one-list-page
  [id]
  (let [list (db/get-one-list [id])]
    (hic-p/html5
      [:h1 (:name (first list))]
      [:table
        [:tr [:th "item"] [:th "done"][:th "delete"]]
        (for [todo list]
          [:tr
            [:td
              (case (:done todo)
                1 [:span {:style "text-decoration:line-through;"}(:item todo)]
                0 (:item todo))]
            [:td
              [:form {:action "/done" :method "POST"}
                [:input {:type "hidden" :name "id" :value (:id todo)}]
                [:input {:type "hidden" :name "list" :value id}]
                [:input {:type "checkbox" :onclick "this.form.submit()" :name "done" :checked (case (:done todo) 1 true 0 false)}]
                ]]
            [:td
                [:form {:action "/delete" :method "POST"}
                  [:input {:type "hidden" :name "id" :value (:id todo)}]
                  [:input {:type "hidden" :name "list" :value id}]
                  [:input {:type "submit" :value "delete"}]]]])]
            [:h2 "Add Another TODO"]
            [:form {:action (format "/list/%s" id) :method "POST"}
              [:p "new item:" [:input {:type "text" :name "item"}]
                              [:input {:type "hidden" :name "id" :value id}]
                              [:input {:type "submit" :value "add new item"}]]])))

(defn all-lists-page
  []
  (let [all-lists (db/get-all-lists)]
    (hic-p/html5
      [:h1 "Here's your list of lists!"]
      (for [list all-lists]
        [:a {:href (format "/list/%s" (str (:id list)))}[:p (:name list)]])
      )))

(defn all-todos-page
  []
  (let [all-todos (db/get-all-todos)]
    (hic-p/html5
      [:h1 "Super cool awesome headline"]
      [:table
        [:tr [:th "item"] [:th "done"][:th "delete"]]
        (for [todo all-todos]
          [:tr
            [:td
              (case (:done todo)
                1 [:span {:style "text-decoration:line-through;"}(:item todo)]
                0 (:item todo))]
            [:td
              [:form {:action "/done" :method "POST"}
                [:input {:type "hidden" :name "id" :value (:id todo)}]
                [:input {:type "checkbox" :onclick "this.form.submit()" :name "done" :checked (case (:done todo) 1 true 0 false)}]
                ]]
            [:td
                [:form {:action "/delete" :method "POST"}
                  [:input {:type "hidden" :name "id" :value (:id todo)}]
                  [:input {:type "submit" :value "delete"}]]]])]
      [:h2 "Add Another TODO"]
      [:form {:action "/" :method "POST"}
        [:p "new item:" [:input {:type "text" :name "item"}]
                        [:input {:type "submit" :value "add new item"}]]])))

(defn about-page
  []
  (hic-p/html5
    [:h1 "About this project"]
    [:p "This is a basic webapp to:"]
    [:ul [:li "help me learn ~*clojure*~"]
         [:li "keep track of #TODO items"]
         [:li "reinforce that learning new languages is fun"]]))

(defn add-todo-item-page
  [{:keys [item]}]
  (db/add-todo-item-to-db item)
  {:status 302
   :headers {"Location" "/"}
   :body ""})

(defn add-item-to-list-page
 [params]
 (db/add-todo-item-to-list params)
 {:status 302
  :headers {"Location" (format "/list/%s" (:id params)) }
  :body ""})

(defn set-item-done-page
  [{:keys [id done list]}]
  (db/set-item-as-done [id done])
  {:status 302
   :headers {"Location" (format "/list/%s" list)}
   :body ":"})

(defn delete-item-done-page
 [{:keys [id list]}]
 (db/delete-item [id])
 {:status 302
  :headers {"Location" (format "/list/%s" list)}
  :body ":"})
