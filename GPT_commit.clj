(ns GPT-commit.core
  (:require [clojure.java.shell :as shell]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [chatgpt.core :as chatgpt]))

(defn list-new-and-modified-files []
  (-> (shell/sh "git" "ls-files" "--others" "--modified")
      :out
      (clojure.string/split-lines)))

(defn get-diff [file]
  (-> (shell/sh "git" "diff" file)
      :out
      clojure.string/trim))

(defn commit-file [file message]
  (shell/sh "git" "commit" "-m" message file))

(defn summarize-diff [file]
  (let [diff (get-diff file)
        prompt (str "Summarize the diff of this file: " diff)]
    (-> (chategpt/get-response prompt)
        :message)))

(defn commit-new-and-modified-files []
  (doseq [file (list-new-and-modified-files)]
    (commit-file file (summarize-diff file)))
