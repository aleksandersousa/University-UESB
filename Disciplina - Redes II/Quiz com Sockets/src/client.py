import socket, json
from random import randint

HOST = '127.0.0.1'
PORT = 50000
ADM = 0
s = None

class Client:
    question_code = None
    is_autenticated = False
    size = 1024

    def __init__(self, is_adm = None):
        self.is_adm = is_adm
        self.id = randint(1, 100)
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.connect((HOST, PORT))
    

    def format_stats(self, data, isParcial = False):
        data = dict(data)
        
        if not isParcial:
            print('\nestatisticas da questao: ' + list(data)[0] + '\n')
        
        for answer in data[list(data)[0]]:
            answer = dict(answer)
            if answer[list(answer)[1]]:
                print(str(answer[list(answer)[0]]) + ' pessoa(s) respondeu(ram) ' + list(answer)[0] + ' - correta')
            else:
                print(str(answer[list(answer)[0]]) + ' pessoa(s) respondeu(ram) ' + list(answer)[0] + ' - errada')


    def run(self, question = None, answer = None, code = None):
        if (answer == 'fim'):
            if self.is_adm == ADM:
                code = int(code)
                ans = {
                    'code': code,
                    'answer': answer
                }

                data_string = json.dumps(ans)
                self.s.sendall(str.encode(data_string))

                data = self.s.recv(self.size)
                data = data.decode('UTF-8')
                data = json.loads(data)
                self.format_stats(data)
            else:
                print('Voce nao eh adm! Nao tem privilegios para encerrar.')
        else:
            if self.is_adm == ADM:
                quest = {
                    'id': ADM,
                    'question': question,
                    'answer': answer,
                }
                data_string = json.dumps(quest)
                self.s.sendall(str.encode(data_string))

                data = self.s.recv(self.size)
                data = data.decode('UTF-8')
                self.question_code = data
                print('\nseu codigo eh: ' + data)
            else:
                if self.is_autenticated:
                    ans = {
                        'id': self.id,
                        'code': int(code),
                        'is_autenticated': self.is_autenticated,
                        'answer': answer
                    }

                    data_string = json.dumps(ans)
                    self.s.sendall(str.encode(data_string))

                    data = self.s.recv(self.size)
                    data = data.decode('UTF-8')
                    data = json.loads(data)
                    print('\nResultados parciais: ')
                    self.format_stats(data=data, isParcial=True)
                else:
                    try:
                        question_code = int(code)
                        ans = {
                            'id': self.id,
                            'is_autenticated': self.is_autenticated,
                            'code': question_code,
                            'answer': answer
                        }

                        data_string = json.dumps(ans)
                        self.s.sendall(str.encode(data_string))
                        
                        data = self.s.recv(self.size)
                        data = data.decode('UTF-8')
                        data = json.loads(data)

                        if data['auth']:
                            self.is_autenticated = data['auth']
                            print('\nA pergunta eh: ' + data['question'])
                        else:
                            print('codigo invalido! Por favor, digite um codigo valido!')
                    except:
                        print('formato de codigo invalido! Por favor, digite um codigo valido!')

