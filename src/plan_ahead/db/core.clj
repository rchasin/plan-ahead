(ns plan-ahead.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [plan-ahead.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity users)

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))

(defentity dances)

(defn save-dance
  [name location start_time end_time]
  (insert dances
          (values {:name name
                   :location location
                   :start_time start_time
                   :end_time start_time
                   :is_active true})))

(defn get-dances []
  (select dances))
