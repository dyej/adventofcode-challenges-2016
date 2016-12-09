(ns day3.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(require ['clojure.string :as 'str])

(def file (io/resource "input.txt"))

(defn get-lines [file]
  (str/split-lines (slurp file)))

(defn check-possible
  [part]
  (println part)
  (let [[x y z] part]
    1))


(defn consume-input
  [input]
  (loop [remaining-input input
         total-possible 0]
    (if (empty? remaining-input)
      total-possible
      (let [[part & remaining] remaining-input]
        (recur remaining
          (+ total-possible (check-possible (str part))))))))



(defn -main [file]
  (consume-input (get-lines file)))
