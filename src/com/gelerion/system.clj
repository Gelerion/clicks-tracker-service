(ns com.gelerion.system
  (:require [mount.core :as mount]
            [com.gelerion.web.http-server :refer [http-server]]
            [com.gelerion.config.config :refer [config]]
            [com.gelerion.states :refer [click-repository-chan]]
            [com.gelerion.domain.repository.clicks-repository :refer [clicks-repository]]
            ))

(defn get-system []
  (-> [#'config
       #'http-server
       #'click-repository-chan
       #'clicks-repository]
      mount/only))
