(ns grep.grep
  (:use [jayq.core :only [$ css html document-ready]]
        [grep.ajax :only [getRes]]))

(def Game window.Game)
(defn l [data]
  (.log js/console data))

(defn startBot[]
  (do
    (l Game)


    ))



(document-ready (startBot))








