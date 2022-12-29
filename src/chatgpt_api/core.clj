(ns chatgpt-api.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key "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")
(def chatgpt-api-url "https://api.openai.com/v1/models/text-davinci-003")

(#_(defn build-request-data [prompt]
  {:model "text-davinci-003"
   }))

(defn build-request-headers []
  {"Authorization" (str "Bearer " api-key)})

(defn send-request [headers]
  (let [response (client/get chatgpt-api-url
                              {:headers headers
                               :as :string
                               :socket-timeout 5000})]
    (println (:body response) :message)
    (:body response) :message))

(defn ask-chatgpt [prompt]
  (let [headers (build-request-headers)]
    (send-request  headers)))


(defn -main []
  (let [response (ask-chatgpt "What is the weather like today?")]
    (println response)))


