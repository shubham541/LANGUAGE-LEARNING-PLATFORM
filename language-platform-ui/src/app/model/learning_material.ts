export interface LearningMaterial {
  languageVocabulary: LanguageVocabulary
  languageGrammar: LanguageGrammar
}

export interface LanguageVocabulary {
  prepositions: string[]
  code: string
  interjections: string[]
  conjunctions: string[]
  verbs: Verbs
  numbers: Numbers
  nouns: Nouns
  determiners: Determiners
  adverbs: Adverbs
  adjectives: Adjectives
  pronouns: Pronouns
}

export interface Verbs {
  Action: string[]
  State: string[]
}

export interface Numbers {
  Cardinal: string[]
  Ordinal: string[]
}

export interface Nouns {
  Person: string[]
  Place: string[]
  Thing: string[]
}

export interface Determiners {
  Articles: string[]
  Possessive: string[]
}

export interface Adverbs {
  Manner: string[]
  Degree: string[]
}

export interface Adjectives {
  Descriptive: string[]
  Qualitative: string[]
}

export interface Pronouns {
  Personal: string[]
  Demonstrative: string[]
}

export interface LanguageGrammar {
  id: string
  code: string
  parts_of_speech: PartsOfSpeech
  sentence_structure: SentenceStructure
  verb_tenses: any
}

export interface PartsOfSpeech {
  Nouns: string
  Verbs: string
  Adjectives: string
  Adverbs: string
  Prepositions: string
  Conjunctions: string
  Determiners: string
  Pronouns: string
  Interjections: string
  Numbers: string
}

export interface SentenceStructure {
  Subject: string
  Predicate: string
  Object: string
  Phrase: string
  Clause: string
}

export interface Question {
  id: string,
  question: string,
  options: string[],
  language: string,
  correctAnswer: string,
  correct: boolean
}

