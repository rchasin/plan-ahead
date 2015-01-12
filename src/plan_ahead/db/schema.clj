(ns plan-ahead.db.schema
  (:require [clojure.java.jdbc :as sql]
            [clojure.java.io :refer [file]]
            [noir.io :as io]))

(def db-store (str (.getName (file ".")) "/site.db"))

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname db-store
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (file (str db-store ".mv.db"))))

; This table is provided by the sample guestbook app. TODO use or delete it.
(defn create-users-table
  []
  (sql/db-do-commands
    db-spec
    (sql/create-table-ddl
      :users
      [:id "varchar(20) PRIMARY KEY"]
      [:first_name "varchar(30)"]
      [:last_name "varchar(30)"]
      [:email "varchar(30)"]
      [:admin :boolean]
      [:last_login :time]
      [:is_active :boolean]
      [:pass "varchar(100)"])))

(defn create-dances-table
  []
  (sql/db-do-commands
    db-spec
    (sql/create-table-ddl
      :dances
      [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
      [:name "varchar(30)"]
      [:location "varchar(200)"]
      [:start_time :time]
      [:end_time :time]
      [:is_active :boolean])))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-users-table)
  (create-dances-table)
  )
