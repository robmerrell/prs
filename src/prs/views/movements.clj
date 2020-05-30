(ns prs.views.movements
  (:require [tick.alpha.api :as tick]))

(defn- date-from-timestamp
  "Returns a formatted date in the Mountain timezone from a Unix timestamp"
  [stamp]
  (as-> stamp n
      (tick/new-duration n :seconds)
      (tick/inst n)
      (tick/in n "America/Denver")
      (tick/format (tick/formatter "MMM dd, yyyy") n)))

(defn- percent-of
  "Calculate the percentage of a number then round up"
  [num percent]
  (->> (/ percent 100) (* num) Math/ceil int))

(defn- percentages
  "Find percentages of the given number between 30-110
  35% - 85% are done in steps of 5. The rest are done in steps of 1"
  [num]
  (->>
   (concat
    (map #(* %1 5) (range 7 18)) ;; 35 - 85
    (into [] (range 86 111))) ; 86 - 110
   (map (fn [x] [x (percent-of num x)]))))

(defn show
  [_req page-vars]
  [:div
   [:div.flex.justify-between
    [:div.text-green.text-3xl (get-in page-vars [:movement :name])]
    [:div.text-white-dark.text-3xl (get-in page-vars [:latest :value])]]

   ;; percentages
   (when (= "weight" (get-in page-vars [:movement :pr_type]))
     (when-let [pr-value (get-in page-vars [:latest :value])]
       [:div.mb-10
        (for [[percent value] (percentages pr-value)]
          [:div.mb-1.bg-gray.flex.items-stretch.text-white.text-lg.py-2
           [:div.flex-1.text-right.pr-4 (str percent "%")]
           [:div.flex-1.text-left.pl-4.text-white-dark value]])]))

   ;; history
   [:div.text-green.text-2xl "History"]
   [:div
    (for [pr (:records page-vars)]
      [:div.flex.items-stretch.bg-gray.text-white.text-lg.py-2.mb-1
       [:div.flex-1.text-center (date-from-timestamp (:created_at pr))]
       [:div.flex-1.text-center (:value pr)]
       [:div.flex-1.text-center
        [:a.text-green-lighter {:href "#"} "Edit"]]])]])
