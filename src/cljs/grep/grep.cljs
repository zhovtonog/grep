(ns grep.grep
  (:use [jayq.core :only [$ css html document-ready ajax]]
        [grep.ajax :only [getRes getData]]))

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

(defn initStatic [state]
  (let [game (js->clj window.Game)]
    (swap! state assoc
           :csrf (get game "csrfToken")
           :townId  (get game "townId"))
  ))

(def dd {:key "val" :key2 "val2"})

(defn testajj [obj st]
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



(defn getFarmList [obj]
  (loop [ress []
             ff obj]
          (if (< (count ff) 1)
            ress
            (recur (conj ress (-> (first ff) :d :farm_town_id)) (rest ff))

        )))

(defn getDataOfClass [accData id]
  (let [collection (-> accData :json :backbone :collections)
        className (filterv #(= (get % :class_name) id) collection)
        data (filter #(= (-> % :d :relation_status) 1) (-> (get className 0) :data))]

    data))


(defn parseDat [data]
  (let [farmCollection (-> data :json :backbone :collections)
        farmList (filterv #(= (get % :class_name) "FarmTownPlayerRelations") farmCollection)
        canFarm (filter #(= (-> % :d :relation_status) 1) (-> (get farmList 0) :data))]

    (jl farmCollection)
     (jl canFarm)
      ;(mapv (fn [data] (jl  (-> data :d :farm_town_id))) canFarm)
      (jl (getFarmList canFarm))
    ))


(defn initFarm [state data]
  (let [farmList (getFarmList (getDataOfClass data "FarmTownPlayerRelations"))]
  (swap! state assoc :farm {:farmList farmList :action_at (.getTime (js/Date.))})))


;(initFarm state cData)

;(jl state)


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







(defn qq [data]
  (do
    (l "get data good")
  (l data)
    )
  )




;(swap! state assoc :key1 "value" :key2 "value3" :key3 {:qq "zz" :mm "hh"})


;(swap! state assoc-in [:key3 :qq] "gggggg")


;(l (clj->js @a))

(defn startBot[]
  (do
    ;(init state Game Data)
    (-> state
        (initStatic)
        (getData qq)
        ;(initFarm Data)
        (jl))
    ))

(document-ready (startBot))








