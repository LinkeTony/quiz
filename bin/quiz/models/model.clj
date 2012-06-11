(ns quiz.models.model)

(def questionsAtom(atom ["1*1=" "100/2=" "100+150=" "8*8=" "100+200*3="]))
(def correctAnswersAtom (atom ["1" "50" "250" "64" "700"]))
(def questionIndexAtom (atom 0))
(def givenAnswersAtom (atom []))
(def scoreAtom (atom 0))

(defn getQuestion [] ;werkt ;getest
    (if (<= @questionIndexAtom  4)
      (nth @questionsAtom @questionIndexAtom)
      "All questions are answered"))

(defn addGivenAnswer [answer] ;werkt ;getest
  (swap! givenAnswersAtom conj answer))
  
(defn raiseQuestionIndex [] ;werkt ;getest
  (swap! questionIndexAtom inc))

(defn checkScore [] ;werkt ;getest
  (str @scoreAtom))

(defn raiseScore [] ;werkt ;getest
  (swap! scoreAtom inc))

(defn checkAnswer [givenAnswer] ;werkt ;getest
  (if(=(nth @correctAnswersAtom @questionIndexAtom) givenAnswer)(raiseScore)))

(defn checkIfAllQuestionsAnswered [] ;werkt ;getest
  (> @questionIndexAtom 4))

(defn getSpecificQuestion [index] ;werkt ;getest
  (nth @questionsAtom index))

(defn getSpecificCorrectAnswer [index] ;werkt ;getest
  (nth @correctAnswersAtom index))

(defn getSpecificGivenAnswer [index] ;werkt ;getest
  (nth @givenAnswersAtom index))

(defn resetQuiz [] ;werkt ;getest
  (reset! questionIndexAtom 0)
  (reset! scoreAtom 0)
  (reset! givenAnswersAtom [])
  )

