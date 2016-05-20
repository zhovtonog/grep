(ns grep.action
  (:use [grep.log :only [l jl derefjl]]
        ))


(defn f [& args]
  (do
    (l "ggghhhh")
    (jl args)))

(defn sendFarm [state]
  (let [s state
        farm (-> @state :farm :farmList)]
    (f farm 55 66 77 888 99)))
