(comment (defn init [state Game Data]
  (let [s state
        g (js->clj Game)
        d (js->clj Data)]
    (swap! s assoc
           :csrf (get g "csrfToken")
           :townId  (get g "townId"))
    (l (clj->js @s))
    )))



(def dd {:key "val" :key2 "val2"})





(comment (defn parseDat [data]
  (let [farmCollection (-> data :json :backbone :collections)
        farmList (filterv #(= (get % :class_name) "FarmTownPlayerRelations") farmCollection)
        canFarm (filter #(= (-> % :d :relation_status) 1) (-> (get farmList 0) :data))]

    (jl farmCollection)
     (jl canFarm)
      ;(mapv (fn [data] (jl  (-> data :d :farm_town_id))) canFarm)
      (jl (getFarmList canFarm))
    )))





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


;(l (clj->js @a



(defn dataModels [data]
  (-> data :json :backbone :models))

(defn dataCollection [data]
  (-> data :json :backbone :collections))


(defn startFarm [state]
  (do
  (jl @state)
    state
    )



  )

(defn getFarmList [obj]
  (loop [ress []
             ff obj]
          (if (< (count ff) 1)
            ress
            (recur (conj ress (-> (first ff) :d :farm_town_id)) (rest ff))

        )))

(defn getDataOfClass [accData id]
  (let [className (filterv #(= (get % :class_name) id) (dataCollection accData))
        data (filter #(= (-> % :d :relation_status) 1) (-> (get className 0) :data))]
    data))


(defn initFarm [state data]
  (let [farmList (getFarmList (getDataOfClass data "FarmTownPlayerRelations"))]
    (swap! state assoc :farm {:farmList farmList :action_at (.getTime (js/Date.))})
    state
    ))

(defn initBuild [state data]
  (let [TownBuildings (filterv #(= (get % :class_name) "TownBuildings") (dataCollection data))
        builded (-> TownBuildings (first) :data (first) :d)
        Town (filterv #(= (get % :model_class_name) "Town") (dataModels data))
        townData (-> Town (first) :data)
        BuildingOrders (filterv #(= (get % :class_name) "BuildingOrders") (dataCollection data))
        BuildingOrdersData (-> BuildingOrders (first) :data)
        part (partial (fn [state order]
                    (swap! state update-in [:build :builded (keyword (-> order :d :building_type))] inc)
                        ) state)]
    (swap! state assoc :build {:builded builded})
    (swap! state assoc :build {:buildingOrders BuildingOrdersData})
    (swap! state assoc :town {:resources (townData :resources) :maxStor (townData :storage)})

    (doall (map part BuildingOrdersData))
    (jl BuildingOrdersData)
    state
  ))



(comment (defn applyData [state data]
  (-> state (initFarm data)
            (initBuild data)
)))

(defn initGame [state]
  (let [game (js->clj window.Game :keywordize-keys true)]
    (swap! state assoc
           :csrf (game :csrfToken)
           :townId  (game :townId)
           :kurator (-> game :premium_features :curator))
    state
  ))


(comment (defn backinitData [state]
  (applyData state cData)))


(defn XXgetData [state callbeck]
  (do
    (l "gg")
    (l state)
    (l callbeck)
    (l cData)

    (callbeck cData)
    )
  )


(comment (defn initData [state]
  (let [data (getData state)]
    (l "ggg")
    (l data)
  )))




  (comment (doseq [collection (dataCollection data)
            models (dataModels data)
            comment concated (into [] (concat collection models))]

      ))
