(ns day1.core
  (:gen-class))

(def directions '["L5" "R1" "L5" "L1" "R5" "R1" "R1" "L4" "L1" "L3" "R2" "R4" "L4" "L1" "L1" "R2" "R4" "R3" "L1" "R4" "L4" "L5"
                  "L4" "R4" "L5" "R1" "R5" "L2" "R1" "R3" "L2" "L4" "L4" "R1" "L192" "R5" "R1" "R4" "L5" "L4" "R5" "L1" "L1" "R48"
                  "R5" "R5" "L2" "R4" "R4" "R1" "R3" "L1" "L4" "L5" "R1" "L4" "L2" "L5" "R5" "L2" "R74" "R4" "L1" "R188" "R5" "L4"
                  "L2" "R5" "R2" "L4" "R4" "R3" "R3" "R2" "R1" "L3" "L2" "L5" "L5" "L2" "L1" "R1" "R5" "R4" "L3" "R5" "L1" "L3"
                  "R4" "L1" "L3" "L2" "R1" "R3" "R2" "R5" "L3" "L1" "L1" "R5" "L4" "L5" "R5" "R2" "L5" "R2" "L1" "L5" "L3" "L5"
                  "L5" "L1" "R1" "L4" "L3" "L1" "R2" "R5" "L1" "L3" "R4" "R5" "L4" "L1" "R5" "L1" "R5" "R5" "R5" "R2" "R1" "R2"
                  "L5" "L5" "L5" "R4" "L5" "L4" "L4" "R5" "L2" "R1" "R5" "L1" "L5" "R4" "L3" "R4" "L2" "R3" "R3" "R3" "L2" "L2"
                  "L2" "L1" "L4" "R3" "L4" "L2" "R2" "R5" "L1" "R2"])

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s)))

(defn walk-blocks
  "Expects a set of coordinates and a direction/distance to walk and returns new coordinates"
  [coordinates part]
  (case (get coordinates 1)
    "north"
    (if (= (subs part 0 1) "L")
      [[(- (get (get coordinates 0) 0) (parse-int (subs part 1 (.length part)))) (get (get coordinates 0) 1)]
       "west"]
      [[(+ (get (get coordinates 0) 0) (parse-int (subs part 1 (.length part)))) (get (get coordinates 0) 1)]
       "east"])
    "east"
    (if (= (subs part 0 1) "L")
      [[(get (get coordinates 0) 0) (+ (get (get coordinates 0) 1) (parse-int (subs part 1 (.length part))))]
       "north"]
      [[(get (get coordinates 0) 0) (- (get (get coordinates 0) 1) (parse-int (subs part 1 (.length part))))]
       "south"])
    "south"
    (if (= (subs part 0 1) "L")
      [[(+ (get (get coordinates 0) 0) (parse-int (subs part 1 (.length part)))) (get (get coordinates 0) 1)]
       "east"]
      [[(- (get (get coordinates 0) 0) (parse-int (subs part 1 (.length part)))) (get (get coordinates 0) 1)]
       "west"])
    "west"
    (if (= (subs part 0 1) "L")
      [[(get (get coordinates 0) 0) (- (get (get coordinates 0) 1) (parse-int (subs part 1 (.length part))))]
       "south"]
      [[(get (get coordinates 0) 0) (+ (get (get coordinates 0) 1) (parse-int (subs part 1 (.length part))))]
       "north"])))

(defn follow-directions
  "Expects a list of directions and returns destination coordinates"
  [directions]
  (loop [remaining-directions directions
         coordinates [[0 0] "north"]]
    (if (empty? remaining-directions)
      (+ (get (get coordinates 0) 0) (get (get coordinates 0) 1))
      (let [[part & remaining] remaining-directions]
        (recur remaining
          (walk-blocks coordinates part))))))

(defn -main []
   (println (follow-directions directions)))
    


               
