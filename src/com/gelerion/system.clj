(ns com.gelerion.system
  (:require [mount.core :as mount]
            [af-trng-wh-ds-520.web.http-server :refer [http-server]]
            [af-trng-wh-ds-520.config.config :refer [config]]
            [af-trng-wh-ds-520.states :refer [click-repository-chan]]
            [af-trng-wh-ds-520.domain.repository.clicks-repository :refer [clicks-repository]]
            ))

(defn get-system []
  (-> [#'config
       #'http-server
       #'click-repository-chan
       #'clicks-repository]
      mount/only))
