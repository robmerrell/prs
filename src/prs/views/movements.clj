(ns prs.views.movements)

(defn- percent-of
  "Calculate the percentage of a number rounded up"
  [num percent]
  (->>
   (* percent 0.01)
   (* num)
   Math/ceil
   int))

(defn- percentages
  "Find percentages of the given number between 30-110
  30% - 80% are done in steps of 5. The rest are done in steps of 1"
  [num]
  (->>
   (concat
    (map #(* %1 5) (range 7 18))
    (into [] (range 86 111)))
   (map (fn [x] [x (percent-of num x)]))))

(defn show
  [_req _page-vars]
  [:div
   [:div.flex.justify-between
    [:div.text-green.text-3xl "Clean and Jerk"]
    [:div.text-white-dark.text-3xl "205"]]

   ;; percentages
   [:div.mb-10
    (for [[percent value] (percentages 205)]
      [:div.mb-1.bg-gray.flex.items-stretch.text-white.text-lg.py-2
       [:div.flex-1.text-right.pr-4 (str percent "%")]
       [:div.flex-1.text-left.pl-4.text-white-dark value]])]

   ;; history
   [:div.text-green.text-2xl "History"]
   [:div
    [:div.flex.items-stretch.bg-gray.text-white.text-lg.py-2.mb-1
     [:div.flex-1.text-center "Feb 20, 2020"]
     [:div.flex-1.text-center "195"]
     [:div.flex-1.text-center
      [:a.text-green-lighter {:href "#"} "Edit"]]]]])
