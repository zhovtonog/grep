(ns grep.ajax
  (:use [jayq.core :only [ajax]]))


(defn getRes []
  (do
    (.log js/console "start req")
    (ajax "/game/farm_town_info?town_id=34273&action=claim_load&h=037236f1a75"
          {:dataType "json"
           :type "POST"
           :data "json=%7B%22target_id%22%3A%229251%22%2C%22claim_type%22%3A%22normal%22%2C%22time%22%3A300%2C%22town_id%22%3A34273%2C%22nl_init%22%3Atrue%7D"
           :success  (fn [data] (.log js/console data.json))})))
