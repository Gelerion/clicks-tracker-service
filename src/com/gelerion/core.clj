(ns com.gelerion.core
  (:require [com.gelerion.system :as system]
            [mount.core :as mount]
            [taoensso.timbre :as timbre :refer [info error spy]])

  (:gen-class))

(defn -main
  [& args]
  (timbre/set-level! :info)
  (let [sys (system/get-system)]
    (mount/start sys)))


