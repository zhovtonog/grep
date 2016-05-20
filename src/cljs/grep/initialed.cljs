(ns grep.initialed)


(defn FarmTownPlayerRelations [state obj]
  (let [canfarm []
        data (:data obj)
        filtred (filterv #(= (-> % :d :relation_status)) data)]
    (loop [f filtred
           result []]
      (if-not (> (count f) 0)
        (swap! state assoc :farm {:farmList result :action_at (dec (.getTime (js/Date.)))})
        (recur (rest f) (conj result (-> (first f) :d :farm_town_id))))


      )
    state
    ))
