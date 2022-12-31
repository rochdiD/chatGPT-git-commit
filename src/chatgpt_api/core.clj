(ns chatgpt-api.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def api-key "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")
(def chatgpt-api-url "https://api.openai.com/v1/completions")

(defn build-request-data [prompt]
  {:model "text-davinci-003"
   :prompt prompt
   :max_tokens 150
   :temperature 0.2
   })

(defn build-request-headers [api-key]
  {"Content-Type" "application/json"
   "Authorization" (str "Bearer " api-key)})

(defn post-to-api [url headers data]
  (client/post url
               {:headers headers
                :body (json/write-str data)
                :as :string
                :socket-timeout 5000}))

(defn read-body [response]
  (:body response))

(defn read-text [body]
  ;;The text of the response is inside the first index of vector (as a value) of "choices" key 
  (get
    (get  
      (get (json/read-str body) "choices") 0) "text"))

(defn clean-text [text]
  (clojure.string/replace text #"\n" ""))


(defn ask-chatgpt [prompt]
  (let [headers (build-request-headers api-key)
        data (build-request-data prompt)
        url chatgpt-api-url ]
      (clean-text
        (read-text
          (read-body
            (post-to-api url headers data))))))
            





