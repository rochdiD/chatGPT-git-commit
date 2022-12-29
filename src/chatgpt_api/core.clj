(ns chatgpt-api.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key "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")
(def chatgpt-api-url "https://api.openai.com/v1/completions")

(defn build-request-data [prompt]
  {:model "text-davinci-003"
   :prompt prompt
   :max_tokens 30
   :temperature 0.3
   })

(defn build-request-headers []
  {"Content-Type" "application/json"
   "Authorization" (str "Bearer " api-key)})

(defn send-request [headers data]
  (let [response (client/post chatgpt-api-url
                              {:headers headers
                               :body (json/write-str data)
                               :as :string
                               :socket-timeout 5000})]
    (println (:body response) )
    (:body response)))

(defn ask-chatgpt [prompt]
  (let [headers (build-request-headers)
        data (build-request-data prompt)]
    (send-request headers data)))


(defn -main []
  (let [response (ask-chatgpt "What is the weather like today?")]
    (println response)))


