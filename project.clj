(defproject
  plan-ahead
  "0.1.0-SNAPSHOT"
  :description
  "FIXME: write description"
  :url
  "http://example.com/FIXME"
  :dependencies
  [[prone "0.8.0"]
   [log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [selmer "0.7.7"]
   [com.taoensso/tower "3.0.2"]
   [markdown-clj "0.9.58" :exclusions [com.keminglabs/cljx]]
   [im.chit/cronj "1.4.3"]
   [com.taoensso/timbre "3.3.1"]
   [com.h2database/h2 "1.4.182"]
   [noir-exception "0.2.3"]
   [korma "0.4.0"]
   [lib-noir "0.9.5"]
   [org.clojure/clojure "1.6.0"]
   [environ "1.0.0"]
   [ring-server "0.3.1"]]
  :repl-options
  {:init-ns plan-ahead.repl}
  :jvm-opts
  ["-server"]
  :plugins
  [[lein-ring "0.9.0"] [lein-environ "1.0.0"] [lein-ancient "0.5.5"]]
  :ring
  {:handler plan-ahead.handler/app,
   :init plan-ahead.handler/init,
   :destroy plan-ahead.handler/destroy,
   :uberwar-name "plan-ahead.war"}
  :profiles
  {:uberjar {:omit-source true, :env {:production true}, :aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies
    [[ring-mock "0.1.5"]
     [ring/ring-devel "1.3.2"]
     [pjstadig/humane-test-output "0.6.0"]],
    :injections
    [(require 'pjstadig.humane-test-output)
     (pjstadig.humane-test-output/activate!)],
    :env {:dev true}}}
  :uberjar-name
  "plan-ahead.jar"
  :min-lein-version "2.0.0")