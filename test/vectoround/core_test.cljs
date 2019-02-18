(ns vectoround.core-test
  (:require [cljs.test :refer-macros [deftest is testing are]]
            [vectoround.core :refer [saferound round]]))

(def pre-rounded-map
  {:foo 60.19012964572332
   :bar 15.428802458406679
   :baz 24.381067895870007})

(def pre-rounded-vector
  [4.0001 3.2345 3.2321 6.4523 5.3453 7.3422])

(def pre-rounded-vectormap
  [{:name "foo" :value 60.19012964572332}
   {:name "bar" :value 15.428802458406679}
   {:name "baz" :value 24.381067895870007}])

(deftest vanilla-round
  (testing "round can round a float"
    (is (= (round 5.005 2) 5.01))
    (is (= (round 5.6 2) 5.6))
    (is (= (round 5.005 0) 5.0))
    (is (= (round 5.05 1) 5.1))
    (is (= (round 5.0049995 2) 5.00))))

(deftest round-map-test
  (let [result (saferound pre-rounded-map 0 nil)
        desired {:foo 60.0
                 :bar 16.0
                 :baz 24.0}]
    (testing "Safe round can round map"
      (is (= result desired)))))

(deftest round-vector-test
  (let [result (saferound pre-rounded-vector 2 nil)
        desired [4.0 3.24 3.23 6.45 5.35 7.34]]
    (testing "Safe round can round a vector"
      (is (= result desired)))))

(deftest round-vectormap-test
  (let [result (saferound pre-rounded-vectormap 0 :value)
        desired [{:name "foo" :value 60.0}
                 {:name "bar" :value 16.0}
                 {:name "baz" :value 24.0}]]
    (testing "Safe round can round a vector of maps"
      (is (= result desired)))))
