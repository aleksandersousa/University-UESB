import socket
import threading
import random
import json
from time import sleep

class Node(threading.Thread):
    __ip = None
    __port = None
    __data = []
    __buffer = {}
    
    def __init__(self,ip = None,sendPort = None,data = [], buffer = {}):
        self.__buffer = buffer
        self.__ip = ip
        self.__port = sendPort
        self.__data = data
        threading.Thread.__init__(self)
        
    def getIp(self):
        return self.__ip
    
    def setIp(self,ip = __ip):
        self.__ip = ip
        
    def getPort(self):
        return self.__port
    
    def setPort(self, sendPort):
        self.__port = sendPort
    
    def getData(self):
        return self.__data
    
    def setData(self, data = __data):
        self.__data = data
        
    def getBuffer(self):
        return self.__buffer
    
    def setBuffer(self,buffer = __buffer):
        self.__buffer = buffer

    def generateRandomList(self):
        randomlist = []
        for i in range(5):
            n = random.randint(1, 30)
            randomlist.append({"number"+str(i): n})
        return randomlist
   
    def receiver(self):
        self.__sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        soc = self.__sock
        print(self.__port)
        soc.bind((self.__ip,self.__port))
        
        soc.listen(100)
        print ("socket is listening")           

        while True:
            c, addr = soc.accept()    
            print ('Got connection from', addr )
            
            # send a message to the client. encoding to send byte type.
            randomList = self.generateRandomList()
            data = json.dumps(randomList)
            c.send(data.encode())
            print('array enviado', data)
            
            # Close the connection with the client
            c.close()
            # Breaking once connection closed
            #break
        
    def senderReceiver(self,ip,port):
        print("conectando {} {} com {} {}".format(ip,port,self.__ip,self.__port))

        connected = False
        soc = socket.socket()

        while not connected:
            try:
                soc.connect((ip,port))
                connected = True
                print("socket connected")
            except socket.error as err:
                print("socket error", err)
                sleep(1)

        # receive data from the server and decoding to get the string.
        print("array recebido", soc.recv(4096).decode())

        # close the connection
        soc.close()    
                        

    def run(self):
        print("funcionando")
        self.receiver()
