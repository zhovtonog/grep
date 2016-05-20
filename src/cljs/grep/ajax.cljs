(ns grep.ajax
  (:require [ajax.core :refer [GET POST]]))


(comment (defn getRes [target townId claim_type csrf timeout]
  (do
    (.log js/console "start req")
    (js/setTimeout  (ajax (str "/game/farm_town_info?town_id=" townId "&action=claim_load&h=" csrf)
          {:dataType "json"
           :type "POST"
           :data (str "json=%7B%22target_id%22%3A%22" target "%22%2C%22claim_type%22%3A%22" claim_type "%22%2C%22time%22%3A300%2C%22town_id%22%3A" townId "%2C%22nl_init%22%3Atrue%7D")
           :success  (fn [data] (.log js/console data.json))}) timeout))))


(comment (defn getData [state]
  (do
    (.log js/console "start req")
    (.log js/console "start req")
    (ajax (str "/game/data?town_id=" (:townId @state) "&action=get&h=" (:csrf @state))
          {:dataType "json"
           :type "POST"
           :async false
           :data (str "json=%7B%22types%22%3A%5B%7B%22type%22%3A%22map%22%2C%22param%22%3A%7B%22x%22%3A14%2C%22y%22%3A1%7D%7D%2C%7B%22type%22%3A%22bar%22%7D%2C%7B%22type%22%3A%22backbone%22%7D%5D%2C%22town_id%22%3A" (:townId @state) "%2C%22nl_init%22%3Afalse%7D" )
           :success  (fn [data] data)}))))



(defn getData [state callbeck]
  (POST (str "/game/data?town_id=" (:townId @state) "&action=get&h=" (:csrf @state))
        {:body (str "json=%7B%22types%22%3A%5B%7B%22type%22%3A%22map%22%2C%22param%22%3A%7B%22x%22%3A14%2C%22y%22%3A1%7D%7D%2C%7B%22type%22%3A%22bar%22%7D%2C%7B%22type%22%3A%22backbone%22%7D%5D%2C%22town_id%22%3A" (:townId @state) "%2C%22nl_init%22%3Afalse%7D" )
         :handler (fn [data] (callbeck data))
         :headers {:Accept "text/plain, */*; q=0.01" :Content-Type "application/x-www-form-urlencoded; charset=UTF-8" :X-Requested-With "XMLHttpRequest"}
         :keywords? true
         :response-format :json
         :error-handler (fn [data] data)}))



;json:{"types":[{"type":"map","param":{"x":14,"y":1}},{"type":"bar"},{"type":"backbone"}],"town_id":34273,"nl_init":false}
