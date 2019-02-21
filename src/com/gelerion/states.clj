(ns com.gelerion.states
  (:require [mount.core :refer [defstate]]
            [clojure.core.async :refer [chan buffer]]))

(defstate click-repository-chan
          :start (chan (buffer 100)))