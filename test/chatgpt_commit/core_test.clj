(ns chatgpt-commit.core-test
  (:require [clojure.test :refer :all]
            [chatgpt-commit.core :refer :all]))

(deftest a-test
  (println (list-new-and-modified-files)))
