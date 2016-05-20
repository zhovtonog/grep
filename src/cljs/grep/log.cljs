(ns grep.log)

(defn l [data]
  (.log js/console data))

(defn jl [data]
  (.log js/console (clj->js data)))

(defn derefjl [data]
  (.log js/console (clj->js @data)))
