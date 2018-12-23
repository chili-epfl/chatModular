import message_queue
import pandas as pd
from os import listdir
import gensim
import numpy as np
import nltk
from nltk.corpus import stopwords
import ast
stopwords = set(nltk.corpus.stopwords.words('english'))

tokenizer = nltk.data.load('tokenizers/punkt/english.pickle')
print('Loading word2vec...')
model = gensim.models.KeyedVectors.load_word2vec_format('files/GoogleNews-vectors-negative300.bin', binary=True)
print('Loaded word2vec.')
tfp_df = pd.read_pickle('files/HolidayTruth/tfp_df_ht.pkl')
#tfp_df = pd.read_pickle('files/TripAdvisor/tfp_df_ta.pkl')
print('Loaded tfp_df.')
max_sentences = 1

def cosine_similarity(vec1, vec2):
	return np.dot(vec1, vec2)/(np.linalg.norm(vec1) * np.linalg.norm(vec2))

def get_sentence_vector(sentence):
	tokens = [token for token in nltk.word_tokenize(sentence) if token not in stopwords]
	vectors = []
	for token in tokens:
		try:
			word_vec = model.wv[token]
			vectors.append(word_vec)
		except:
			pass
	if vectors:
		return np.mean(vectors, axis=0)
	else:
		return []

def is_not_null(sent_vec):
	for element in sent_vec:
		if not element == 0.0:
			return True
	return False

def sent_to_text_similarity(sent_vec, text_vec):
	similarities = []
	for vec in text_vec:
		if is_not_null(vec):
			similarities.append(np.dot(sent_vec, vec)/(np.linalg.norm(sent_vec) * np.linalg.norm(vec)))
	if similarities:
		return np.mean(similarities)
	else:
		return np.nan

def text_to_text_similarity(sent_vecs1, sent_vecs2):
	similarities = []
	for v1 in sent_vecs1:
		if is_not_null(v1):
			similarity = sent_to_text_similarity(v1, sent_vecs2)
			if not np.isnan(similarity):
				similarities.append(similarity)
	if similarities:
		return np.mean(similarities)
	else:
		return np.nan

def text_to_corpus_similarity(text, corpus):
	sent_vecs = text_to_sent_vec(text)
	corpus_vecs = [text_to_sent_vec(other_text) for other_text in corpus]
	max_sim = 0
	index = -1
	for text_index in range(len(corpus_vecs)):
		similarity = text_to_text_similarity(sent_vecs, corpus_vecs[text_index])
		if not np.isnan(similarity) and max_sim < similarity:
			max_sim = similarity
			index = text_index
	if index >= 0:
		return corpus[index]
	else:
		return None

def compute_similarity(row, sent_vec):
	title_sim = 0
	title_word2vec = row['Title_word2vec']
	if len(title_word2vec) > 0:
		if len(title_word2vec[0]) > 0:
			title_sim = cosine_similarity(sent_vec, title_word2vec[0])
	return title_sim

def compute_separate_similarity(row, sent_vecs):
	title_sim = 0
	title_word2vec = row['Title_word2vec']
	if len(title_word2vec) > 0:
		if len(title_word2vec[0]) > 0:
			title_sim = np.dot(sent_vecs[0], title_word2vec[0])/(np.linalg.norm(sent_vecs[0])*np.linalg.norm(title_word2vec[0]))
	fp_sim = text_to_text_similarity(sent_vecs[1:], row['First_Post_word2vec'])
	return title_sim + fp_sim

def compute_separate_similarity_no_question(row, sent_vecs):
	fp_sim = text_to_text_similarity(sent_vecs, row['First_Post_word2vec'])
	return fp_sim

def get_most_similar_title(sentences, sent_vecs):
	if sentences == 0:
		raise ValueError('Write something!')
	elif len(sentences) == 1:
		title_fp_sim = tfp_df.apply(lambda row: compute_similarity(row, sent_vecs[0]), axis=1)
	elif sentences[0].endswith('?'):
		title_fp_sim = tfp_df.apply(lambda row: compute_separate_similarity(row, sent_vecs), axis=1)
	else:
		title_fp_sim = tfp_df.apply(lambda row: compute_separate_similarity_no_question(row, sent_vecs), axis=1)
	return tfp_df.loc[title_fp_sim.idxmax()]

def get_response_sentences(sentences, sent_vecs, link, max_sentences):
	answer_df = pd.read_pickle('files/HolidayTruth/msg_df_ht_clean.pkl')
	#answer_df = pd.read_pickle('files/TripAdvisor/msg_df_ta.pkl')
	answer_df = answer_df[answer_df['Link'].map(lambda x: x == link)]
	if answer_df.empty:
		s = 'I did not find a matching sentence'
		return ("", s)
	
	best_answer = answer_df.loc[answer_df['Reply_word2vec'].apply(lambda other_vecs: 
													 text_to_text_similarity(sent_vecs, other_vecs)).idxmax()]
	
	best_sentence_idx = np.argmax([sent_to_text_similarity(sent_vec, sent_vecs) for sent_vec in best_answer.Reply_word2vec if len(sent_vec)])
	reply_sentences = best_answer.Reply
	best_reply = reply_sentences[best_sentence_idx]
	if max_sentences <= 1:
		return (best_reply, best_reply)
	else:
		context_sent_count = int((max_sentences - 1)/2)
		sent_count = len(reply_sentences)
		lower_bound = best_sentence_idx - context_sent_count
		upper_bound = best_sentence_idx + context_sent_count + 1
		return (best_reply, ' '.join(reply_sentences[max(0, lower_bound - max(0, upper_bound - sent_count)): 
										min(upper_bound + max(0, 0 - lower_bound) + ((max_sentences - 1) % 2), sent_count)]))

def chatbot_answer(question, max_sentences=1):
	try:
		sentences = tokenizer.tokenize(question)
		sent_vecs = [get_sentence_vector(sent) for sent in sentences]
		most_similar_title = get_most_similar_title(sentences, sent_vecs)
		response = get_response_sentences(sentences, sent_vecs, most_similar_title.Link, max_sentences)
		return response
	except:
		return "I'm sorry I didn't find anything, would you like to ask another question?"
			


def get_most_similar_answer(sentences, sent_vecs, df):
	if sentences == 0:
		raise ValueError('Write something!')
	else:
		#check only sent_vecs[0] (first sentence of the question) because should already have enough information to distinguish forum from tuto responses
		title_fp_sim = df.apply(lambda row: compute_similarity(row, sent_vecs[0]), axis=1)
	return df.loc[title_fp_sim.idxmax()].Answer


def find_answer(question, answers):
	try:
		answers_df = pd.DataFrame([[tokenizer.tokenize(item[0])] for item in answers], columns=['Answer'])
		answers_df['Title_word2vec'] = answers_df.Answer.apply(lambda sents: [get_sentence_vector(sent) for sent in sents])
	
		sentences = tokenizer.tokenize(question)
		sent_vecs = [get_sentence_vector(sent) for sent in sentences]
		answer = get_most_similar_answer(sentences, sent_vecs, answers_df)[0]
		
		for item in answers:
			if answer in item[0]:
				return item[1]
	except:
		return "I'm sorry I didn't find anything, would you like to ask another question?"

