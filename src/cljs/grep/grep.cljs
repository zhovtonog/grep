(ns grep.grep
  (:use [grep.log :only [l jl derefjl]]
        [jayq.core :only [$ css html document-ready ajax]]
        [grep.ajax :only [getData]]
        [grep.initialed :only [FarmTownPlayerRelations]]
        [grep.action :only [sendFarm]]))

(def state (atom {}))
(def Game window.Game)
(def Data window.Data)

(declare startBot)

(def cData (js->clj Data :keywordize-keys true))



;================================================

(defn dataModels [data]
  (-> data :json :backbone :models))

(defn dataCollection [data]
  (-> data :json :backbone :collections))



(defn getDevData [state callbeck]
  (do
    (l "dasdasdasdas")
    ;(.setTimeout js/window (callbeck cData) "30000")))
    (js/setTimeout (fn [] (callbeck cData)) 100)))
   ; ))



(defn applyData[state data]

  (if (< (:init_at state) (.getTime (js/Date.)))
    (do
      (doseq [concated (into [] (concat (dataCollection data) (dataModels data)))]
        (cond
          (= (:class_name concated) "FarmTownPlayerRelations") (FarmTownPlayerRelations state concated)

          )
        )
      (startBot state)
      )
    )
  )



(defn initGame [state]
  (let [game (js->clj window.Game :keywordize-keys true)]
    (swap! state assoc
           :csrf (game :csrfToken)
           :townId  (game :townId)
           :kurator (-> game :premium_features :curator)
           :init_at (dec (.getTime (js/Date.))))
    state
  ))

(defn initState [state]
  (let [partialedData (partial applyData state)]
      (initGame state)
      (getDevData state partialedData)
  ))


(defn goTask [state]
  (do
    (l "ggg")
  (-> state (sendFarm))
    ))


(defn startBot[state]
  (if (nil? (:init_at @state))
    (initState state)
    (goTask state)))



(document-ready (startBot state))








