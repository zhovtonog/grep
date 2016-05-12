(ns grep.grep
  (:use [jayq.core :only [$ css html document-ready]]
        [grep.ajax :only [getRes]]))


(def state (atom {}))
(def Game window.Game)
(def Data window.Data)


(defn l [data]
  (.log js/console data))


(defn init [state Game Data]
  (let [s state
        g (js->clj Game)
        d (js->clj Data)]
    ;(l (pr-str g))
    ;(l (get g "csrfToken"))
    (swap! s assoc
           :csrf (get g "csrfToken")
           :townId  (get g "townId"))
    (l (get d "json"))
    (l Game)
    (l (clj->js s))
    ))





















;(swap! state assoc :key1 "value" :key2 "value3" :key3 {:qq "zz" :mm "hh"})


;(swap! state assoc-in [:key3 :qq] "gggggg")


;(l (clj->js @a))

(defn startBot[]
  (do
    (init state Game Data)))

(document-ready (startBot))








