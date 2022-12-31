(ns chatgpt-commit.core
  (:require [clojure.java.shell :as shell]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [chatgpt-api.core :as chatgpt-api]))

(defn list-untracked-files []
  (-> (shell/sh "git" "ls-files" "--others" "--exclude-standard")
      :out
      (clojure.string/split-lines)))

(defn list-not-staged-files []
  (-> (shell/sh "git" "diff" "--name-only")
      :out
      (clojure.string/split-lines)))

(defn get-diff [file]
  (-> (shell/sh "git" "diff" file)
      :out
      clojure.string/trim))

(defn git-add-and-commit [file message]
  (do (shell/sh "git" "add" file)
      (shell/sh "git" "commit" "-m" message)))

(defn summarize-diff [diff]
  (let [prompt (str "Suggest a detailed, technical and professional git message to this diff, no need to start with 'Commit:': " diff)]
    (chatgpt-api/ask-chatgpt prompt)))

(defn commit-file [file]
  (git-add-and-commit file (summarize-diff (get-diff file))))

(defn commit-new-and-modified-files [list-of-files]
  (for [file list-of-files]
    (commit-file file)))

(defn -main []
  (do
  (println "start")
  (commit-new-and-modified-files (list-not-staged-files))
  (println "end")))