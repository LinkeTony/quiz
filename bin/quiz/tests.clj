(ns quiz.tests
  (:use clojure.test
        modelletje))

 ;Ter info: bij sommmige tests hebben we comments gezet om de status te laten zien van bepaalde dingen
 ;dit omdat de uitvoer van bepaalde tests invloed hebben op andere tests en we op deze manier weten wat er wordt verwacht.

(deftest testCheckIfAllQuestionsAnswered
  (is (= false (checkIfAllQuestionsAnswered)))
  )

 ;status voor test = questionIndex staat op 0
 ;status na test = questionIndex staat op 2
(deftest testGetQuestionAndRaisQuestionIndex 
  (is (= 0 @questionIndexAtom))
  (is (= "1*1=" (getQuestion)))
  (raiseQuestionIndex)
  (is (= 1 @questionIndexAtom))
  (is (= "100/2=" (getQuestion)))
  (raiseQuestionIndex)
  (is (= 2 @questionIndexAtom))
  (is (= "100+150=" (getQuestion)))
  )

(deftest testAddGivenAnswerAndGetSpecificGivenAnswer
  (addGivenAnswer "3")
  (is (= "3" (getSpecificGivenAnswer 0)))
  (addGivenAnswer "10")
  (is (= "10" (getSpecificGivenAnswer 1)))
  (addGivenAnswer "89")
  (is (= "89" (getSpecificGivenAnswer 2)))
  (is (not= nil @givenAnswersAtom))
  )

 ; status voor test = score staat op 0
 ; status na test = score staat op 3
(deftest testRaiseScoreAndCheckScore 
  (is (= "0" (checkScore)))
  (raiseScore)
  (is (= "1" (checkScore)))
  (raiseScore)
  (is (= "2" (checkScore)))
  (raiseScore)
  (is (= "3" (checkScore)))
  )

(deftest testGetSpecificQuestion
  (is (= "1*1="       (getSpecificQuestion 0)))
  (is (= "100/2="     (getSpecificQuestion 1)))
  (is (= "100+150="   (getSpecificQuestion 2)))
  (is (= "8*8="       (getSpecificQuestion 3)))
  (is (= "100+200*3=" (getSpecificQuestion 4)))
  )

(deftest testGetSpecificCorrectAnswer
  (is (= "1"   (getSpecificCorrectAnswer 0)))
  (is (= "50"  (getSpecificCorrectAnswer 1)))
  (is (= "250" (getSpecificCorrectAnswer 2)))
  (is (= "64"  (getSpecificCorrectAnswer 3)))
  (is (= "700" (getSpecificCorrectAnswer 4)))
  )

 ; status voor test = questionindex staat op 2 en score staat op 3
 ; status na test = score staat op 4 en questionindex staat op 4
(deftest testCheckAnswerAndCheckIfAllQuestionsAnswered 
  (is (= "3"     (checkScore)))
  (is (= 4       (checkAnswer "250")))
  (is (= "4"     (checkScore)))
  (is (= nil     (checkAnswer "252")))
  (is (not= "5"  (checkScore)))
  
  (is (= false (checkIfAllQuestionsAnswered)))
  (raiseQuestionIndex)
  (is (= false (checkIfAllQuestionsAnswered)))
  (raiseQuestionIndex)
  (is (= false (checkIfAllQuestionsAnswered)))
  (raiseQuestionIndex)
  (is (= true (checkIfAllQuestionsAnswered))) ;alle vragen zijn beantwoord
  (is (= "All questions are answered" (getQuestion)))
  )
 
  ; status na test = questionIndex = 0 - score = 0 - givenAnswers = leeg
 (deftest testResetQuiz
   (resetQuiz)
    (is (= 0 @questionIndexAtom))
    (is (= 0 @scoreAtom))
    (is (= [] @givenAnswersAtom))
   )
 
(run-all-tests)

  

  
