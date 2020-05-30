(ns prs.views.login
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn index
  [_req _page-vars]
  [:div.w-full
   [:form.bg-gray.shadow-md.rounded.px-8.pt-6.pb-8.mb-4 {:action "/login" :method "post"}
    (anti-forgery-field)
    [:div.mb-4
     [:label.block.text-gray-700.text-sm.font-bold.mb-2 {:for "email"} "Email"]
     [:input#email.shadow.appearance-none.border.rounded.w-full.py-2.px-3.text-gray-700.leading-tight.focus:outline-none.focus:shadow-outline {:type "text" :name "email" :placeholder "Email"}]]
    [:div.mb-6
     [:label.block.text-gray-700.text-sm.font-bold.mb-2 {:for "password"} "Password"]
     [:input#password.shadow.appearance-none.border.border-red-500.rounded.w-full.py-2.px-3.text-gray-700.mb-3.leading-tight.focus:outline-none.focus:shadow-outline {:type "password" :name "password" :placeholder "**********"}]]
    [:div.flex.items-center.justify-between
     [:input.bg-green.text-white.font-bold.py-2.px-4.rounded.focus:outline-none.focus:shadow-outline {:type "submit" :value "Sign In"}]]]])
