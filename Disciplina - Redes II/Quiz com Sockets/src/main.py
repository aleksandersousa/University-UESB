from client import Client

ADM = 0

#interface do sistema
def main():
    type = input('Digite 0 para adm ou outro numero para cliente: ')
    
    if type == '0':
        print('Voce eh o adm!')
        client = Client(ADM)

        question = input('Digite sua pergunta: ')
        answer = input('Digite a resposta para essa pergunta: ')

        client.run(question=question, answer=answer)

        code = client.question_code

        answer = input('se quiser encerrar, digite fim: ')

        client = Client(ADM)
        client.run(answer=answer, code=code)
    else:
        is_autenticated = False
        while not is_autenticated:
            client = Client()
            id = client.id
            is_autenticated =  client.is_autenticated 
            code = input('Digite o codigo: ')
            client.run(code=code)
            is_autenticated = client.is_autenticated
        
        answer = input('Digite sua resposta: ')
        client = Client()
        client.id = id
        client.is_autenticated = is_autenticated
        client.run(answer=answer, code=code)


if __name__ ==  '__main__':
    main()
