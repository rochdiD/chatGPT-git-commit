(defproject chatgpt_commit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :main chatgpt-commit.core
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.10.1"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.trace "0.7.11"]]
  :repl-options {:init-ns chatgpt-commit.core})
