(defproject grep "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "LATEST"]
                 [org.clojure/clojurescript "LATEST"]
                 [jayq "2.5.4"]
                 [cljs-ajax "LATEST"]]
  :plugins [[lein-cljsbuild "1.1.3"]]
  :cljsbuild
  {:builds
   [{:compiler
     {:output-to "resources/public/js/grep.js",
      :optimizations :simple,
      :pretty-print true},
     :source-paths ["src/cljs"]}]})
