(defproject clicks-tracker-service "1.0.0"
  :description "Record clicks"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [http-kit "2.3.0"]
                 [compojure "1.6.1"]
                 [mount "0.1.16"]
                 [aero "1.1.3"]
                 [com.taoensso/timbre "4.10.0"]
                 [prismatic/schema "1.1.10"]
                 [org.clojure/core.async "0.4.490"]]
  :main ^:skip-aot com.gelerion.core
  :profiles {:uberjar {:aot :all}})
