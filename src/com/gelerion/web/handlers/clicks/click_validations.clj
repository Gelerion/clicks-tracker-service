(ns com.gelerion.web.handlers.clicks.click-validations
  (:require [schema.core :as schema]
            [clojure.walk :refer [keywordize-keys]]))

(schema/def AdvertiserId
  (schema/constrained
    String
    #(re-matches #"^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$" (name %))
    'AdvertiserId))

(def ClickRequest
  {:app_id                    schema/Str
   :advertiser_id             AdvertiserId
   :pid                       schema/Str
   :campaign_id               schema/Str
   (schema/optional-key :ttl) schema/Str})

(defn valid-click?
  [params]
  (nil? (schema/check ClickRequest (keywordize-keys params))))
