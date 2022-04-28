from time import sleep
from Node import Node

PORT_PREFIX = 20000
NODE_AMOUNT = 10

def main():
    nodes = []
    for i in range(2):
        node = Node()
        
        node.setIp("127.0.0.1")
        node.setPort(PORT_PREFIX+i)
        node.start()
        nodes.append(node)
    
    sleep(2)
    for node in nodes:
        port_to_connect = node.getPort()
        ip_to_connect = node.getIp()
        
        for node2 in nodes:
            if port_to_connect == node2.getPort():
                continue
            node2.senderReceiver(ip_to_connect, port_to_connect)
    
    
if __name__ == "__main__":
    main()