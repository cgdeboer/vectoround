Vectoround: Sum-safe Rounding for collections
=============================================

.. image:: https://travis-ci.org/cgdeboer/vectoround.svg?branch=master
    :target: https://travis-ci.org/cgdeboer/vectoround

Leinengen coordinates:

.. image:: http://clojars.org/cgdeboer/vectoround/latest-version.svg
    :target: http://clojars.org/cgdeboer/vectoround ]

Vectoround is a sum-safe rounding library for
collections (lists, vectors, sets, maps).

Example Code:

.. code-block:: clojure

  (ns sample.core
    (:require [vectoround.core :refer [saferound round]])

  (def pre-rounded-vectormap
    [{:name "foo" :value 60.19012964572332}
     {:name "bar" :value 15.428802458406679}
     {:name "baz" :value 24.381067895870007}])

  (saferound pre-rounded-vectormap 0 :value)

  ;; => [{:name "foo", :value 60.0}
  ;;     {:name "bar", :value 16.0}
  ;;     {:name "baz", :value 24.0}]

  ;; => original sum (100) is preserved.


How It Works
---------------
Vectoround provides a function called :code:`saferound` that takes the
following ordered inputs:

a collection: map, vector, list or set.

places (int): Places for rounding.
    Number of places each item in the set should be rounded to.

field: :code:`:keyword` if the collection is a list/vector of maps, else pass :code:`nil`

    Vectoround uses the 'difference' strategy and seeks to minimize the
    sum of the array of the differences between the original value and the
    rounded value of each item in the collection. It will adjust the items
    with the largest difference to preserve the sum.

will return :code:`nil` or the same data structure that was passed with the
rounded values.


Feature Support
---------------

Vectoround definitely supports at least these collections.

- `list` (either list of floats, or list of maps)
- `vector` (either vector of floats, or vector of maps)
- `map` (rounds the values)


Documentation
-------------

Documentation beyond this readme not available.


How to Contribute
-----------------

#. Check for open issues or open a fresh issue to start a discussion around a feature idea or a bug.
#. Fork `the repository`_ on GitHub to start making your changes to the **master** branch (or branch off of it).
#. Write a test which shows that the bug was fixed or that the feature works as expected.
#. Send a pull request. Make sure to add yourself to AUTHORS_.

.. _`the repository`: https://github.com/cgdeboer/vectoround
.. _AUTHORS: https://github.com/cgdeboer/vectoround/blob/master/AUTHORS.rst
