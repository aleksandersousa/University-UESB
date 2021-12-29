import socket,threading, json
from random import randint

HOST = 'localhost'
PORT = 50000
ADM = 0

adm_questions = []
client_answers = []
stats = {}


class Server(object):
	#inicia a conexão
	def __init__(self):
		self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
		self.sock.bind((HOST, PORT))
		print('server running on: ' + str(HOST) + ':' + str(PORT))

	#escuta um cliente criando uma thread nova
	def listen(self):
		self.sock.listen(5)
		while True:
			client, address = self.sock.accept()
			threading.Thread(target=self.listen_to_client, args=(client, address)).start()
	

	#checa se o codigo do codigo ja existe no BD
	def same_code(self, code):
		for answer in client_answers:
			answer = dict(answer)
			if list(answer)[0] == code:
				return True, answer
		return False
	
	
	#salva uma resposta de pergunta no BD
	def add_answer(self, id, code, answer):
		if client_answers:
			if self.same_code(code=code):
				self.same_code(code=code)[1][code].append({
					'id': id,
					'answer': answer
				})
			else:
				client_answers.append({
					code: [
						{
							'id': id,
							'answer': answer
						}
					]
				})
		else:
			client_answers.append({
				code: [
					{
						'id': id,
						'answer': answer
					}
				]
			})
	

	#autentica um cliente no servidor
	def autenticate(self, code):
		question = next((item for item in adm_questions if item['code'] == code), False)
		if question:
			return {
				'auth': True,
				'question': question['object']['question']
			}
		else:
			return {
				'auth': False
			}


	#gera as estatisticas
	def create_stats(self, code):
		count = 1
		isEquals = False
		question = next((item for item in adm_questions if item['code'] == code), False)

		if stats:
			stats.clear()

		code_answers = []
		for item in client_answers:
			item = dict(item)
			if list(item)[0] == code:
				code_answers = item[code]
				break
						
		for i in range(len(code_answers)):
			for j in range(i + 1, len(code_answers)):
				if code_answers[i]['answer'] == code_answers[j]['answer']:
					count += 1
			
			if stats:
				for item in stats[question['object']['question']]:
					item = dict(item)
					if list(item)[0] == code_answers[i]['answer']:
						isEquals = True
				if not isEquals:
					stats[question['object']['question']].append({
						code_answers[i]['answer']: count,
						'is_correct': question['object']['answer'] == code_answers[i]['answer']
					})
			else:
				stats.update({
					question['object']['question']: [
						{
							code_answers[i]['answer']: count,
							'is_correct': question['object']['answer'] == code_answers[i]['answer']
						}
					]
				})

			count = 1
			isEquals = False


	#interface do servidor
	def listen_to_client(self, client, address):
		size = 1024
		try:
			data = client.recv(size)
			if data:
				data_received = json.loads(data)

				if (data_received['answer'] == 'fim'):
					code = data_received['code']

					self.create_stats(code)
					data_to_send = json.dumps(stats)
					client.send(str.encode(data_to_send))

					item = next((item for item in adm_questions if item['code'] == code), False)
					adm_questions.remove(item)

					print('Client disconnected')
					client.close()
				else:
					if (data_received['id'] == ADM):
						question_code = randint(1000, 9999)

						adm_questions.append({
							'code': question_code,
							'object': {
								'question': data_received['question'],
								'answer': data_received['answer'],
							}
						})

						response = str(question_code)
						client.send(str.encode(response))
					else:
						code = data_received['code']

						if (data_received['is_autenticated']):
							id = data_received['id']
							answer = data_received['answer']
							self.add_answer(id=id, code=code, answer=answer)

							self.create_stats(code)
							data_to_send = json.dumps(stats)
							client.send(str.encode(data_to_send))
						else:
							response = self.autenticate(code=code)
							data_to_send = json.dumps(response)
							client.send(str.encode(data_to_send))
			else:
				print('Client disconnected')
				client.close()
		except:
			client.close()


if __name__ == "__main__":
    Server().listen()
