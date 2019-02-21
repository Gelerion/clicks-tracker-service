(ns com.gelerion.config.config
  (:require [mount.core :as mount :refer [defstate]]
            [aero.core :as aero :refer [read-config]]
            [clojure.java.io :as io]))


(defn- read-config-file []
  (read-config (io/resource "system_config.edn")))

(def make-configuration (memoize read-config-file))

(defstate config
          :start (make-configuration))