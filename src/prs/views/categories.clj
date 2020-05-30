(ns prs.views.categories)

(defn show
  [_req page-vars]
  [:div
   ;; categories list
   [:nav.flex.flex-no-wrap.overflow-x-auto.scrolling-touch.no-scrollbar
    (for [category (:categories page-vars)]
      [:a.bg-green.mr-5.flex-none.p-5.text-white.text-2xl {:href (str "/categories/" (:id category))} (:name category)])]

   [:div.mt-10.mb-10.text-lg
    [:span.text-white.pr-5 "Movements"]
    [:span.text-green (count (:movements page-vars))]]

   ;; movements list
   (for [movement (:movements page-vars)]
     [:nav
      [:a {:href (str "/movements/" (:id movement))}
       [:div.bg-gray-lighter.flex.justify-between.text-3xl.pl-10.pr-10
        [:div.text-white.pt-5.pb-5 (:name movement)]
        [:div.text-white-dark.pt-5.pb-5 (:latest movement)]]]
      [:div.mb-5]])])
