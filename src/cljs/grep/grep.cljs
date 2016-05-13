(ns grep.grep
  (:use [jayq.core :only [$ css html document-ready ajax]]
        [grep.ajax :only [getRes]]))


(def state (atom {}))
(def Game window.Game)
(def Data window.Data)

(def cData (js->clj Data :keywordize-keys true))


(defn l [data]
  (.log js/console data))

(defn jl [data]
  (.log js/console (clj->js data)))


(defn init [state Game Data]
  (let [s state
        g (js->clj Game)
        d (js->clj Data)]
    ;(l (pr-str g))
    ;(l (get g "csrfToken"))
    (swap! s assoc
           :csrf (get g "csrfToken")
           :townId  (get g "townId"))
    ;(l (get d "json"))
    ;(l Game)
    (l (clj->js @s))
    ))


(def dd {:key "val" :key2 "val2"})

(defn testajj [obj]
  (do
    (.log js/console "start req")
    (ajax "./api/res.json"
          {:dataType "json"
           :type "POST"
           :async false
           :success  (fn [data] (do
                                  (l (clj->js obj))
                                  obj))
           :error (fn [data] (log js/console data))
           })))



;(jl (-> cData :json :backbone :collections))


(defn parseDat [data]
  (let [farmCollection (-> data :json :backbone :collections)
        farmList (filterv #(= (get % :class_name) "FarmTownPlayerRelations") farmCollection)
        canFarm (filter #(= (-> % :d :relation_status) 1) (-> (get farmList 0) :data))]

    (jl farmCollection)
     (jl canFarm)
      (mapv (fn [data] (jl  (-> data :d :farm_town_id))) canFarm)
      (loop [res []
             f canFarm]
        (if (> (count f) 0)
          (recur (conj res (-> canFarm first :d :farm_town_id)) (rest canFarm))
          (jl res)))



    ))

(parseDat cData)


;(-> % :d :relation_status)


(comment (-> dd (testajj)
    (testajj)
    (testajj)
    (testajj)))



(defn pp [obj]
  (do
    (l (clj->js obj))
    obj
    ))












;(swap! state assoc :key1 "value" :key2 "value3" :key3 {:qq "zz" :mm "hh"})


;(swap! state assoc-in [:key3 :qq] "gggggg")


;(l (clj->js @a))

(defn startBot[]
  (do
    (init state Game Data)))

(document-ready (startBot))








