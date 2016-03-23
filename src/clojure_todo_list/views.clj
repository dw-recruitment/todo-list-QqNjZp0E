(ns clojure-todo-list.views
  (:require [clojure-todo-list.db :as db]
            [clojure.string :as str]
            [hiccup.page :as hic-p]))

(defn gen-page-head
  [title]
  [:head
    [:title title]
    (hic-p/include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css")
    (hic-p/include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js")])

(defn headers
  []
  (hic-p/html5
    [:nav {:class "navbar navbar-inverse navbar-fixed-top"}
      [:div {:class "container"}
        [:div {:class "navbar-header"}
        [:button {:type "button" :class "navbar-toggle collapsed" :data-toggle "collapse" :data-target "#navbar" :aria-expanded "false" :aria-controls "navbar"}
          [:span {:class "sr-only"} "Toggle navigation"]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}]]
        [:div {:class "navbar-brand"} "Todo list"]]
        [:div {:id "navbar" :class "collapse navbar-collapse"}
          [:ul {:class "nav navbar-nav"}
            [:li {:class "active"} [:a {:href "/"}"Home"]]
            [:li [:a {:href "/about"}"About"]]]]]]))

(defn get-one-list-page
  [id]
  (let [list (db/get-one-list [id])]
    (hic-p/html5
      (gen-page-head "So many things to do!")
      (headers)
      [:div {:class "container" :style "padding-top: 50px"}
      [:h1 (:name (first list))]
      [:table {:class "table table-striped table-hover table-condensed col-md-4"}
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
                  [:input {:type "submit" :value "delete" :class "btn btn-warning"}]]]])]
            [:h2 "Add Another TODO"]
            [:form {:action (format "/list/%s" id) :method "POST" :class "form-inline"}
              [:p "new item: " [:input {:type "text" :name "item" :class "form-control"}]
                              [:input {:type "hidden" :name "id" :value id}]
                              [:input {:type "submit" :value "add new item" :class "btn btn-primary"}]]]
            [:form {:action (format "/list/%s/delete" id) :method "POST" :class "form-inline"}
                [:input {:type "submit" :value "delete this list" :class "btn btn-danger"}]]])))

(defn all-lists-page
  []
  (let [all-lists (db/get-all-lists)]
    (hic-p/html5
      (gen-page-head "So many todo lists!")
      (headers)
      [:div {:class "container" :style "padding-top: 50px"}
      [:h1 "Here's your list of lists!"]
      (for [list all-lists]
        [:a {:href (format "/list/%s" (str (:id list)))}[:p (:name list)]])
      [:h2 "Add another list"]
      [:form {:action "/list" :method "POST" :class "form-inline"}
        [:p "new list: " [:input {:type "text" :name "name" :class "form-control"}]
                        [:input {:type "submit" :value "add new list" :class "btn btn-primary"}]]]]
      )))

(defn about-page
  []
  (hic-p/html5
    (gen-page-head "So many todo lists!")
    (headers)
    [:div {:class "container" :style "padding-top: 50px"}
    [:h1 "About this project"]
    [:p "This is a basic webapp to:"]
    [:ul [:li "help me learn ~*clojure*~"]
         [:li "keep track of #TODO items"]
         [:li "reinforce that learning new languages is fun"]]]))

(defn add-item-to-list-page
 [params]
 (db/add-todo-item-to-list params)
 {:status 302
  :headers {"Location" (format "/list/%s" (:id params)) }
  :body ""})

(defn add-list-page
 [params]
 (db/add-new-list params)
 {:status 302
  :headers {"Location" "/"}
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

(defn delete-list-page
    [id]
    (db/delete-list [id])
    {:status 302
     :headers {"Location" "/"}
     :body ":"})
