(ns chatgpt-commit.core-test
  (:require [clojure.test :refer :all]
            [chatgpt-commit.core :refer :all]
            [chatgpt-api.core :refer :all]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.tools.trace :as trace]))

;(println (json/write-str  (build-request-data "how are you?")))

;(commit-files (list-not-staged-files))


(trace/trace (commit-files (list-not-staged-files)))

;(prn (list-not-staged-files))

;(commit-file "src/chatgpt_commit/core.clj")

;(prn (summarize-diff "src/chatgpt_api/core.clj"))



(#_(deftest files-to-commit-test
  (do (println (list-untracked-files)
      (println (list-not-staged-files))))))


(#_(deftest test-ask-chatgpt
  (testing "Test ask-chatgpt function"
    (let [response (ask-chatgpt "What is your name?" )]
      (println response)
      (is (not (nil? response)))
      (is (string? response))))))    