(ns grep)





(defn doc-ready-handler []
  (let[ ready-state (. js/document -readyState)]
    (if (= "complete" ready-state)
      (do
        (.log js/console "page ready")
      ))))

(defn on-doc-ready []
  (aset  js/document "onreadystatechange" doc-ready-handler ))

(on-doc-ready)
