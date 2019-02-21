(ns com.gelerion.web.http-server
  (:require [org.httpkit.server :as http-kit]
            [mount.core :as mount :refer [defstate]]
            [com.gelerion.config.config :refer [config]]
            [taoensso.timbre :as timbre :refer [info]]
            [compojure.core :refer [routes GET]]
            [com.gelerion.web.handlers.clicks.new-click-handler :as handlers]
            [ring.middleware.params :as middleware]))

(defn endpoints []
  (routes
    (GET "/health-check" [:as req] {:status  200})
    (GET "/new-click" [:as req] (handlers/handle-new-click req))))

(defn start-server []
  (info (format "Starting http server on port %s" (:server-port config)))
  (http-kit/run-server
    (middleware/wrap-params (endpoints))
    {:port (:server-port config)}))

(defstate http-server
          :start (start-server)
          :stop (http-server :timeout 100))