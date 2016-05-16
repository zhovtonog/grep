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


(defn getData [state callbeck]
  (do
    (.log js/console "start req")
    (ajax (str "/game/data?town_id=" (:townId state) "&action=get&h=" (:csrf state))
          {:dataType "json"
           :type "POST"
           :data (str "json=%7B%22types%22%3A%5B%7B%22type%22%3A%22map%22%2C%22param%22%3A%7B%22x%22%3A14%2C%22y%22%3A1%7D%7D%2C%7B%22type%22%3A%22bar%22%7D%2C%7B%22type%22%3A%22backbone%22%7D%5D%2C%22town_id%22%3A" (:townId state) "%2C%22nl_init%22%3Afalse%7D" )
           :success  (fn [data] (callbeck data))})))



;json:{"types":[{"type":"map","param":{"x":14,"y":1}},{"type":"bar"},{"type":"backbone"}],"town_id":34273,"nl_init":false}
