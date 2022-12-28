(ns chatgpt.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key "YOUR_API_KEY")

(defn get-response [prompt]
  (let [response (client/get "https://api.openai.com/v1/chat/gpt"
                             {:query-params {:prompt prompt
                                            :model "davinci"
                                            :api-key api-key}})]
    (json/read-str (:body response))))

(defn -main []
  (let [response (get-response "What is the weather like today?")]
    (println response)))