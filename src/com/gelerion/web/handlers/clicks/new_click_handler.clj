(ns com.gelerion.web.handlers.clicks.new-click-handler
  (:require [af-trng-wh-ds-520.web.handlers.clicks.click-validations :refer [valid-click?]]
            [af-trng-wh-ds-520.config.config :refer [config]]
            [af-trng-wh-ds-520.states :refer [click-repository-chan]]
            [taoensso.timbre :as timbre :refer [debug info]]
            [clojure.core.async :refer [>!!]]))

(defn- get-app-id
  [params]
  (get params "app_id"))

(defn- redirect-response
  [params]
  {:status  302
   :body    "<h1>OK</h1>"
   :headers {"Location" (str (:play-store-link config) (get-app-id params))}})

(defn- invalid-click-response
  []
  {:status 400
   :body "<h1>Invalid Click</h1>"})

(defn- internal-error-response
  []
  {:status 500
   :body "<h1>Internal Error</h1>"})

(defn- save-and-redirect-async
  [click]
  (>!! click-repository-chan click)
  (redirect-response click))

(defn handle-new-click
  [{:keys [query-params] :as req}]
  (debug (format "Handle a new click %s" query-params))
  (if (valid-click? query-params)
    (save-and-redirect-async query-params)
    (invalid-click-response)))


