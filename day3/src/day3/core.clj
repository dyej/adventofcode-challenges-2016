(ns day3.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(require ['clojure.string :as 'str])
(use '[clojure.string :only (split trim)])

(def file (io/resource "input.txt"))

(defn split-on-space [s]
         (split (trim s) #"\s+"))

(defn get-lines [file]
  (str/split-lines (slurp file)))

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s)))

(defn check-possible
  [part]
  (let [[x y z] (split-on-space part)]
    (if (> (+ (parse-int x) (parse-int y)) (parse-int z))
      (if (> (+ (parse-int x) (parse-int z)) (parse-int y))
        (if (> (+ (parse-int y) (parse-int z)) (parse-int x))
          1 
          0) 
        0) 
      0)))
    
    


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
