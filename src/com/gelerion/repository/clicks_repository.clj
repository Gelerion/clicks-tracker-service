(ns com.gelerion.repository.clicks-repository
  (:require [com.gelerion.config.config :refer [config]]
            [taoensso.timbre :as timbre :refer [debug info]]
            [org.httpkit.client :as http]
            [com.gelerion.states :refer [click-repository-chan]]
            [clojure.core.async :refer [>!! <!! alts!! chan]]
            [mount.core :refer [defstate]]))

(def stopped? (atom false))

(defn- stop-click-repository []
  (swap! stopped? (fn [current-state] true)))

(defn- url-for
  [click]
  (let [base-url (:click-saver-url config)
        app_id (get click "app_id")]
    (str base-url app_id)))

(defn- options
  [click]
  (let [advertiser_id (get click "advertiser_id")
        pid (get click "pid")
        ttl (get click "ttl")]
    {:query-params {:advertiser_id advertiser_id
                    :pid           pid
                    :ttl           ttl}
     :timeout      4000}))

;synchronous
(defn save
  [click]
  (debug (format "Saving new click %s into db" click))
  (let [url (url-for click)
        {:keys [status error] :as response} @(http/get url (options click))]
    (debug (format "Save request to %s returned %s" url status))
    response))

(defn save-async
  [click]
  (debug (format "Saving new click %s into db" click))
  (let [url (url-for click)]
    (http/get url (options click)
              ; callback
              (fn [{:keys [status headers body error]}]
                (if error
                  (info "Failed to save" error)
                  (info "Click was successfully saved"))))))

(defn start-click-repository []
  (while (not @stopped?)
    (let [click (<!! click-repository-chan)]
      (save click))))

(defstate clicks-repository
          :start (start-click-repository)
          :stop (stop-click-repository))