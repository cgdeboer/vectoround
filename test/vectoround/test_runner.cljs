(ns vectoround.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [vectoround.core-test]))

(doo-tests 'vectoround.core-test)
