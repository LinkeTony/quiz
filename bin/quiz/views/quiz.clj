(ns quiz.views.quiz
  (:require [quiz.views.common :as common])
  (:require [noir.session :as session])
  (:require [noir.response :as response])
  (:require [quiz.models.model :as model])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers
        hiccup.form-helpers))

(defpage "/quiz" []
  ;(session/clear!)
  (model/resetQuiz)
  ;(session/put! :questionIndex 0)
         (common/layout
           [:p "<br /><h1>Welcome to quiz!!</h1><br /><br /><br /><br /><br /><br /><br /><br /><br />"]
           (form-to [:post "/quiz"]
                    (model/getQuestion)
                    [:p "<br />"]
                    (text-field "Answer" "")
                    [:p "<br />"]
                    (submit-button "Next"))))


(defpage [:post "/quiz"] {:as formvalues}
  ;(render "/results")
  (model/addGivenAnswer (formvalues :Answer))
  (model/checkAnswer (formvalues :Answer))
    (model/raiseQuestionIndex)
    
    
  ;(when(true? model/checkIfAllQuestionsAnswered) (render "/results") )
   ; (:when (true? model/checkIfAllQuestionsAnswered) (render "/results"))
  (common/layout

  [:p "<br /><h1>Welcome to quiz!!</h1><br /><br /><br /><br /><br /><br /><br /><br /><br />"]
           (form-to [:post "/quiz"]
                    (model/getQuestion)
                    [:p "<br />"]
                    (text-field "Answer" "")
                    [:p "<br />"]
                    (submit-button "Next")
                    [:p "<br />"]
                     (link-to "/results" "Resultaten")
                   )))





(defpage "/results" []
(common/layout
           [:p "<br /><h1>Resultaten</h1><br /><br /><br /><br /><br /><br /><br /><br /><br />"]
           "<table><tr><td><h2>Vragen</h2></td><td><h2>Correcte Antwoord</h2></td><td><h2>Gegeven Antwoord</h2></td></tr>"
           
             (for [x [0 1 2 3 4 ]]
               
                 (str "<tr><td>" (model/getSpecificQuestion x) "</td><td>" (model/getSpecificCorrectAnswer x)  "</td><td>" (model/getSpecificGivenAnswer x) "</td></tr>")
                 )
           "</table>"
           "<br /> <h3> uw score: " (model/checkScore) "</h3>"
))







(defpartial postNewQuestion []
  [:div
     [:p "<h1>Welcome to quiz!!</h1>"]
           (form-to [:post "/quiz"]
                    (model/getQuestion)
                    (text-field "Answer" "")
                    (submit-button "Next"))])

