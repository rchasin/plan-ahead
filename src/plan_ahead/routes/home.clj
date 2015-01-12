(ns plan-ahead.routes.home
  (:require [compojure.core :refer :all]
            [plan-ahead.layout :as layout]
            [plan-ahead.util :as util]
            [noir.response :as response]
            [plan-ahead.db.core :as model]
            ))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (layout/render "about.html"))

;; TODO this surely belongs in a more specific file!
(defn list-dances []
  (model/get-dances))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/dances" [] (response/json (list-dances)))
  )
