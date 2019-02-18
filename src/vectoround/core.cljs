(ns vectoround.core)

(defn number-map [i val]
  {:value val :order i :original val})

(defn denary [places]
  (Math/pow 10 places))

(defn round [num places]
  (let [scaler (denary places)]
    (when num
      (-> num (* scaler) Math/round (/ scaler)))))

(defn sum-numbers [numbers]
  (reduce + (map #(:value %) numbers)))

(defn round-vector [vec places]
  (let [numbers (map-indexed number-map vec)
        orig-sum (-> numbers sum-numbers (round places))
        local (map #(assoc % :value (round (:original %) places)) numbers)
        local-sum (-> local sum-numbers (round places))
        min-inc (/ (denary places))
        diff (-> orig-sum (- local-sum) (round places))
        increment (if (> diff 0) min-inc (* -1 min-inc))
        local (sort-by #(- (:original %) (:value %)) local)
        local (->> (if (< diff 0) local (reverse local)) (into []))
        limit (min (-> diff Math/abs (/ min-inc) int) (count local))
        head (map #(assoc % :value (+ increment (:value %)))
                  (subvec local 0 limit))
        tail (subvec local limit (count local))]
    (into [] (map #(-> (:value %) (round places))
                  (sort-by #(:order %) (concat head tail))))))


(defn round-maps [entities places field]
  (let [new-values (round-vector (map field entities) places)]
    (into [] (map-indexed (fn [i x] (assoc x field (nth new-values i)))
                          entities))))

(defn round-sequence [entities places field]
  (let [with-field? (and field (every? map? entities))]
    (if with-field?
      (round-maps entities places field)
      (round-vector entities places))))

(defn round-map [entities places]
  (let [new-values (round-vector (vals entities) places)]
    (zipmap (keys entities) new-values)))

(defn saferound [entities places field]
  (if (sequential? entities)
    (round-sequence entities places field)
    (round-map entities places)))
