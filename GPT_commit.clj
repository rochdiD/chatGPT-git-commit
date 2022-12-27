(ns GPT-commit.core
  (:require [clojure.java.shell :as shell]))

(defn list-changed-files []
  (-> (shell/sh "git" "diff" "--name-only")
      :out
      (clojure.string/split-lines)))

(defn commit-file [file message]
  (shell/sh "git" "commit" "-m" message file))

(defn commit-changed-files [message]
  (doseq [file (list-changed-files)]
    (commit-file file message)))
