(defproject clojure-todo-list "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler clojure-todo-list.core/handler}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot clojure-todo-list.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
